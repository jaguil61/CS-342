/* Project 4 - Tic Tac Toe
 * Jose M. Aguilar (jaguil61)
 * 
 * This class stores are the logic behind the GUI (Action Events)
 * 
 * Controller.java
 */ 
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable{

	String difficultyX = ""; // the difficulty for X
	String difficultyO = ""; // the difficulty for O
	int numGames = 0; // number of games to play
	int aiXScore = 0; // AI X 
	int aiOScore = 0; // AI O
	boolean difChosenX = false; // Did the user choose the difficulty for ai X?
	boolean difChosenO = false; // Did the user choose the difficulty for ai O?
	boolean numGamesChosen = false; // Did the user choose how many games to play?
	
	
	@FXML
	private Button playButton;
	
	@FXML
	private Text topRow;
	
	@FXML
	private Text midRow;
	
	@FXML
	private Text bottomRow;
	
	@FXML
	private Text XScoreText;
	
	@FXML
	private Text OScoreText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	/* This helper method checks if the play button should be enabled or disabled
	 * Only enable if the number if the difficulty and number of games is chosen
	 */
	private void enableOrDisable()
	{
		if (difChosenX && difChosenO && numGamesChosen)
			playButton.setDisable(false); // enable
		
		else
			playButton.setDisable(true); // disable
	}
	
	// method that figures out what to do when the toggle buttons for the difficulty is selected
	public void difficultyXAction(ActionEvent event) throws IOException
	{
		ToggleButton togBut = (ToggleButton)event.getSource(); // grab the toggle button that was pressed
		
		if (togBut.isSelected())
		{
			difChosenX = true;
			difficultyX = togBut.getText();
			//System.out.println("Difficulty X: " + difficultyX); // For Debugging Purposes
		}
		
		else // toggle button isn't selected
		{
			difChosenX = false;
			difficultyX = "";
			//System.out.println("Difficulty X: " + difficultyX); // For Debugging Purposes
		}
		
		enableOrDisable();
	}
	
	// method that figures out what to do when the toggle buttons for the difficulty is selected
		public void difficultyOAction(ActionEvent event) throws IOException
		{
			ToggleButton togBut = (ToggleButton)event.getSource(); // grab the toggle button that was pressed
			
			if (togBut.isSelected())
			{
				difChosenO = true;
				difficultyO = togBut.getText();
				//System.out.println("Difficulty O: " + difficultyO); // For Debugging Purposes
			}
			
			else // toggle button isn't selected
			{
				difChosenO = false;
				difficultyO = "";
				//System.out.println("Difficulty O: " + difficultyO); // For Debugging Purposes
			}
			
			enableOrDisable();
		}
	
	// method that figures out what to do when the number of games to play is chosen
	public void numGamesAction(ActionEvent event) throws IOException
	{
		ToggleButton togBut = (ToggleButton)event.getSource(); // grab the toggle button that was pressed
		
		if (togBut.isSelected())
		{
			numGamesChosen = true;
			numGames = Integer.parseInt(togBut.getText());
			//System.out.println("Number Of Games: " + numGames); // For Debugging Purposes
		}
		
		else // toggle button isn't selected
		{
			numGamesChosen = false;
			numGames = 0;
			//System.out.println("Number Of Games: " + numGames); // For Debugging Purposes
		}
		
		enableOrDisable();
	}
	
	// method that starts the game
	public void playGameAction(ActionEvent event) throws IOException
	{
		// loop as many times as the user chose
		for (int i = 0; i < numGames; i++)
		{
			int onGame = i+1;
			//System.out.println("On Game: " + (onGame));
			
			Platform.runLater(()->chooseMove(onGame));
		}
	}
	
	// method that will open a new window with instructions on how to play the game
	public void howToAction(ActionEvent event) throws IOException
	{
		Stage howToStage = new Stage();
		
		howToStage.setTitle("How To Play");
		
		Parent root2 = FXMLLoader.load(getClass().getResource("/HowToPlayScreen.fxml"));
		
		Scene howToScene = new Scene(root2, 600, 400);
		
		howToStage.setScene(howToScene);
		howToStage.show();
	}
	
	// method that will close the game
	public void exitGameAction(ActionEvent event) throws IOException
	{
		Platform.exit();
	}
	
	// this method places the X or O in the spot decided by the MinMax algorithm. Also passes what game they're on
	private void chooseMove(int onGame)
	{
		String turn = whoGoesFirst();
		Integer index = -1;
		
		
		String[] board = {"b", "b", "b", "b", "b", "b", "b", "b", "b"};
		
		ExecutorService ex = Executors.newFixedThreadPool(5);
		
		for(int i = 0; i < 9; i++) 
		{
			Future<Integer> future;
			
			if (turn.equals("X"))
				future = ex.submit(new TTTCall(board, turn, difficultyX));
			
			else
				future = ex.submit(new TTTCall(board, turn, difficultyO));
				
			index = getIndex(future); // the spot X or O will mark
			
			if (index == -1) // something went wrong in the TTCall class and no move was chosen
				break;
			
			else
			{
				board[index] = turn;
				
				Platform.runLater(()->topRow.setText(board[0] + " " + board[1] + " " + board[2]));
				Platform.runLater(()->midRow.setText(board[3] + " " + board[4] + " " + board[5]));
				Platform.runLater(()->bottomRow.setText(board[6] + " " + board[7] + " " + board[8]));
				
				//printBoard(board);
			}
			
			if (didXWin(board))
			{
				//System.out.println("***X Won!***");
				aiXScore++;
				Platform.runLater(()->XScoreText.setText("AI X Score: " + aiXScore));
				Platform.runLater(()->winAlertX(onGame));
				
				break;
			}
			
			else if (didOWin(board))
			{
				//System.out.println("***O Won!***");
				aiOScore++;
				Platform.runLater(()->OScoreText.setText("AI O Score: " + aiOScore));
				Platform.runLater(()->winAlertO(onGame));
				break;
			}
			
			else
				Platform.runLater(()->tieAlert(onGame));
			
			
			if (turn.equals("X")) // change it to O's turn 
				turn = "O";
		
			else 
				turn = "X";
			
			
		}
		
		ex.shutdown();
		
		//System.out.println("\n***All Done!***");
	}
	
	
	// function that randomly chooses who goes first
	private String whoGoesFirst()
	{
		String[] theTurns = {"X", "O"}; // possible turns
		
		Random rand = new Random();
		
		Integer ranIndex = rand.nextInt(theTurns.length); // random number (0 or 1)
		
		String turn = theTurns[ranIndex]; // get the turn at the random index
		
		return turn;
	}
	
	// method that gets the index
	private Integer getIndex(Future<Integer> theFuture)
	{
		Integer index = -1;
		
		try {
			index = theFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return index;
	}
	
	public void tieAlert(int game)
	{
		Alert alertScreen = new Alert(AlertType.INFORMATION);
		
		alertScreen.setTitle("Nobody WON!");
		alertScreen.setHeaderText(null);
		alertScreen.setContentText("X and O tied on game " + game + ".\nThis dialog box will close on its own");
		
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		
		pause.setOnFinished(e->{alertScreen.close();});
		
		pause.play();
		
		alertScreen.show();
	}
	
	// this method displays an alert with who won
	public void winAlertX(int game)
	{
		Alert alertScreen = new Alert(AlertType.INFORMATION);
		
		alertScreen.setTitle("X WON!");
		alertScreen.setHeaderText(null);
		alertScreen.setContentText("X won on game " + game + ".\nThis dialog box will close on its own");
		
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		
		pause.setOnFinished(e->{alertScreen.close();});
		
		pause.play();
		
		alertScreen.show();
	}
	
	// this method displays an alert with who won
	public void winAlertO(int game)
	{
		Alert alertScreen = new Alert(AlertType.INFORMATION);
		
		alertScreen.setTitle("O WON!");
		alertScreen.setHeaderText(null);
		alertScreen.setContentText("O won on game " + game + ".\nThis dialog box will close on its own");
		
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		
		pause.setOnFinished(e->{alertScreen.close();});
		
		pause.play();
		
		alertScreen.show();
	}
	
	// this method will figure out if X won
	public Boolean didXWin(String[] board)
	{
		boolean won = false;
		
		if (board[0].equals("X") && board[1].equals("X") && board[2].equals("X")) // top row
			won = true;
		
		else if (board[3].equals("X") && board[4].equals("X") && board[5].equals("X")) // mid row
			won = true;
		
		else if (board[6].equals("X") && board[7].equals("X") && board[8].equals("X")) // bottom row
			won = true;
		
		else if (board[0].equals("X") && board[3].equals("X") && board[6].equals("X")) // left column
			won = true;
		
		else if (board[1].equals("X") && board[4].equals("X") && board[7].equals("X")) // mid column
			won = true;
		
		else if (board[2].equals("X") && board[5].equals("X") && board[8].equals("X")) // right column
			won = true;
		
		else if (board[0].equals("X") && board[4].equals("X") && board[8].equals("X")) // diagonal
			won = true;
		
		else
			won = false;
		
		return won;
	}
	
	// this method will figure out if O won
	public Boolean didOWin(String[] board)
	{
		boolean won = false;
		
		if (board[0].equals("O") && board[1].equals("O") && board[2].equals("O")) // top row
			won = true;
		
		else if (board[3].equals("O") && board[4].equals("O") && board[5].equals("O")) // mid row
			won = true;
		
		else if (board[6].equals("O") && board[7].equals("O") && board[8].equals("O")) // bottom row
			won = true;
		
		else if (board[0].equals("O") && board[3].equals("O") && board[6].equals("O")) // left column
			won = true;
		
		else if (board[1].equals("O") && board[4].equals("O") && board[7].equals("O")) // mid column
			won = true;
		
		else if (board[2].equals("O") && board[5].equals("O") && board[8].equals("O")) // right column
			won = true;
		
		else if (board[0].equals("O") && board[4].equals("O") && board[8].equals("O")) // diagonal
			won = true;
		
		else
			won = false;
		
		return won;
	}
	
	// function for debugging purposes
	private void printBoard(String[] theBoard)
	{
		
		System.out.println("\nThe Game Board:");
		System.out.println(theBoard[0]+ " " + theBoard[1] + " " + theBoard[2]);
		System.out.println(theBoard[3]+ " " + theBoard[4] + " " + theBoard[5]);
		System.out.println(theBoard[6]+ " " + theBoard[7] + " " + theBoard[8]);
	}
	
}
