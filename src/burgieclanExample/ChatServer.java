package Incomplete;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {

	/***************
	 * CONSTRUCTOR *
	 ***************/

	public ChatServer() {
	    clientConnections = new Vector<ClientConnection>();

		(new Thread(new ClientListenThread(this))).start();	
	}


	/******************
	 * LISTEN THREADS *
	 ******************/
	
	private class ClientListenThread implements Runnable {
		public ClientListenThread(ChatServer chatserver) {
			this.chatServer = chatserver;	
		}
		
		public void run() {
			try {
			int serverPort = ServerConfig.getLocalClientPort();
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Running");
			while(true){
				Socket clientSocket = listenSocket.accept();
				System.out.println("Accepted");
				ClientConnection c = new ClientConnection(clientSocket, chatServer);
			}
			} catch(IOException e) { System.out.println("Listen: " + e.getMessage());}
			
		}
		
		private ChatServer chatServer;
	}
	

	/********************
	 * PROTOCOL METHODS *
	 ********************/

	/**
	 * Connect a client to the chat network.
	 * @param connection
	 * @param nickname
	 */
	public void clientConnect(ClientConnection clientConnection, String nickname) {
		boolean free = true;
		for(ClientConnection c : clientConnections) {
			if(c.getNickname() == nickname) {
				free = false;
			}
		}
		if(free) {
			clientConnection.setConnected(true);
			clientConnections.add(clientConnection);
			System.out.println("Connection accepted");
			clientConnection.send(ProtocolDB.ACCEPTED_COMMAND, null);
		} else {
			clientConnection.setConnected(false);
			System.out.println("Connection denied");
			clientConnection.send(ProtocolDB.REJECTED_COMMAND, null);
		}
	}
	

	/**
	 * removes a client connection
	 * @param clientConnection
	 */
	public void clientDisconnect(ClientConnection clientConnection) {	
		clientConnections.remove(clientConnection);
	}


	/**
	 * Broadcast the message from the given client connection to all other clients. 
	 * @param clientConnection
	 * @param messageText
	 */
	public void clientMessage(ClientConnection clientConnection, String messageText) {
		String[] arguments = new String[2];
		arguments[0] = messageText;
		arguments[1] = clientConnection.getNickname();
		clientConnection.send(ProtocolDB.SERVERMESSAGE_COMMAND, arguments);
	}

	
	/*************
	 * VARIABLES *
	 *************/

	private Vector<ClientConnection> clientConnections;	
}