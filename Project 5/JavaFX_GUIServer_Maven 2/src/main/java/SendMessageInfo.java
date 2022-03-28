/* Project 5 - GUI Server Improvement
 * Jose M. Aguilar (jaguil61)
 *
 * This is the information that the client is sending to the server
 * 
 * SendMessageInfo.java
 */

import java.io.Serializable;

public class SendMessageInfo implements Serializable{

	// added by compiler
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String toClient; // the client that is meant to receive the message
	
	public SendMessageInfo(String theMessage, String theToClient)
	{
		message = theMessage;
		toClient = theToClient;
	}
	
	// this method will return the message that was sent
	public String getMessage()
	{
		return message;
	}
	
	// this method will return the name of the client that is supposed to receive the message
	public String getToClient()
	{
		return toClient;
	}
}
