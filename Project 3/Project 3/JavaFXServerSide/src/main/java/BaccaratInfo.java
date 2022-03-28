/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * All the stuff that will be sent between the server and client program. 
 * 
 * BaccaratInfo.java
 */

import java.io.Serializable;
import java.util.ArrayList;

public class BaccaratInfo implements Serializable {

	/**
	 * Added by compiler
	 */
	private static final long serialVersionUID = 1L;
	
	private String type;
	
	public ArrayList<Card> playerHand = new ArrayList<>();
	public ArrayList<Card> bankerHand = new ArrayList<>();
	public String winner = "";
	public double winnings = 0;
	public double bet = 0;
	public String person = "";
	public boolean anotherGame = false;
	
	// Either "ServerInfo" or "ClientInfo"
	public BaccaratInfo(String theType)
	{
		type = theType;
	}
	
	// Will return the type of the information that was passed
	public String getType()
	{
		return type;
	}
}
