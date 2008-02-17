import java.awt.Choice;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.jms.TextMessage;

import org.apache.fop.viewer.Command;

import com.sun.jdi.connect.Connector;

/**
 * J2ME MIDlet class. Deployable as an application onto a CLDC mobile device.
 * Main client class which connects to the server over GPRS IP. Allows the client to vote in 
 * a rich environment primarily due to its integration with J2ME Polish.
 */
public class ClientMIDlet extends MIDlet implements Runnable, CommandListener {
	
	private Display display;
	private Form f;
	private StringItem si;
	private StringItem voteStatus;
	private StringItem stats;
	private ChoiceGroup cg;
	
	private boolean isPaused;
	private boolean stop;
	private boolean wasClicked = false;
	private int topic = 0;
	
	private Command selectCommand = new Command("Select", Command.ITEM, 1);
	private Command exitCommand = new Command("Exit", Command.EXIT, 1);
	private Command restartCommand = new Command("Restart", Command.ITEM, 1);
	
	private DataInputStream is;
	private DataOutputStream os;
	private SocketConnection sc;
	private MessageConnection mc;
	
	/**
	 * Constructor creates a new MIDlet, sets the display and 
	 * adds the initial items to the screen.
	 */
	public ClientMIDlet() {
		display = Display.getDisplay(this);
		
		//#style mainScreen
		f = new Form("Socket Client");
		
		//#style menuItem
		si = new StringItem("Status:", " ");
		voteStatus = new StringItem("Vote Status:", " ");
		stats = new StringItem("Statistics:", " ");
		
		f.addCommand(exitCommand);
		f.addCommand(selectCommand);
		
		f.setCommandListener(this);
		display.setCurrent(f);	
	}
	
	/**
	 * Check if the application is paused.
	 * @return Boolean indicating whether the application is suspended.
	 */
	public boolean isPaused() {
		return isPaused;
	}
	
	/**
	 * Required method to start the life cycle of the MIDlet.
	 */
	public void startApp() 
	{
		isPaused = false;
		Thread t = new Thread(this);
		t.start();      
	}
	
	/**
	 * Suspend the application.
	 */
	public void pauseApp() {
		isPaused = true;
	}
	
