/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * A single instance of a card
 * 
 * Card.java
 */

public class Card {
	
	private String suite;
	private int value;
	
	public Card(String theSuite, int theValue)
	{
		suite = theSuite;
		value = theValue;
	}
	
	// method that returns the suite of the card
	public String getSuite()
	{
		return suite;
	}
	
	// method that returns the value of the card
	public int getValue()
	{
		return value;
	}
}
