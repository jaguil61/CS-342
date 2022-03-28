/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * Will test the BaccaratGameLogic class
 * 
 * BaccaratGameLogicTest.java
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BaccaratGameLogicTest {
	
	@Test
	void handTotalTest1()
	{
		ArrayList<Card> theHand = new ArrayList<>();
		Card theCard1 = new Card("Hearts", 9);
		Card theCard2 = new Card("Spades", 6);
		
		theHand.add(theCard1);
		theHand.add(theCard2);
		
		int theTotal = BaccaratGameLogic.handTotal(theHand);
		
		// the total should be 5 because 9+6=15 and after we drop the 1 it's 5
		assertEquals(5, theTotal, "Wrong Total!");
	}
	
	@Test
	void handTotalTest2()
	{
		ArrayList<Card> theHand = new ArrayList<>();
		Card theCard1 = new Card("Clubs", 3);
		Card theCard2 = new Card("Spades", 6);
		
		theHand.add(theCard1);
		theHand.add(theCard2);
		
		int theTotal = BaccaratGameLogic.handTotal(theHand);
		
		// the total should be 9 because 3+6=9 
		assertEquals(9, theTotal, "Wrong Total!");
	}
	
	@Test
	void evaluatePlayerDrawTest1()
	{
		ArrayList<Card> theHand = new ArrayList<>();
		Card theCard1 = new Card("Spades", 3);
		Card theCard2 = new Card("Diamonds", 6);
		
		theHand.add(theCard1);
		theHand.add(theCard2);
		
		// should return false because the players total is greater than 5
		assertFalse(BaccaratGameLogic.evaluatePlayerDraw(theHand), "Wrong Return!");
	}
	
	@Test
	void evaluatePlayerDrawTest2()
	{
		ArrayList<Card> theHand = new ArrayList<>();
		Card theCard1 = new Card("Spades", 2);
		Card theCard2 = new Card("Ace of Spades", 1);
		
		theHand.add(theCard1);
		theHand.add(theCard2);
		
		// should return true because the players total is less than 5
		assertTrue(BaccaratGameLogic.evaluatePlayerDraw(theHand), "Wrong Return!");
	}
	
	@Test
	void evaluatePlayerDrawTest3()
	{
		ArrayList<Card> theHand = new ArrayList<>();
		Card theCard1 = new Card("Hearts", 4);
		Card theCard2 = new Card("Ace of Spades", 1);
		
		theHand.add(theCard1);
		theHand.add(theCard2);
		
		// should return true because the players total is 5
		assertTrue(BaccaratGameLogic.evaluatePlayerDraw(theHand), "Wrong Return!");
	}
	
	@Test
	void evaluatePlayerDrawTest4()
	{
		ArrayList<Card> theHand = new ArrayList<>();
		Card theCard1 = new Card("Clubs", 4);
		Card theCard2 = new Card("Diamonds", 5);
		
		theHand.add(theCard1);
		theHand.add(theCard2);
		
		// should return false because the players total is 9
		assertFalse(BaccaratGameLogic.evaluatePlayerDraw(theHand), "Wrong Return!");
	}
	
	@Test
	void evaluateBankerDrawTest1()
	{
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Hearts", 2);
		Card bankCard2 = new Card("Ace of Spades", 1);
		Card playerCard = null; // player didn't draw a third card
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		
		/* banker should draw a third card because their total is less than 5
		 * and the player didn't draw a third card 
		 */
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(bankHand, playerCard), "Banker Didn't Draw!");
	}
	
	@Test
	void evaluateBankerDrawTest2()
	{
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Spades", 2);
		Card bankCard2 = new Card("Clubs", 4);
		Card playerCard = null; // player didn't draw a third card
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		
		/* banker shouldn't draw a third card because their total is 6
		 * and the player didn't draw a third card 
		 */
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(bankHand, playerCard), "Banker Drew!");
	}
	
	@Test
	void evaluateBankerDrawTest3()
	{
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Clubs", 2);
		Card bankCard2 = new Card("Ace of Spades", 1);
		Card playerCard = new Card("Diamonds", 3);
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		
		/* banker should draw a third card because their total is 3
		 * and the player drew a third card with a value that wasn't 8
		 */
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(bankHand, playerCard), "Banker Didn't Draw!");
	}
	
	@Test
	void evaluateBankerDrawTest4()
	{
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Spades", 3);
		Card bankCard2 = new Card("Diamonds", 2);
		Card playerCard = new Card("Hearts", 3);
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		
		/* banker shouldn't draw a third card because their total is 6
		 * and the player drew a third card with a value that was 3
		 */
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(bankHand, playerCard), "Banker Drew!");
	}
	
	@Test
	void evaluateBankerDrawTest5()
	{
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Spades", 2);
		Card bankCard2 = new Card("Hearts", 2);
		Card playerCard = new Card("Hearts", 3);
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		
		/* banker should draw a third card because their total is 4
		 * and the player drew a third card with a value that was 3
		 */
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(bankHand, playerCard), "Banker Didn't Draw!");
	}
	
	@Test
	void evaluateBankerDrawTest6()
	{
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Diamonds", 2);
		Card bankCard2 = new Card("Spades", 3);
		Card playerCard = new Card("Hearts", 5);
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		
		/* banker should draw a third card because their total is 5
		 * and the player drew a third card with a value that was 5
		 */
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(bankHand, playerCard), "Banker Didn't Draw!");
	}
	
	@Test
	void whoWonTest1()
	{
		ArrayList<Card> playerHand = new ArrayList<>();
		Card playerCard1 = new Card("Diamonds", 4);
		Card playerCard2 = new Card("Hearts", 2);
		
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Spades", 3);
		Card bankCard2 = new Card("Clubs", 3);
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		
		// the player and the banker both have a total of an 6 so it's a tie
		assertEquals("Draw", BaccaratGameLogic.whoWon(playerHand, bankHand), "Wasn't a Tie!");
	}
	
	@Test
	void whoWonTest2()
	{
		ArrayList<Card> playerHand = new ArrayList<>();
		Card playerCard1 = new Card("Diamonds", 0); // 10 of Diamonds
		Card playerCard2 = new Card("King of Hearts", 0);
		Card playerCard3 = new Card("Spades", 7);
		
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		playerHand.add(playerCard3);
		
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Spades", 0); // 10 of Spades
		Card bankCard2 = new Card("Kind of Diamonds", 0);
		Card bankCard3 = new Card("Ace of Spades", 1);
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		bankHand.add(bankCard3);
		
		// playerTotal = 7 | bankerTotal = 1 | player wins
		assertEquals("Player", BaccaratGameLogic.whoWon(playerHand, bankHand), "Player Didn't Win!");
	}
	
	@Test
	void whoWonTest3()
	{
		ArrayList<Card> playerHand = new ArrayList<>();
		Card playerCard1 = new Card("Diamonds", 7); 
		Card playerCard2 = new Card("Hearts", 4);
		Card playerCard3 = new Card("Jack of Spades", 0);
		
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		playerHand.add(playerCard3);
		
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Queen of Spades", 0); 
		Card bankCard2 = new Card("Kind of Diamonds", 0);
		Card bankCard3 = new Card("Spades", 4);
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		bankHand.add(bankCard3);
		
		// playerTotal = 1 | bankerTotal = 4 | banker wins
		assertEquals("Banker", BaccaratGameLogic.whoWon(playerHand, bankHand), "Banker Didn't Win!");
	}
	
	@Test
	void whoWonTest4()
	{
		ArrayList<Card> playerHand = new ArrayList<>();
		Card playerCard1 = new Card("Diamonds", 4); 
		Card playerCard2 = new Card("Hearts", 4);
		
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		
		ArrayList<Card> bankHand = new ArrayList<>();
		Card bankCard1 = new Card("Queen of Spades", 0); 
		Card bankCard2 = new Card("Kind of Diamonds", 0);
		Card bankCard3 = new Card("Spades", 4);
		
		bankHand.add(bankCard1);
		bankHand.add(bankCard2);	
		bankHand.add(bankCard3);
		
		// playerTotal = natural 8 | bankerTotal = 4 | Player wins
		assertEquals("Player", BaccaratGameLogic.whoWon(playerHand, bankHand), "Player Didn't Win!");
	}
}
