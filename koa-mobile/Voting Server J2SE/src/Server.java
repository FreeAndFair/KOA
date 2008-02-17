import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This server class will listen indefinitely for connection requests from a client
 * and spawn a new thread to handle each new session on the appropriate port.
 */
public class Server {
	// The port on which to listen
	final static int PORT = 8000;
    public static void main(String[] args) throws IOException {
        // Create a new server socket
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Listening on port " + PORT + "...");
        
        // To number a client
        int clientNo = 1;
        
        while (true) {
            // Listen for a new connection request
            Socket connectToClient = serverSocket.accept();
            
            // Print the new connection number to the console
            System.out.println("Start thread for client " + clientNo);				
            
            // Find the clients host name and ip address
            InetAddress clientInetAddress = connectToClient.getInetAddress();
            
            System.out.println("Client " + clientNo + "'s host name is "
                    + clientInetAddress.getHostName());
            
            System.out.println("Client " + clientNo + "'s IP Address is "
                    + clientInetAddress.getHostAddress());
            
            // Create a new thread for the connection
            HandleAClient thread = new HandleAClient(connectToClient);
            
            // Start the new thread
            thread.start();
            
            // Increment clientNo
            clientNo++;
        }
    }
}

