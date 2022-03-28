/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * This is what decides what goes on in the game. Decides all the rules.
 * 
 * BaccaratGameLogic.java
 */

import java.util.ArrayList;

public class BaccaratGameLogic {
	
	/* This method will evaluate two hands at the end of 
	 * the game and return a string depending on the 
	 * winner: “Player”, “Banker”, “Draw”. 
	 * (hand total's should never be greater than 9)
	 */
	public static String whoWon(ArrayList<Card> hand1, ArrayList<Card>hand2)
	{
		String winner = "";
		int hand1Total = handTotal(hand1); // player's total
		int hand2Total = handTotal(hand2); // banker's total
		
		// both hands had the same total so it's a tie
		if (hand1Total == hand2Total)
			winner = "Draw";
		
		// hand1 (player) got a natural 9/8 so they automatically win
		else if (hand1Total == 9 || hand1Total == 8)
			winner = "Player";
		
		// hand2 (banker) got a natural 9/8 so they automatically win
		else if (hand2Total == 9 || hand2Total == 8)
			winner = "Banker";
		
		// the player had a higher number meaning they were closer to 9 
		else if (hand1Total > hand2Total)
			winner = "Player";
		
		// the banker must have had a larger total meaning they were closer to 9
		else 
			winner = "Banker";
		
		return winner;
	}
	
	/* This method will take a hand and return how many 
	 * points that hand is worth.
	 */
	public static int handTotal(ArrayList<Card> hand)
	{
		int theTotal = 0;
		
		// add up the hand
		for (Card i: hand)
		{
			theTotal+=i.getValue();
		}
		
		// subtract 10 if the total is greater than 9
		if (theTotal > 9)
			theTotal-=10;
		
		return theTotal;
	}
	
	/* This method will return true if the banker should 
	 * be dealt a third card, otherwise return false.
	 */
	public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard)
	{
		boolean drawOne = false;
		int theTotal = handTotal(hand);
		
		// The banker must stand if their total was greater or equal to 7 
		if (theTotal >= 7)
			drawOne = false;
				
		// "[If the player stood then] the banker draws a third card with hands 0–5" - https://en.wikipedia.org/wiki/Baccarat_(card_game)
		else if (playerCard == null && theTotal <= 5)
			drawOne = true;
		
		// The player drew a third card and the banker didn't get a 7 or higher 
		else if (playerCard != null && theTotal < 7)
			drawOne = bankerDrawHelper(theTotal, playerCard.getValue());
		
		// The player stood and but the dealer got a 6 or higher 
		else 
			drawOne = false;
		
		return drawOne;
	}
	
	/* This method will return true if the player should 
	 * be dealt a third card, otherwise return false.
	 */
	public static boolean evaluatePlayerDraw(ArrayList<Card> hand)
	{
		boolean drawOne = false;
		int theTotal = handTotal(hand);
		
		// the player only draws if they're total is 0-5
		if (theTotal <= 5)
			drawOne = true;
		
		// the player got a value greater than 5
		else
			drawOne = false;
		
		return drawOne;
	}
	
	/* This helper method will determine if the banker 
	 * should draw since the player has a third card
	 */
	private static boolean bankerDrawHelper(int dealerTotal, int playerCardVal)
	{
		boolean drawOne = false;
		
		/* "If the banker total is 2 or less, then the banker
		 * draws a card, regardless of what the player's 
		 * third card is." - Wikipedia
		 */
		if (dealerTotal <= 2)
			drawOne = true;
		
		/* "If the banker total is 3, then the banker draws a 
		 * third card unless the player's third card was an 8." - Wikipedia
		 */
		else if (dealerTotal == 3 && playerCardVal != 8)
			drawOne = true;
		
		/* "If the banker total is 4, then the banker draws a 
		 * third card if the player's third card was 
		 * 2, 3, 4, 5, 6, 7." - Wikipedia 
		 */
		else if (dealerTotal == 4 && (playerCardVal >= 2 && playerCardVal <= 7))
			drawOne = true;
		
		/* "If the banker total is 5, then the banker draws a 
		 * third card if the player's third card was 
		 * 4, 5, 6, or 7." - Wikipedia
		 */
		else if (dealerTotal == 5 && (playerCardVal >= 4 && playerCardVal <= 7))
			drawOne = true;
		
		/* "If the banker total is 6, then the banker draws a 
		 * third card if the player's third card was a 
		 * 6 or 7." - Wikipedia
		 */
		else if (dealerTotal == 6 && (playerCardVal == 6 || playerCardVal == 7))
			drawOne = true;
		
		/* "[Else] the banker total is 7 [or higher], then the banker stands." - Wikipedia
		 * or non of the other conditions weren't met. Such as the banker had a total of 
		 * 6 but the player's card had a value of 3
		 */
		else 
			drawOne = false;
		
		return drawOne;
	}
	
	
}
