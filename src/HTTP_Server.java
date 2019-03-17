
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;


import java.io.*;

public class HTTP_Server {
	
	public static void main(String[] args) throws IOException {
	       
		
	    // have to create HTTP_Server instance with one argument    
		if (args.length != 1) {
			System.err.println("Usage: java HTTP_Server <port number>");
	        System.exit(1);
	       }
		
		// create serverSocket with given portNumber
		int portNumber = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(portNumber);

        // start accepting connections
	    while (true) {
		     try ( 
		          Socket clientSocket = serverSocket.accept();
		          PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);	            
		    	
		    		 BufferedReader in = new BufferedReader(
		                new InputStreamReader(clientSocket.getInputStream()));
		        ) {
		        
		            String inputLine, outputLine;
		            
		            // Initiate conversation with client
		            HTTP_Response response = new HTTP_Response();
		            
		            inputLine = in.readLine();
		            while (!inputLine.isEmpty()) {
		            	outputLine = response.processInput(inputLine);
		            	out.println(outputLine);
		            	inputLine = in.readLine();     	
		            }
 
	
	
		        } catch (IOException e) {
		            System.out.println("Exception caught when trying to listen on port "
		                + portNumber + " or listening for a connection");
		            System.out.println(e.getMessage());
		        }
		       
	       }
	    }
	
	
	
	

	
	
	
}




