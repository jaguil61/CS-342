/* Jose M. Aguilar (jaguil61)
 * Project 3
 * 
 * This will play the game based who you will bet on and how much you're betting on
 * 
 * BaccaratGame.java
 */

import java.util.ArrayList;

public class BaccaratGame {

	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private double currentBet;
	private double totalWinnings;
	
	private String betOn;
	private String winner;
	
	BaccaratGame() // constructor
	{
		playerHand = new ArrayList<Card>();
		bankerHand = new ArrayList<Card>();
		theDealer = new BaccaratDealer();
		currentBet = 0;
		totalWinnings = 0;
		betOn = "";
		winner = "";
	}
	
	// this method starts a game by placing a bet and choosing who to bet on
	public void placeBet(String bettingOn, double theBet)
	{
		betOn = bettingOn;
		currentBet = theBet;
		
		// start the game by generating a deck and shuffling it
		theDealer.shuffleDeck();
		
		// clear the player/bankers hands
		playerHand.clear();
		bankerHand.clear();
				
		// deal the cards to the player and the banker
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();
		
		// figure out if the player should draw another card
		Card thirdCard = null; // if the player drew a third card it'll get stored here
		
		if (BaccaratGameLogic.evaluatePlayerDraw(playerHand)) // draw another card
		{
			thirdCard = theDealer.drawOne();
			playerHand.add(thirdCard);
		}
		
		// else don't do anything if false
		
		// figure out if the banker should draw another card
		if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, thirdCard)) // draw another card
			bankerHand.add(theDealer.drawOne());
		
		// else don't do anything if false
		
		// determine the winner
		winner = BaccaratGameLogic.whoWon(playerHand, bankerHand);
		
	}
	
	/* This method will determine if the user won or lost 
	 * their bet and return the amount won or lost 
	 * based on the value in currentBet. (The users profits)
	 */
	public double evaluateWinnings()
	{
		double profit = 0; 
		
		
		// Player wins: all player bets are 1:1 
		if (betOn == winner && winner == "Player")
			profit += currentBet;
		
		
		// Banker wins: all bank bets are 1:1 (bank stores 5% of players winnings)
		else if (betOn == winner && winner == "Banker")
		{
			double calcBet = currentBet - (currentBet * 0.05); // don't change currentBet
			
			profit += calcBet;
		}
			
		// Tie: all tie bets are 8:1
		else if (betOn == winner && winner == "Draw")
			profit += currentBet * 8;
		
		// the client lost
		else
			profit -= currentBet;
		
		totalWinnings = currentBet + profit; // update total winnings
		
		return profit;
	}
	
	// this method will return the players final hand
	public ArrayList<Card> getPlayerHand()
	{
		return playerHand;
	}
	
	// this method will return the bankers final hand
	public ArrayList<Card> getBankerHand()
	{
		return bankerHand;
	}
	
	// this method returns the clients total winnings
	public double getTotalWinnings()
	{
		return totalWinnings;
	}
	
	// this method returns the winner of the one round
	public String getWinner()
	{
		return winner;
	}
}
