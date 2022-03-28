/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * Will test the BaccaratDealer class
 * 
 * BaccaratDealerTest.java
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BaccaratDealerTest {

	BaccaratDealer theDealer;
	
	@BeforeEach
	void setup() 
	{
		theDealer = new BaccaratDealer();
		theDealer.generateDeck();
	}
	
	@Test
	void generateDeckTest1() 
	{
		ArrayList<Card> theDeck = theDealer.getDeck();
		
		// generateDeck should have made a deck of 52 cards
		assertEquals(52, theDeck.size(), "Incorrect Size!");
	}
	
	@Test
	void generateDeckTest2()
	{
		theDealer.dealHand(); // remove two from deck
		
		theDealer.generateDeck(); // generates a new 52 card deck
		
		// the deck size should be 52 since a new one was generated
		assertEquals(52, theDealer.deckSize(), "Incorrect Size!");
	}
	
	@Test
	void dealHandTest1()
	{
		ArrayList<Card> theHand = theDealer.dealHand();
		
		// should generate a ArrayList the size of 2
		assertEquals(2, theHand.size(), "Incorrect Size!");
	}
	
	@Test
	void dealHandTest2()
	{
		theDealer.dealHand();
		
		// deck size should be 50 since two cards were taken out
		assertEquals(50, theDealer.deckSize(), "Incorrect Size!");
	}
	
	@Test 
	void drawOneTest1()
	{
		Card theCard = theDealer.drawOne();
		
		// the card drawn should have a value between 0 and 9
		assertTrue(0 <= theCard.getValue(), "Value is Below 0!");
		assertTrue(9 >= theCard.getValue(), "Value is Beyond 9!");
	}

	@Test 
	void drawOneTest2()
	{
		theDealer.drawOne();
		theDealer.drawOne();
		theDealer.drawOne();
		
		// deck size should be 49 since three cards were taken out
		assertEquals(49, theDealer.deckSize(), "Incorrect Size!");
	}
	
	@Test
	void shuffleDeckTest1()
	{
		theDealer.shuffleDeck();
		
		// the size of the deck should still be 52
		assertEquals(52, theDealer.deckSize(), "Incorrect Size!");
	}
	
	@Test
	void shuffleDeckTest2()
	{
		theDealer.dealHand();
		theDealer.drawOne();
		theDealer.shuffleDeck();
		
		// the size of the deck should still be 52
		assertEquals(52, theDealer.deckSize(), "Incorrect Size!");
		
		theDealer.dealHand();
		theDealer.drawOne();
		
		// the size should be 49 now since 3 cards were taken out
		assertEquals(49, theDealer.deckSize(), "Incorrect Size!");
	}
}
