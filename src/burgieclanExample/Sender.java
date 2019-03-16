package Incomplete;
import java.io.DataOutputStream;
import java.io.IOException;

import Incomplete.ProtocolDB;

/**
 * The Sender will take care of all actions required for sending messages.
 */
public class Sender {
    /**
     * Create a new Sender that has an empty nickname-socket mapping
     */
    public Sender(Connection connection) throws IOException {
    	this.connection = connection;
    	out = new DataOutputStream(connection.getSocket().getOutputStream());
    }
    
    /**
     * Send a message
     * @param command The command of this message
     * @param arguments	The arguments of this message
     */
    public void send(String command, String[] arguments) {
    	String message = command;
    	if(arguments != null) {
    		for(int i = 0; i < arguments.length; i++) {
    			message = message + ProtocolDB.COMMAND_DELIMITER + arguments[i];
    		}
    	}

    	try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Connection connection;
    private DataOutputStream out;
}