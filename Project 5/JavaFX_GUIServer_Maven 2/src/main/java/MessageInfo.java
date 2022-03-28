/* Project 5 - GUI Server Improvement
 * Jose M. Aguilar (jaguil61)
 *
 * This is the information that the server is sending to the clients
 * 
 * MessageInfo.java
 */
import java.io.Serializable;
import java.util.ArrayList;


public class MessageInfo implements Serializable{

	// added by compiler
	private static final long serialVersionUID = 1L;
	
	private String message;
	private int clientNum; // the client that sent the message
	private ArrayList<Integer> clientsOnline = new ArrayList<Integer>(); // the clients that are currently online
	
	public MessageInfo(String theMessage, int theClientNum, ArrayList<Integer> theClientsOnline)
	{
		message = theMessage;
		clientNum = theClientNum;
		clientsOnline = theClientsOnline; 
	}
	

	// this method will return the message that was saved
	public String getMessage()
	{
		return message;
	}
	
	// this method will return the number of the client sent the message
	public int getClientNum()
	{
		return clientNum;
	}
	
	// this method will return the arraylist of the clients that are online
	public ArrayList<Integer> getList()
	{
		return clientsOnline;
	}
}
