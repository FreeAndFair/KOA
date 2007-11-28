import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * This is the primary server side class which effectively handles the session with the client
 * and follows the communication protocol. All interactions with the client is done through this class.
 */
public class HandleAClient extends Thread {
	private Socket connectToClient;
	private DataInputStream isFromClient;
	private DataOutputStream osToClient;
	
	/**
	 * During initialization set the class' private socket variable to that specified 
	 * by the Server class.
	 * @param socket The port number which handles the client session.
	 */
	public HandleAClient(Socket socket) {
		connectToClient = socket;
	}
	
	/**
	 * Creates a new list containing a new Ballot object for each choice.
	 * @param al The complete list from StatementExecutor from which to create a new one.
	 * @return List containing a Ballot object for each choice.
	 */
	private ArrayList makeChoiceList(ArrayList al) {
		ArrayList open = new ArrayList();
		for (int i=0; i<al.size(); i+=3) {
			String topic = (String)al.get(i);
			Integer idNumber = Integer.valueOf((String)(al.get(i+1)));
			byte[] imageA = (byte[]) al.get(i+2);
			
			open.add(new Ballot(topic, idNumber.intValue(), imageA, 0, 0));
		}
		return open;
	}
	
	/**
	 * Creates a new list containing a new Ballot object for each topic.
	 * @param al The complete list from StatementExecutor from which to create a new one.
	 * @return List containing a Ballot object for each topic.
	 */
	private ArrayList makeTopicList(ArrayList al) {
		ArrayList open = new ArrayList();
		for (int i=0; i<al.size(); i+=5) {
			String topic = (String)al.get(i);
			Integer idNumber = Integer.valueOf((String)(al.get(i+1)));
			byte[] imageB = (byte[]) al.get(i+2);
			Integer sms = Integer.valueOf((String)(al.get(i+3)));
			Long num = Long.valueOf((String)(al.get(i+4)));
			
			open.add(new Ballot(topic, idNumber.intValue(),imageB,sms.intValue(),num.longValue()));
		}
		return open;
	}
	
