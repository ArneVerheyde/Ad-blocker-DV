import java.io.*; 
import java.net.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class ChatClient {
	
	public static void main(String argv[]) throws Exception    { 
		
//		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in)); 
//		
//		Socket clientSocket = new Socket("localhost", 6789); 
//		DataOutputStream outToServer = new DataOutputStream (clientSocket.getOutputStream()); 
//		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
//		String sentence = inFromUser.readLine(); 
//		outToServer.writeBytes(sentence + '\n'); 
//		String modifiedSentence = inFromServer.readLine(); 
//		System.out.println("FROM SERVER: " + modifiedSentence); 
//		
//		clientSocket.close(); 
//	} 

        if (argv.length != 3) {
            System.err.println(
                "Usage: java ChatClient <HTTPCommand> <URI> <Port>");
            System.exit(1);
        }

        // HTTPCommand refers to HEAD, GET, PUT or POST
        
        String HTTPCommand = argv[0];
        String URI = argv[1];
  
        int portNumber = Integer.parseInt(argv[2]);

        try (
            Socket kkSocket = new Socket(URI, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            
            String fromServer = in.readLine();
            System.out.println("Server: " + fromServer);

            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + URI);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                URI);
            System.exit(1);
        }
    }
	
	public boolean executeCommand() throws IOException {
		ZonedDateTime lastmodified = getFileLastModified(getPath());
		sendRequest(lastmodified);
		return processResponse();
	}
	
	private ZonedDateTime getFileLastModified(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean processResponse() {
		// TODO Auto-generated method stub
		return false;
	}

	private void sendRequest(ZonedDateTime modifiedSince) {
		PrintWriter writer = new PrintWriter(getWriter());
		writer.print("GET " + getPath() + " HTTP/1.1\r\n");
		writer.print("Host: " + getHost() + "\r\n");
		if (modifiedSince != null)
			writer.print("If-Modified-Since: " + modifiedSince.format(DateTimeFormatter.RFC_1123_DATE_TIME));
		writer.print("\r\n");
		writer.flush();
		
	}

	private String getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	private Writer getWriter() {
		// TODO Auto-generated method stub
		return null;
	}
		

}
