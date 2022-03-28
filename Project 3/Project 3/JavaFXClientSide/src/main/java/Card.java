/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * A single instance of a card but also will assign a picture of the card based on it's suite and value. 
 * 
 * Card.java
 */

import javafx.scene.image.Image;

// Client Version
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
	
	// returns an image of the card
	public Image getImage(String suite, int value)
	{
		Image card = null;
		
		if (suite.contains("Clubs"))
			card = getClubs(suite, value);
		
		else if (suite.contains("Diamonds"))
			card = getDiamonds(suite, value);
		
		else if (suite.contains("Hearts"))
			card = getHearts(suite, value);
		
		else if (suite.contains("Spades"))
			card = getSpades(suite, value);
		
		else
			card = new Image("red_joker.png"); // default card
		
		return card;
	}
	
	// will return the clubs cards
	private Image getClubs(String suite, int value)
	{
		Image card = null; 
		
		if (value == 1)
			card = new Image("ace_of_clubs.png");
		
		else if (value == 2)
			card = new Image("2_of_clubs.png");
		
		else if (value == 3)
			card = new Image("3_of_clubs.png");
		
		else if (value == 4)
			card = new Image("4_of_clubs.png");
		
		else if (value == 5)
			card = new Image("5_of_clubs.png");
		
		else if (value == 6)
			card = new Image("6_of_clubs.png");
		
		else if (value == 7)
			card = new Image("7_of_clubs.png");
		
		else if (value == 8)
			card = new Image("8_of_clubs.png");
		
		else if (value == 9)
			card = new Image("9_of_clubs.png");
		
		else if (suite == "Clubs" && value == 0)
			card = new Image("10_of_clubs.png");
		
		else if (suite == "Jack of Clubs")
			card = new Image("jack_of_clubs.png");
		
		else if (suite == "King of Clubs")
			card = new Image("king_of_clubs.png");
		
		else if (suite == "Queen of Clubs")
			card = new Image("queen_of_clubs.png");
		
		else
			card = new Image("red_joker.png"); // default card
		
		return card;
	}
	
	// will return the diamonds cards
	private Image getDiamonds(String suite, int value)
	{
		Image card = null; 
		
		if (value == 1)
			card = new Image("ace_of_diamonds.png");
		
		else if (value == 2)
			card = new Image("2_of_diamonds.png");
		
		else if (value == 3)
			card = new Image("3_of_diamonds.png");
		
		else if (value == 4)
			card = new Image("4_of_diamonds.png");
		
		else if (value == 5)
			card = new Image("5_of_diamonds.png");
		
		else if (value == 6)
			card = new Image("6_of_diamonds.png");
		
		else if (value == 7)
			card = new Image("7_of_diamonds.png");
		
		else if (value == 8)
			card = new Image("8_of_diamonds.png");
		
		else if (value == 9)
			card = new Image("9_of_diamonds.png");
		
		else if (suite == "Diamonds" && value == 0)
			card = new Image("10_of_diamonds.png");
		
		else if (suite == "Jack of Diamonds")
			card = new Image("jack_of_diamonds.png");
		
		else if (suite == "King of Diamonds")
			card = new Image("king_of_diamonds.png");
		
		else if (suite == "Queen of Diamonds")
			card = new Image("queen_of_diamonds.png");
		
		else
			card = new Image("red_joker.png"); // default card
		
		return card;
	}

	// will return the hearts cards
	private Image getHearts(String suite, int value)
	{
		Image card = null; 
		
		if (value == 1)
			card = new Image("ace_of_hearts.png");
		
		else if (value == 2)
			card = new Image("2_of_hearts.png");
		
		else if (value == 3)
			card = new Image("3_of_hearts.png");
		
		else if (value == 4)
			card = new Image("4_of_hearts.png");
		
		else if (value == 5)
			card = new Image("5_of_hearts.png");
		
		else if (value == 6)
			card = new Image("6_of_hearts.png");
		
		else if (value == 7)
			card = new Image("7_of_hearts.png");
		
		else if (value == 8)
			card = new Image("8_of_hearts.png");
		
		else if (value == 9)
			card = new Image("9_of_hearts.png");
		
		else if (suite == "Hearts" && value == 0)
			card = new Image("10_of_hearts.png");
		
		else if (suite == "Jack of Hearts")
			card = new Image("jack_of_hearts.png");
		
		else if (suite == "King of Hearts")
			card = new Image("king_of_hearts.png");
		
		else if (suite == "Queen of Hearts")
			card = new Image("queen_of_hearts.png");
		
		else
			card = new Image("red_joker.png"); // default card
		
		return card;
	}
		
	// will return the spades cards
	private Image getSpades(String suite, int value)
	{
		Image card = null; 
		
		if (value == 1)
			card = new Image("ace_of_spades.png");
		
		else if (value == 2)
			card = new Image("2_of_spades.png");
		
		else if (value == 3)
			card = new Image("3_of_spades.png");
		
		else if (value == 4)
			card = new Image("4_of_spades.png");
		
		else if (value == 5)
			card = new Image("5_of_spades.png");
		
		else if (value == 6)
			card = new Image("6_of_spades.png");
		
		else if (value == 7)
			card = new Image("7_of_spades.png");
		
		else if (value == 8)
			card = new Image("8_of_spades.png");
		
		else if (value == 9)
			card = new Image("9_of_spades.png");
		
		else if (suite == "Spades" && value == 0)
			card = new Image("10_of_spades.png");
		
		else if (suite == "Jack of Spades")
			card = new Image("jack_of_spades.png");
		
		else if (suite == "King of Spades")
			card = new Image("king_of_spades.png");
		
		else if (suite == "Queen of Spades")
			card = new Image("queen_of_spades.png");
		
		else
			card = new Image("red_joker.png"); // default card
		
		return card;
	}
		
}