	/**
	 * Resizes the image retrieved from the database, into a new more compact image
	 * for display on the client device.
	 * @param data The image represented as an array of bytes.
	 * @return A byte array representing the new resized image.
	 * @throws IOException
	 */
	private byte[] resizeImage(byte[] data) throws IOException {        
		Image image = new ImageIcon(data).getImage();    
		ImageIcon adjustedImg = new ImageIcon(image.getScaledInstance(30,30, Image.SCALE_SMOOTH));
		
		int resizeWidth = adjustedImg.getIconWidth();
		int resizeHeight = adjustedImg.getIconHeight();
		
		BufferedImage bi = new BufferedImage(resizeWidth, resizeHeight, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D big = bi.createGraphics();
		big.drawImage(adjustedImg.getImage(), 0, 0, null);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		encoder.encode(bi);
		byte[] byteArray = os.toByteArray();
		return byteArray;
	}
	
	/**
	 * Default run method for the thread.
	 */
	public void run() {
		try {
			
			// Create data input and output streams
			isFromClient = new DataInputStream(connectToClient.getInputStream());
			osToClient = new DataOutputStream(connectToClient.getOutputStream());
			
			int receivedAns;
			
			/* Check whether the connection has been initiated by a client or an SMS relay.
			 * 2 for relay, 1 for client. */
			int clientcheck = isFromClient.readInt();
			
			// Connection from SMS relay
			if ( clientcheck  == 2 ) {
				// Read chosen answer ID from the relay
				receivedAns = isFromClient.readInt();
				
				// Try to register the ID associated with the chosen answer
				StatementExecutor sE = new StatementExecutor();
				if(sE.voteRegister(receivedAns))
					osToClient.writeUTF("Registered");
				else
					osToClient.writeUTF("NotRegistered");
				
				connectToClient.close();
				isFromClient.close();
				osToClient.close();
				
			}
			
			// Continously serve the client
			while(clientcheck ==1) {
				
				// Create a new StatementExecutor to service the database.
				StatementExecutor sE = new StatementExecutor();
				
				// Create a new ArrayList to hold data for the client
				ArrayList topicsToClient = new ArrayList();
				ArrayList ansToClient = new ArrayList();
				
				
				/**********************************************************
				 **      Compile and send topic list to the client       **
				 **********************************************************/
				
				// Create a new topic list of Ballot objects
				topicsToClient = makeTopicList(sE.showTopics());
				
				/* Send the size of the ArrayList to the client */
				osToClient.writeInt(topicsToClient.size());
				
				Ballot ballot = new Ballot();
				for (int i=0; i<topicsToClient.size(); i++) {
					ballot = (Ballot)topicsToClient.get(i);
					String string = ballot.getTopic();
					int idNum = ballot.getIdNum();
					byte[] image = ballot.getImage();
					int smsFlag = ballot.getSMS();
					long telNum = ballot.getTelNum();
				
					/* Write the topic string to the client */
					osToClient.writeUTF(string);
					
					/* Write the topic ID to the client */
					osToClient.writeInt(idNum);
					
					if(image.length != 0) {
						/* resize image and convert back to bytes */
						byte[] newimage = resizeImage(image);
						
						/* Write the image length follwed by the image in bytes to the client */
						osToClient.writeInt(newimage.length);
						osToClient.write (newimage, 0, newimage.length);
					}
					else
						osToClient.writeInt(0);

					/* Write SMS number to client provided sms flag is set to 1 */
					osToClient.writeInt(smsFlag);
					if( smsFlag == 1) {
						osToClient.writeLong(telNum);
					}
				}
				
				// Check if the user has exited the application
				int finished = isFromClient.readInt();
				if(finished == 0){/* continue */ } else break;
				
				/**********************************************************
				 **      Read and process the reply from the client      **
				 **********************************************************/
				
				// Read in the int representing the topic ID from the client
				int receivedTopic = isFromClient.readInt();
				
				// Check if the client has already voted on the chosen topic
				String ip = connectToClient.getInetAddress().getHostAddress();
				if(sE.checkIP(receivedTopic,ip)) {
					System.out.println("Already voted!");
					// Tell the client to stop
					osToClient.writeInt(1);
					continue;
				}
				osToClient.writeInt(0);
				
				/**********************************************************
				 **   Compile and send possible choices to the client    **
				 **********************************************************/
				
				/* Return an arraylist representing the answers and associated ID for
				 * the chosen topic. */
				ArrayList ansList = sE.showAnswers(receivedTopic);                   
				ansToClient = makeChoiceList(ansList);
				
				osToClient.writeInt(ansToClient.size());
				
				for (int j=0; j<ansToClient.size(); j++) {
					ballot = (Ballot)ansToClient.get(j);
					String stringAns = ballot.getTopic();
					int idNumAns = ballot.getIdNum();
					byte[] imageans = ballot.getImage();
					
					osToClient.writeUTF(stringAns);
					osToClient.writeInt(idNumAns);
					
					if(imageans.length != 0) {
						byte[] newimage = resizeImage(imageans);
						osToClient.writeInt(newimage.length);
						osToClient.write (newimage, 0, newimage.length);
					}
					else
						osToClient.writeInt(0);
				}
				
				// Check if the user has exited the application
				finished = isFromClient.readInt();
				if(finished == 0){/* continue */ } else break;
				
				// Check if the current vote was relayed (1=no, 2=yes)
				int relayed = isFromClient.readInt();
				
				/**********************************************************
				 **      Read and process the reply from the client      **
				 **********************************************************/
				
				if(relayed ==1) {
					// Now recieve the selected answer from the client in the form of an idNum
					receivedAns = isFromClient.readInt();
					
					// Try to register the ID associated with the chosen answer
					if(sE.voteRegister(receivedAns)) {
						// Successful
						osToClient.writeInt(0);
					}
					else {
						// Failed to register
						osToClient.writeInt(1);
					}
				}
				
				// Add the host / IP pair to the database to prevent multiple votes
				sE.addIP(receivedTopic,ip);
				System.out.println("Added ("+ip+","+receivedTopic+") to the database");
				
				
				/**********************************************************
				 **      Send vote statistics to the client              **
				 **********************************************************/
				
				/* Send vote statistics to the client */
				ArrayList statsList = sE.getStats(receivedTopic);
				// statsList contains each answer followed by corresponding number of votes
				osToClient.writeInt(statsList.size());
				
				// Caculate the total votes under the chosen topic for percentage purposes
				int sum =0;
				for(int s=0; s < statsList.size(); s+=2)
				{
					sum += Integer.valueOf((String)statsList.get(s+1)).intValue();
					
				}
				osToClient.writeInt(sum);
				
				for(int s=0; s < statsList.size(); s+=2)
				{
					osToClient.writeUTF((String)statsList.get(s));
					osToClient.writeInt(Integer.valueOf((String)statsList.get(s+1)).intValue());
					
				}
				
				// Check if the user has exited the application
				finished = isFromClient.readInt();
				if(finished == 0){/* continue */ } else break;
				
			}
			
			connectToClient.close();
			isFromClient.close();
			osToClient.close();	
		}
		
		catch (EOFException eof) {
			try {
				connectToClient.close();
				isFromClient.close();
				osToClient.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}		
			eof.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}