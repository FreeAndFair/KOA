import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.wireless.messaging.*;

/**
 * J2ME MIDlet class. Deployable as an application onto a CLDC mobile device.
 * This is the SMS Relay class, which should be deployed and executed on the device
 * which is acting as the SMS relay. This class continuously listens for SMS connections.
 */
public class SMSRelay extends MIDlet implements Runnable, CommandListener { 
	private Display display;
	private Form f;
	private StringItem si;
	private Command exitCommand = new Command("Exit", Command.EXIT, 1);
	private boolean done;
	
	// To talk to server (send chosen ansID to server when recieved from client)
	private DataOutputStream os;
	private DataInputStream is;
	private SocketConnection sc;
	private MessageConnection mc;
	
	/**
	 * Constructor initializes the display.
	 */
	public SMSRelay() {
		display = Display.getDisplay(this);
		f = new Form("SMS Relay");
		si = new StringItem("Waiting...","");
		f.addCommand(exitCommand);
		display.setCurrent(f);
		f.setCommandListener(this);
	}
	
	/**
	 * Required method to start the life cycle of the MIDlet.
	 */
	public void startApp() {
		Thread t = new Thread(this);
		t.start();      
	}
	
	/**
	 * Required method to pause the application.
	 */
	protected void pauseApp() {	
	}
	
	/**
	 * Bring the MIDlet to it's destroyed state.
	 * @param unconditional True to destroy.
	 */
	public void destroyApp(boolean unconditional) {
		try {
			stop();
			notifyDestroyed();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	} 
	
	/**
	 * Stop all streams.
	 * @throws IOException
	 */
	public void stop() throws IOException {
		if(mc != null)
			mc.close();
	}
	
	/**
	 * Indicate the thread to start.
	 * Listens on a new MessageConnection.
	 */
	public void run() {
		f.append(si);

		if (mc == null) {
			try {
				// Open the messaging inbound port.
				mc = newMessageConnection("sms://:6000"); 
				loopForMessages();			
			}
			catch(Exception e) {
				System.out.println
				("startApp.newMessageProcessor" + e);
			}
		}	
	}
	
	/**
	 *  newMessageConnection returns a new MessageConnection
	 *  @param addr is the address (local or remote)
	 *  @return MessageConnection that was created (client 
	 *  or server)
	 *  @throws Exception if an error is encountered
	 */
	public MessageConnection newMessageConnection(String addr)
	throws Exception {
		return((MessageConnection)Connector.open(addr));
	}
	
	
	
	/** 
	 * Loop for messages until done 
	 */
	public void loopForMessages() {
		while (!done)
			processMessage();
	}
	
	/** 
	 * Notify the message processor to exit 
	 */
	public void notifyDone() {
		done = true;
	}
	
	/** 
	 * processMessage reads and processes a Message 
	 */
	public void processMessage() {
		Message msg = null;
		//  Try reading a message
		try {
			msg = mc.receive();
		}
		catch (Exception e) {
			// Handle reading errors
			System.out.println("processMessage.receive " + e);
		}
		// Process the received message
		if (msg instanceof TextMessage) {
			TextMessage tmsg = (TextMessage)msg;
			si.setText(tmsg.getPayloadText());
			int ansID = Integer.parseInt(tmsg.getPayloadText());
			initiateConnection(ansID);
		}
		else
			System.err.println("Type of message not a TextMessage!");   
	}
	
	/**
	 * This method will connect to the server and register the specified ID,
	 * that was just recieved by SMS from the client.
	 * @param ansID The ID to be registered.
	 */
	public void initiateConnection(int ansID) {
		try {
			sc = (SocketConnection) Connector.open("socket://csserver.ucd.ie:8000");
			os = sc.openDataOutputStream();
			is = sc.openDataInputStream();
			
			// Identify itself as a relay connection
			os.writeInt(2);
			
			os.writeInt(ansID);
			String reg = is.readUTF();
			si.setText(reg);
			
			is.close();
			os.close();
			sc.close();
			
		} catch (IOException e) {
		}   
	}
	
	/**
	 * Handles events when a command is pressed.
	 */
	public void commandAction(Command c, Displayable s) {
		if (c == exitCommand) {
			destroyApp(true);		
		}
	}	
}