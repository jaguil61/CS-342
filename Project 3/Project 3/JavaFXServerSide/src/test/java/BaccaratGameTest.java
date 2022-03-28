/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * Will test the BaccaratGame class
 * 
 * BaccaratGameTest.java
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BaccaratGameTest {
	
	BaccaratGame playGame;
	
	@BeforeEach
	void setup()
	{
		playGame = new BaccaratGame();
	}
	
	@Test
	void evaluateWinningsTest1() 
	{
		playGame.placeBet("Draw", 1000);
		
		if (playGame.getWinner() == "Draw") // winnings are 8:1
		{
			assertEquals(8000, playGame.evaluateWinnings(), "Incorrect Draw Winnings!");
			assertEquals(9000, playGame.getTotalWinnings(), "Incorrect Total Winnings");
		}
		
		else // they lost money
		{
			assertEquals(-1000, playGame.evaluateWinnings(), "Incorrect Loss Winnings!");
			assertEquals(0, playGame.getTotalWinnings(), "Incorrect Total Loss Winnings");
		}
	}
	
	@Test
	void evaluateWinningsTest2() 
	{
		playGame.placeBet("Player", 100);
		
		if (playGame.getWinner() == "Player") // winnings are 1:1
		{
			assertEquals(100, playGame.evaluateWinnings(), "Incorrect Player Winnings!");
			assertEquals(200, playGame.getTotalWinnings(), "Incorrect Total Winnings");
		}
		
		else // they lost money
		{
			assertEquals(-100, playGame.evaluateWinnings(), "Incorrect Loss Winnings!");
			assertEquals(0, playGame.getTotalWinnings(), "Incorrect Total Loss Winnings");
		}
	}
	
	@Test
	void evaluateWinningsTest3() 
	{
		playGame.placeBet("Banker", 10);
		
		if (playGame.getWinner() == "Banker") // winnings are 1:1 (bank stores 5% of players winnings)
		{
			assertEquals(9.5, playGame.evaluateWinnings(), "Incorrect Banker Winnings!");
			assertEquals(19.5, playGame.getTotalWinnings(), "Incorrect Total Winnings");
		}
		
		else // they lost money
		{
			assertEquals(-10, playGame.evaluateWinnings(), "Incorrect Loss Winnings!");
			assertEquals(0, playGame.getTotalWinnings(), "Incorrect Total Loss Winnings");
		}
	}
	
	
	@Test
	void placeBetTest1()
	{
		playGame.placeBet("Player", 100);
		playGame.placeBet("Banker", 1000);
		playGame.placeBet("Draw", 10);
		
		// player hand should only have a size of 2 or 3
		assertTrue(2 <= playGame.getPlayerHand().size(), "Too Many Cards!");
		assertTrue(3 >= playGame.getPlayerHand().size(), "Too Many Cards!");
	}
	
	@Test
	void placeBetTest2()
	{
		playGame.placeBet("Player", 96);
		playGame.placeBet("Banker", 200);
		playGame.placeBet("Draw", 117);
		
		// banker hand should only have a size of 2 or 3
		assertTrue(2 <= playGame.getBankerHand().size(), "Too Many Cards!");
		assertTrue(3 >= playGame.getBankerHand().size(), "Too Many Cards!");
	}
}
