package Incomplete;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * The Listener will listen for incomming packets and will parse them.
 */
public class Listener implements Runnable {
    /**
     * Create a new listener for the given socket
     * @param server
     * @throws IOException
     * 		There was an IO problem creating the input stream for this listener
     */
    public Listener(Connection connection) throws IOException{
        this.connection = connection;
        this.in = new DataInputStream(connection.getSocket().getInputStream());
        (new Thread(this)).start();
    }
    
    /**
     * Keep listening for incoming messages from the socket of this Listener and parse the messages.
     */
    public void run() {  
    	try {
    	String data = in.readUTF();
    	String[] message = data.split(ProtocolDB.COMMAND_DELIMITER);
    	String command = message[0];
    	System.out.println(command);
    	switch(command){
    		case ProtocolDB.CLIENTCONNECT_COMMAND:
    			System.out.println(message[1]);
    			connection.getChatServer().clientConnect((ClientConnection)connection, message[1]);
    		case ProtocolDB.CLIENTDISCONNECT_COMMAND:
    			connection.getChatServer().clientDisconnect((ClientConnection)connection);
    		case ProtocolDB.CLIENTMESSAGE_COMMAND:
    			connection.getChatServer().clientMessage((ClientConnection)connection, message[1]); 
    	}
    	} catch(Exception e) {System.out.println("Exception: " + e.getMessage()); }
    }
    
    

    private Connection connection;
    private DataInputStream in;
    
}

