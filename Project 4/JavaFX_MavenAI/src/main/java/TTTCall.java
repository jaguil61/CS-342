// Handles the logic of the Tic Tac Toe game

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;

public class TTTCall implements Callable<Integer>{
	String[] board;
	String turn;
	String mode;
	private ArrayList<Node> bestMoves; // stores the best moves
	
	TTTCall(String[] theBoard, String theTurn, String theMode){ // constructor
		board = theBoard;
		turn = theTurn; // X or O
		mode = theMode;
		bestMoves = new ArrayList<>();
	}
	

	@Override
	public Integer call() throws Exception {
		
		Integer val = -1;
		
		//System.out.println("\nTurn: " + turn + "\nMode: " + mode); // for debugging purposes
		
		if (mode.equals("Novice"))
			val = noviceMode(val);
		
		else if (mode.equals("Advanced"))
			val = advancedMode(val);
		
		else if (mode.equals("Expert"))
			val = expertMode(val);
		
		else
			System.out.println("Method Not Called!"); // for debugging purposes
		
		return val;
	}

	
	
	/* This helper method will choose a random move to return based on the array passed into it
	 * This is so the first best move isn't always chosen
	 */
	public static Integer randMove(ArrayList<Node> theMovesList)
	{
		Random rand = new Random();
		
		Integer ranIndex = rand.nextInt(theMovesList.size()); // get a random index from the bestMoves array
		
		Node node = theMovesList.get(ranIndex); // get the value at that index
		
		Integer move = node.getMovedTo() - 1; // subtract one for correct move
		
		//System.out.println("\nRandom Best Move: " + move);
		
		return move;
	}
	
	// chooses the best win moves for X from the moves list
	private void chooseWinX(ArrayList<Node> theMovesList)
	{
		for (Node i : theMovesList)	
		{
			if (i.getMinMax() == 10) // will choose an optimal move for X
				{
					bestMoves.add(i);
					//System.out.println("\nMove added for: " + turn);
				}
			
			// else do nothing
		}
	}
	
	// chooses moves that results in a tie for
	private void chooseTie(ArrayList<Node> theMovesList)
	{
		for (Node i : theMovesList)	
		{
			if (i.getMinMax() == 0) // will choose an optimal move for X
				{
					bestMoves.add(i);
					//System.out.println("\nMove added for: " + turn);
				}
			
			// else do nothing
		}
	}
	
	// chooses the best win moves for O from the moves list
	private void chooseWinO(ArrayList<Node> theMovesList)
	{
		for (Node i : theMovesList)	
		{
			if (i.getMinMax() == -10) // will choose an optimal move for O
				{
					bestMoves.add(i); // subtract 1 for correct index
					//System.out.println("\nMove added for: " + turn);
				}
			
			// else do nothing
		}
	}
	
	// this method is for expert mode
	private int expertMode(int val)
	{
		//System.out.println("Expert Method Called!"); // for debugging purposes
		
		MinMax sendIn_InitState = new MinMax(board);
		
		ArrayList<Node> movesList = sendIn_InitState.findMoves();
		
		if (movesList.isEmpty()) // board is full
		{
			//System.out.println("***No Possible Moves***"); // for debugging purposes
			val = -1;
		}
		
		// check for win moves
		// Choose the best moves
		else 
		{
			if (turn.equals("X"))
				chooseWinX(movesList);
			
			else
				chooseWinO(movesList);
		}
		
		
		// no win moves were found so find moves to block opposite opponent or tie them
		if (bestMoves.isEmpty()) 
		{
			if (turn.equals("X"))
				chooseWinO(movesList);
			
			else
				chooseWinX(movesList);
			
			chooseTie(movesList);
		}
		
		
		val = randMove(bestMoves); 
		
		
		//printPossibleBestMoves();
		//printBestMoves(movesList);
		
		return val;
	}
	
	// this method is for the advanced mode
	private int advancedMode(int val)
	{
		//System.out.println("Advanced Method Called!"); // for debugging purposes
		
		MinMax sendIn_InitState = new MinMax(board);
		
		ArrayList<Node> movesList = sendIn_InitState.findMoves();
		
		if (movesList.isEmpty()) // board is full
		{
			//System.out.println("***No Possible Moves***"); // for debugging purposes
			val = -1;
		}
		
		// Choose the best moves
		else 
		{
			if (turn.equals("X"))
				chooseWinX(movesList);
			
			else
				chooseWinO(movesList);
			
			chooseTie(movesList); // also choose tie moves
		}
		
		// no win/tie moves were chosen for O or X even though there were possible moves
		if (bestMoves.isEmpty() && !movesList.isEmpty()) 
		{
			//printPossibleBestMoves();
			//printBestMoves(movesList);
			
			val = randMove(movesList); // choose a random move from the possible moves
		}
		
		// bestMoves.isEmpty() isn't empty
		else
		{
			//printPossibleBestMoves();
			//printBestMoves(movesList);
			
			val = randMove(bestMoves); // choose a random best move
		}
		
		return val;
	}
	
	/* This method is for the novice mode
	 * Only chooses random moves based on the board
	 */
	private int noviceMode(int val)
	{
		//System.out.println("Novice Method Called!"); // for debugging purposes
		
		MinMax sendIn_InitState = new MinMax(board);
		
		ArrayList<Node> movesList = sendIn_InitState.findMoves();
		
		if (movesList.isEmpty()) // board is full
		{
			//System.out.println("***No Possible Moves***"); // for debugging purposes
			val = -1;
		}
		
		else
			val = randMove(movesList);
		
		//printBestMoves(movesList);
		
		return val;
	}
	
	// only for debugging purposes
	private void printPossibleBestMoves()
	{
		System.out.println("\nTurn: " + turn);
		System.out.print("\nBest Possible Moves For " + turn + ": < ");
		for(Node i: bestMoves)
		{
			System.out.print((i.getMovedTo() - 1) + " ");
		}
		
		System.out.print(" >");
	}
	
	// only used for debugging purposes
	private void printBestMoves(ArrayList<Node> movesList)
	{
		System.out.print("\n\nThe moves list for " + turn + " is: < ");
		
		for(int x = 0; x < movesList.size(); x++)
		{
			Node temp = movesList.get(x);
			
			if(temp.getMinMax() == 10 || temp.getMinMax() == 0)
			{
				System.out.print((temp.getMovedTo() - 1) + " ");
			}
		}
		
		System.out.print(">");
	}
}