	/**
	 * Bring the MIDlet to it's destroyed state.
	 * @param unconditional True to destroy.
	 */
	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
		try {
			stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Indicate the thread to start.
	 * Starts and finishes the session with the server.
	 */
	public void run() {
		try {
			sc = (SocketConnection) Connector.open("socket://localhost:8000");  //localhost:8000 csserver.ucd.ie:8000
			si.setText("Connected to Server\n");
			
			is = sc.openDataInputStream();
			os = sc.openDataOutputStream();
			
			// Identify itself as a client connection
			os.writeInt(1);
			
			/*************************************************************************
			 **      Retrieve topic data from the server and diplay on device       **
			 *************************************************************************/
			
			// Loop forever, recieving data
			while(true) {			
				f.append(si);
				
				/* Recieve size of the ArrayList on the server */
				int num = is.readInt();
				
				// Read in a list of Topic Strings, and a list of id chars. Store both in an array.
				String[] voteTopic = new String[num];
				int[] voteId = new int[num];
				Image[] images = new Image[num];
				int[] smsFlag = new int[num];
				long[] smsNum = new long[num];
				
				for (int i=0; i<num; i++) {
					voteTopic[i] = is.readUTF();
					voteId[i] = is.readInt();
					
					int length = is.readInt();
					if(length == 0)
						images[i] = null;
					else {
						byte[] imagedata = new byte[length];
						is.readFully (imagedata, 0, length);
						images[i] = Image.createImage(imagedata,0,length);
					}
					
					smsFlag[i] = is.readInt();
					if( smsFlag[i] == 1 ) {
						smsNum[i] = is.readLong();
					}               
				}
				
				cg = new ChoiceGroup("Please select topic", Choice.EXCLUSIVE,voteTopic,images);
				f.append(cg);
				
				
				/*************************************************************************
				 **      Choose a topic, send to server and wait for response           **
				 *************************************************************************/
				
				while (wasClicked == false) {
					// Wait until a choice has been made.
				}
				wasClicked = false;
				
				int topicChoice = topic;
				// Retrieve the appropriate id Number from the voteId array.
				int selected = voteId[topicChoice];		
				// Write the selected int, representing the topicID, to the server
				os.writeInt(selected);
				
				// Check if client has already voted
				if(is.readInt() == 1) {
					Alert a = new Alert("Error", "Sorry you have already voted, please choose another topic.", null, AlertType.ERROR);
					display.setCurrent(a);
					a.setCommandListener(this);
					a.setTimeout(2000);
					continue;
				}
				
				
				/*************************************************************************
				 **      Retrieve possible choices for the chosen topic and display     **
				 *************************************************************************/
				
				
				// The client recieves the size of an incoming list of group of answers.
				num = is.readInt();
				
				// The client now recieves a list of answers from the server, along with a list id numbers.
				String[] voteAns = new String[num];
				int[] voteIDAns = new int[num];
				Image[] imagesA = new Image[num];
				
				for (int i=0; i<num; i++) {
					voteAns[i] = is.readUTF();
					voteIDAns[i] = is.readInt();
					int length = is.readInt();
					if(length == 0)
						images[i] = null;
					else {
						byte[] imagedata = new byte[length];
						is.readFully (imagedata, 0, length);
						imagesA[i] = Image.createImage(imagedata,0,length);
					}
				}
				
				f.delete(1);
				cg = new ChoiceGroup("Please select answer", Choice.EXCLUSIVE, voteAns, imagesA);
				f.append(cg);
				
				
				/*************************************************************************
				 **      Choose a choice, send to server and wait for response          **
				 *************************************************************************/
				
				while (wasClicked == false) {
					// Wait unill a choice is made.
				}
				wasClicked = false;
				
				// Now use the index to retrieve the appropriate id Number in the voteIdAns array.
				int selectedAns = voteIDAns[topic];
				
				// If the topic which was voted on was flagged SMS, then then SMS server should
				// relay the answer to the server. Otherwise the client simply sends answer choice
				// to the server.
				if( smsFlag[topicChoice] == 1) {
					os.writeInt(2);
					String vote = voteIDAns[topic]+"";
					mc = (MessageConnection)Connector.open("sms://"+smsNum[topicChoice]+":6000");
					sendTextMessage(mc,vote,"sms://"+smsNum[topicChoice]+":6000");
					mc.close();
					voteStatus.setText("Check relay!");
				}
				else {
					os.writeInt(1);

					os.writeInt(selectedAns);	
					int voteRegister = is.readInt();
					
					if (voteRegister == 0)
						voteStatus.setText("Vote has been registered!");
					else
						voteStatus.setText("Vote was not registered!");
				}
			
				f.delete(1);
				
				/*************************************************************************
				 **      Recieve and display current vote statistics                    **
				 *************************************************************************/
				
				f.append(voteStatus);
				
				String selTopic = voteTopic[topicChoice];            
				StringBuffer sb= new StringBuffer();
				int anssize = is.readInt();
				int totalVotes = is.readInt();
				for(int s=0; s < anssize; s+=2) {
					int percentage;
					String ans = is.readUTF();
					int ansVotes = is.readInt();
					if(totalVotes !=0){
						percentage = (ansVotes*100)/totalVotes;
					}
					else
						percentage=0;
					sb.append(ans+" has "+ansVotes+" votes!  "+percentage+"%\n");             
				}
				stats.setText("\n"+selTopic+"\n"+sb);
				f.append(stats);
				
				f.removeCommand(selectCommand);
				f.addCommand(restartCommand);
				
				while (wasClicked == false) {
					// Wait unill a choice is made.
				}
				wasClicked = false;
				
				f.deleteAll();
				f.removeCommand(restartCommand);
				f.addCommand(selectCommand);	
			}
		}
		catch (ConnectionNotFoundException cnfe) {
			Alert a = new Alert("Connection Error", "Cannot connect to server. Please ensure server" +
					" is running.", null, AlertType.ERROR);
			a.setTimeout(Alert.FOREVER);
			a.setCommandListener(this);
			display.setCurrent(a);
		}
		catch (IOException ioe) {
			if (!stop) {
				ioe.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Handles events when a command is pressed.
	 */
	public void commandAction(Command c, Displayable s) {
		
		if (c == selectCommand && !isPaused()) {
			topic = cg.getSelectedIndex();
			wasClicked = true;			
			try {
				// Write 0 to notify continue
				os.writeInt(0);
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		
		if (c == Alert.DISMISS_COMMAND) {
			destroyApp(true);
		}
		
		if (c == exitCommand) {
			destroyApp(true);
		}
		
		if (c == restartCommand && !isPaused()) {
			try {
				// Write 0 to notify continue
				os.writeInt(0);

			} catch (IOException e) {
				e.printStackTrace();
			} 
			wasClicked = true;
			topic=0;		
		}		
	}
	
	/**
	 * Stop all streams.
	 * @throws IOException
	 */
	public void stop() throws IOException {
		
		// Write 1 to notify finished
		os.writeInt(1);
		
		if (is != null)
			is.close();
		if (os != null)
			os.close();
		if (sc != null)
			sc.close();
		if(mc!=null)
			mc.close();	
	}
	
	/**
	 *  Sends a TextMessage on the specified connection.
	 *  @param mc The MessageConnection
	 *  @param msg The message to send
	 *  @param url The destination address.
	 */
	private void sendTextMessage(MessageConnection mc, String msg, String url) {
		try {
			TextMessage tmsg =(TextMessage)mc.newMessage(MessageConnection.TEXT_MESSAGE);
			if (url!= null)
				tmsg.setAddress(url);
			tmsg.setPayloadText(msg);
			mc.send(tmsg);
		}
		catch(Exception e) {
			System.out.println("Error sending message: " + e);
		}
	}
}