
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.io.*;

public class HTTP_Response {

    public String processInput(String theInput) {
        String theOutput = "HTTP/1.1 200 OK\r\n\r\n" + getServerTime();
        
        
        return theOutput;
        
    }
    
	String getServerTime() {
	    Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat(
	        "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
	    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	    return dateFormat.format(calendar.getTime());
	}
	
//	String getContentType(String type, String subType) {
//		return type + '/' + subType;
//	}
	
	String getContentLength() {
		return null;
	}
	
	String getChunckedEncoding() {
		return null;
	}
    

	
}
