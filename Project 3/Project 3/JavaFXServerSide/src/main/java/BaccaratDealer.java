/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * This handles the generation/shuffling of the deck and the passing of cards
 * 
 * BaccaratDealer.java
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BaccaratDealer {
	
	private ArrayList<Card> deck;
	
	BaccaratDealer() // constructor
	{
		deck = new ArrayList<Card>();
	}
	
	/* This method creates a standard 52 card deck
	 * 13 clubs, diamonds, hearts, and spades
	 * 10's and face cards have a value of 0
	 */
	public void generateDeck()
	{
		deck.clear(); // make a fresh deck ArrayList just in case it's full already
		
		// generate the clubs
		generateSuite(deck, "Clubs");
		
		// generate the diamonds
		generateSuite(deck, "Diamonds");
		
		// generate the hearts
		generateSuite(deck, "Hearts");
		
		// generate the spades
		generateSuite(deck, "Spades");
	}
	
	/* This method will deal two cards
	 * and return them in an ArrayList<Card>
	 */
	public ArrayList<Card> dealHand()
	{
		ArrayList<Card> theHand = new ArrayList<Card>();
		
		for (int i = 0; i < 2; i++) 
		{
			int rand = genRandNum(); // generate random number to choose an index
			
			Card randCard = deck.get(rand); // get a random card from the deck
			
			theHand.add(randCard); 
			
			deck.remove(rand); // remove that card from the original deck so that it can't be chosen again
		}
		
		return theHand;
	}
	
	// This method  will deal a single card and return it.
	public Card drawOne()
	{
		int rand = genRandNum(); // generate random number to choose an index
		
		Card randCard = deck.get(rand); // get a random card from the deck
		
		deck.remove(rand); // remove that card from the original deck so that it can't be chosen again
		
		return randCard;
	}
	
	/* shuffleDeck will create a new deck of 52 cards and “shuffle”; 
	 * randomize the cards in that ArrayList<Card>.
	 */
	public void shuffleDeck()
	{
		// generate new deck
		generateDeck();
		
		// shuffle the deck
		Collections.shuffle(deck); // Collections.shuffle is used to shuffle lists
	}
	
	// This method will just return how many cards are in this deck at any given time.
	public int deckSize()
	{
		return deck.size();
	}
	
	// This helper method will generate the suites based on the string passed into it and add them to the deck
	private void generateSuite(ArrayList<Card> theDeck, String theSuite)
	{
		Card theCard = null;
		
		for (int i = 1; i < 14; i++)
		{
			switch (i)
			{
			case 1: 
				theCard = new Card("Ace of " + theSuite, i); 
				theDeck.add(theCard);
				break;
			case 10: 
				theCard = new Card(theSuite, 0); 
				theDeck.add(theCard);
				break;
			case 11:
				theCard = new Card("Jack of " + theSuite, 0); 
				theDeck.add(theCard);
				break;
			case 12:
				theCard = new Card("Queen of " + theSuite, 0); 
				theDeck.add(theCard);
				break;
			case 13:
				theCard = new Card("King of " + theSuite, 0); 
				theDeck.add(theCard);
				break;
			default:
				theCard = new Card(theSuite, i); 
				theDeck.add(theCard);
			}
		}
	}
	
	// This helper method will generate a random number from 1 and (deckSize() - 1)
	private int genRandNum()
	{
		Random rand = new Random();
		
		return rand.nextInt(deckSize());
	}
	
	// for debugging purposes (delete later)
	public ArrayList<Card> getDeck()
	{
		return deck;
	}
}
