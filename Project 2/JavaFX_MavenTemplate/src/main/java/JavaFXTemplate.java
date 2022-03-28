/* 
 * CS 342 Project 2 (main)
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * JavaFXTemplate.java
 * 
 * In this project you will implement the popular casino and state lottery game, Keno.
 * 
 */

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

public class JavaFXTemplate extends Application {
	
	MenuBar menuBar;
	Button startButton;
	Button startDrawings;
	ToggleButton randomNumBut;
	BorderPane borderPane;
	GridPane gridPane;
	int numSpotsChosen = 0; // keeps track of the number of spots chosen
	int numSpotsPlay = 0; // the number of spots the player wants to play
	int numDrawingsPlay = 0; // the number of drawings the player wants to play
	int totalScoreNum = 0;
	Boolean spotsToPlayChosen = false;
	Boolean drawingsToPlayChosen = false;
	Boolean randomNumsChosen = false;
	ArrayList<Integer> randNumsArrayList = new ArrayList<>(); // stores all the random numbers that will be generated if this button is pressed
	ArrayList<Integer> spotsChosenArrayList = new ArrayList<>(); // keeps track of the spots the client chose
	ArrayList<Integer> matchedNumsArrayList = new ArrayList<>(); // keeps track of the numbers the client matched
	EventHandler<ActionEvent> chooseSpotsHandler;
	EventHandler<ActionEvent> chooseDrawingsHandler;
	EventHandler<ActionEvent> spotHandler; // for the spots in the grid
	EventHandler<ActionEvent> startDrawingsHandler;
	EventHandler<ActionEvent> randomNumHandler; // for the button that lets you choose random numbers
	PauseTransition pause = new PauseTransition(Duration.seconds(1));
	 
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {	
		primaryStage.setTitle("Keno!");
		
		startButton = new Button("Lets Play!");
		borderPane = new BorderPane();
		
		// Play button 
		startButton.setPrefHeight(100);
		startButton.setPrefWidth(200);
		
		// Organize Menu and Start Button
		borderPane.setTop(createMenuBar(0));
		borderPane.setCenter(startButton);
		
		Scene startScene = new Scene(borderPane, 500, 300);
		primaryStage.setScene(startScene);
		primaryStage.show();
		
		startButton.setOnAction(e->primaryStage.setScene(createGameScene())); // switches scene
	}
	
	/* method to create menu bar
	 * if scene == 1 then the style option will be returned
	*/
	public MenuBar createMenuBar(int scene)
	{
		// Menu Options
		menuBar  = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem optOne = new MenuItem("Rules for Keno");
		MenuItem optTwo = new MenuItem("Odds of Winning");
		MenuItem optThree = new MenuItem ("New Look");
		MenuItem optFour = new MenuItem("Exit Game");
		
		/* Menu actions
		 * Open new window that displays rules so 
		 * player can have the rules open as they play
		 */
		optOne.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
						
				Stage rulesStage = new Stage(); // create new stage/window
						
				rulesStage.setTitle("Rules for Keno");
						
				Image rulesPic = new Image("keno_rules.jpg"); 
						
				ImageView rulesView = new ImageView(rulesPic);
						
				Scene rulesScene = new Scene(new ScrollPane(rulesView), 985, 260); // ScrollPane makes the scene scrollable
							
				rulesStage.setScene(rulesScene);
				rulesStage.show();	
			}
					
		});
				
		/* Open new window that displays the odds of winning so 
		 * player can have the odds open as they play
		 */
		optTwo.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
						
				Stage oddsStage = new Stage(); // create new stage/window
						
				oddsStage.setTitle("Odds of Winning");
						
				Image oddsPic = new Image("win_odds.jpg"); 
						
				ImageView oddsView = new ImageView(oddsPic);
						
				Scene oddsScene = new Scene(new ScrollPane(oddsView), 775, 500); 
						
				oddsStage.setScene(oddsScene);
				oddsStage.show();	
			}
					
		});
		
		optThree.setOnAction(e->menuBar.setStyle("-fx-background-color: red;"));
		
		// Closes out of the game
		optFour.setOnAction(e->Platform.exit()); // closes all windows when "Exit Game" pressed
		
		if (scene == 1)
			menu.getItems().addAll(optOne, optTwo, optThree, optFour);
		
		else
			menu.getItems().addAll(optOne, optTwo, optFour);
		
		menuBar.getMenus().add(menu);
		
		return menuBar;
	}
	
	// method that creates the game scene
	public Scene createGameScene()
	{
		borderPane = new BorderPane();
		
		GridPane spotOptGrid = new GridPane(); // GridPane used to store buttons for game options
		spotOptGrid.setAlignment(Pos.CENTER_LEFT);
		spotOptGrid.setHgap(10);
		spotOptGrid.setVgap(10);
		
		GridPane spotsGrid = new GridPane();
		spotsGrid.setAlignment(Pos.CENTER);
		spotsGrid.setHgap(10);
		spotsGrid.setVgap(10);
		
		String buttonNamesArray[] = {"1", "4", "8", "10"}; // used to name the four buttons for how many spots they want to choose
		
		int counter = 1; // used to count the columns 
		
		// used to store buttons so that they can all be disabled/enabled whenever
		ToggleButton spotOptArray[] = new ToggleButton[4];
		ToggleButton drawingsOptArray[] = new ToggleButton[4]; 
		ToggleButton spotArray[] = new ToggleButton[80]; // stores all the buttons in the spots grid
		
		// Buttons for choosing spots section
		ToggleGroup spotToggleGroup = new ToggleGroup(); // lets only one button be chosen at a time
		
		Text spotText = new Text("Number of spots to play:");
		
		spotOptGrid.add(spotText, 0, 0);
		
		//event handler for when the client chooses how many spots they want to play
		chooseSpotsHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToggleButton b1 = (ToggleButton)event.getSource(); // get the button that was pressed
				
				numSpotsPlay = Integer.valueOf(b1.getText());
				
				if (!b1.isSelected()) // if the button is toggled off unassign the number of spots to play
				{
					numSpotsPlay = 0;
					spotsToPlayChosen = false;
					startDrawings.setDisable(true); // disable just in case they deselect the number of spots they want to play
					disableOrEnable(spotArray, 0); // disable all grid buttons
					randomNumBut.setDisable(true); // disable random number button
					/*System.out.println("The number of spots being played is " + numSpotsPlay); // for debugging purposes
					System.out.println("Spots not chosen");*/
				}
				
				else if (b1.isSelected() && randomNumsChosen)
				{
					spotsToPlayChosen = true;
					disableOrEnable(spotArray, 0); // disable all grid buttons
					randomNumBut.setDisable(false); // enable random number button
				}
				
				else // is selected
				{
					spotsToPlayChosen = true;
					disableOrEnable(spotArray, 1); // enable all grid buttons
					randomNumBut.setDisable(false); // enable random number button
					/*System.out.println("The number of spots being played is " + numSpotsPlay); // for debugging purposes
					System.out.println("Spots chosen"); // for debugging purposes*/
				}
				
				// the client chose all their numbers and has selected how many spots to play as well as how many drawings to play
				if (spotsToPlayChosen && drawingsToPlayChosen && numSpotsChosen == numSpotsPlay)
					startDrawings.setDisable(false);
				
				// the client chose how many spots to play, how many drawings to play, and chose to do random numbers
				else if (b1.isSelected() && drawingsToPlayChosen && randomNumsChosen) 
					startDrawings.setDisable(false);
				
				else // the client hasn't chosen the correct number of spots so don't let them start the drawing
					startDrawings.setDisable(true);
				
			}
		};
		
		// use a for loop to create the buttons
		for (int i = 0; i < buttonNamesArray.length; i++)
		{
			ToggleButton b1 = new ToggleButton(buttonNamesArray[i]);
			b1.setOnAction(chooseSpotsHandler);
			b1.setToggleGroup(spotToggleGroup); // add the button to the toggle group
			spotOptArray[i] = b1; // add buttons to array to disable or enable them later
			spotOptGrid.add(b1, counter, 0);
			
			
			counter++;
		}
		
		counter = 1; // reset counter
		// END OF choosing spots section
		
		// Buttons for choosing how many times they wanna play (drawings)
		ToggleGroup drawingToggleGroup = new ToggleGroup();
		
		Text drawingText = new Text("Number of drawings to play:");
		
		spotOptGrid.add(drawingText, 0, 3);
		
		// event handler for when the client chooses how many spots they want to play
		chooseDrawingsHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToggleButton b1 = (ToggleButton)event.getSource(); // get the button that was pressed
				
				numDrawingsPlay = Integer.valueOf(b1.getText());
				
				if (!b1.isSelected()) // if the button is pressed toggled off unassign the number of drawings  to play
				{
					numDrawingsPlay = 0;
					drawingsToPlayChosen = false;
					startDrawings.setDisable(true); // disable just in case they deselect the number of drawings they want to play
					/*System.out.println("The number of drawings being played is " + numDrawingsPlay); // for debugging purposes
					System.out.println("Drawings not chosen"); // for debugging purposes*/
				}
				
				else // is selected
				{
					drawingsToPlayChosen = true;
					/*System.out.println("The number of drawings being played is " + numDrawingsPlay); // for debugging purposes
					System.out.println("Drawings chosen"); // for debugging purposes*/
				}
				
				// the client chose all their numbers and has selected how many spots to play as well as how many drawings to play
				if (spotsToPlayChosen && drawingsToPlayChosen && numSpotsChosen == numSpotsPlay)
					startDrawings.setDisable(false);
				
				else if (spotsToPlayChosen && drawingsToPlayChosen && randomNumsChosen)
					startDrawings.setDisable(false);
				
				else // the client hasn't chosen the correct number of spots so don't let them start the drawing
					startDrawings.setDisable(true);
			}
		};	
		
		for (int i = 0; i < buttonNamesArray.length; i++)
		{
			ToggleButton b1 = new ToggleButton(Integer.toString(i+1));
			b1.setOnAction(chooseDrawingsHandler);
			b1.setToggleGroup(drawingToggleGroup);
			drawingsOptArray[i] = b1; // add buttons to array to disable or enable them later
			spotOptGrid.add(b1, counter, 3);
			
			counter++;
		}
		// END OF drawings section
		
		// button for random number generator section
		Text randomNumText = new Text("Choose random numbers?: ");
		
		spotOptGrid.add(randomNumText, 0, 6);
		
		randomNumHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ToggleButton b1 = (ToggleButton)event.getSource();
				
				if(b1.isSelected())
				{
					// disable the grid so that they can't choose any spots
					disableOrEnable(spotArray, 0);
					
					int primRandNumArray[] = randomNums(numSpotsPlay); // create an array of random numbers based on how many spots they want to play
					
					primArrToArrList(randNumsArrayList, primRandNumArray);
					randomNumsChosen = true;
				}
				
				else
				{
					// renable the grid so that they can choose
					disableOrEnable(spotArray, 1);
					
					randomNumsChosen = false;
				}
				
				// enable the drawings button
				if (b1.isSelected() && spotsToPlayChosen && drawingsToPlayChosen)
					startDrawings.setDisable(false);
				
				// button was toggled off but the player had chosen the appropriate spots
				else if (spotsToPlayChosen && drawingsToPlayChosen && numSpotsChosen == numSpotsPlay) 
					startDrawings.setDisable(false);
				
				else
					startDrawings.setDisable(true);
			}
			
		};
		
		randomNumBut = new ToggleButton("Yes");
		randomNumBut.setOnAction(randomNumHandler);
		randomNumBut.setDisable(true);
		
		spotOptGrid.add(randomNumBut, 1, 6);
		// END OF random num button section
		
		// Create grid of numbers (1-80)
		Text gridText = new Text("Choose your numbers:");
		
		// event handler for when the spot grid buttons are toggled on or off
		spotHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ToggleButton b1 = (ToggleButton)event.getSource(); // get the button that was pressed
				
				IntegerStringConverter converter = new IntegerStringConverter(); // used to convert strings to integers
				int atIndex = -1;
				
				if (b1.isSelected()) // enabled
				{
					spotsChosenArrayList.add(converter.fromString(b1.getText()));
					numSpotsChosen++;
					/*System.out.println("Chose button " + b1.getText()+ " | Number of spots currently chosen " + numSpotsChosen + 
							" | ArrayList size " + spotsChosenArrayList.size()); // for debugging purposes*/
				}
				
				else // disabled
				{
					atIndex = spotsChosenArrayList.indexOf(converter.fromString(b1.getText())); // find the index of the number that was deselected
					spotsChosenArrayList.remove(atIndex);
					
					numSpotsChosen--;
					/*System.out.println("Chose button " + b1.getText()+ " | Number of spots currently chosen " + numSpotsChosen + 
							" | ArrayList size " + spotsChosenArrayList.size()); // for debugging purposes*/
				}
				
				// the client chose the appropriate number of spots and has chosen the number of drawings they want to play
				if (numSpotsChosen == numSpotsPlay && drawingsToPlayChosen) 
					startDrawings.setDisable(false);
				
				else // the client hasn't chosen the correct number of spots so don't let them start the drawing
					startDrawings.setDisable(true);
					
			}
			
		};
		
		spotsGrid.add(gridText, 0, 4);
		
		createSpotGrid(spotsGrid, spotArray);
		// END OF numbers grid section
		
		// start drawings button section	
		GridPane scoreGridPane = new GridPane(); // for the scoring section
			
		// event handler that handles what happens when you press the start drawings button
		startDrawingsHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// disable all buttons until drawings are complete
				Button b1 = (Button)event.getSource();
				b1.setDisable(true); 
				disableOrEnable(spotOptArray, 0);
				disableOrEnable(drawingsOptArray, 0);
				disableOrEnable(spotArray, 0);
				randomNumBut.setDisable(true);
				
				Integer spotsChosenArray[] = new Integer[numSpotsPlay];
				
				//figure out if random numbers were chosen or not
				if (randomNumsChosen)
					spotsChosenArray = randNumsArrayList.toArray(spotsChosenArray); // convert arrayList to array
				
				else // random numbers were not chosen so use the ones the client chose
					spotsChosenArray = spotsChosenArrayList.toArray(spotsChosenArray);
				
	
				scoreGridPane.getChildren().clear(); // clear the grid pane so that it doesn't stack on top of each other
				
				// loop as many times as drawings the client chose
				for (int i = 0; i < numDrawingsPlay; i++)
				{
					int curScore = 0;
					
					int winningNumsArray[] = randomNums(20);
					
					int matchedNums = 0;
					matchedNums = matchingNums(winningNumsArray, spotsChosenArray, matchedNumsArrayList);
					
					curScore = currentWinnings(numSpotsPlay, matchedNums);
					
					totalScoreNum+=curScore;
					
					addToScoreSec(i, scoreGridPane, winningNumsArray, spotsChosenArray, matchedNumsArrayList, curScore);
					
					/*// for debugging purposes
					System.out.println();
					System.out.println("Drawing: "+ (i+1) + " | You matched " + matchedNums + " number(s)");
					System.out.println("Winning Numbers: ");
					printPrimArray(winningNumsArray);
					System.out.println();
					System.out.println("Your numbers: ");
					printArray(spotsChosenArray);
					System.out.println();
					System.out.println("Numbers Matched: ");
					printArrayList(matchedNumsArrayList);
					System.out.println();*/
					
					matchedNumsArrayList.removeAll(matchedNumsArrayList); // clear 
				}
				
				createScoreSec(scoreGridPane, totalScoreNum);
				
				// re-enable all the buttons again
				b1.setDisable(false);
				disableOrEnable(spotOptArray, 1);
				disableOrEnable(drawingsOptArray, 1);
				randomNumBut.setDisable(false);
				
				if (randomNumsChosen)
					disableOrEnable(spotArray, 0);
				
				else
					disableOrEnable(spotArray, 1);
			}
			
		};
		
		startDrawings = new Button("Start Drawing(s)");
		startDrawings.setOnAction(startDrawingsHandler);
		startDrawings.setDisable(true);
		
		BorderPane.setAlignment(startDrawings, Pos.CENTER_LEFT);
		
		// Add sections to borderPane
		borderPane.setTop(createMenuBar(1));
		borderPane.setLeft(spotOptGrid);
		borderPane.setCenter(spotsGrid);
		borderPane.setRight(startDrawings);
		borderPane.setBottom(scoreGridPane);
		
		return new Scene(borderPane, 1150, 700);
	}
	
	// method to create buttons and to add them to the grid
	public void createSpotGrid(GridPane spotsGrid, ToggleButton spotArray[])
	{
		int counter = 1; //used to name the buttons
		int index = 0;
		
		for (int i = 0; i < 10; i++)
		{
			for (int j = 1; j < 9; j++)
			{
				ToggleButton b1 = new ToggleButton(Integer.toString(counter));
				b1.setOnAction(spotHandler);
				b1.setDisable(true);
				spotArray[index] = b1;
				spotsGrid.add(b1, j, i);
				
				counter++;
				index++;
			}
		}
	}
	
	/* method that disables or enables all the buttons that are passed into it 
	 * 0 = disable, 1 = enable
	 */
	public void disableOrEnable(ToggleButton toggleButtonArray[], int option)
	{
		for (ToggleButton buttons: toggleButtonArray)
		{	
			if (option == 0) // disable
				buttons.setDisable(true);
			
			else // enable
				buttons.setDisable(false);
		}
	}
	
	/* method that chooses random numbers based on the spots the 
	 * client wants to play and puts them into an array. Also used for
	 * generating the winning numbers. Using primitive type int because
	 * Integer was giving issues.
	 */
	public int[] randomNums(int howMany)
	{
		int randNumArray[] = new int[howMany]; // stores all the random numbers generated 
		
		Random randGen = new Random();
		
		/* Loop that creates the appropriate amount of random numbers based
		 * the amount of spots the client wants to play
		 */
		for (int i = 0; i < randNumArray.length; i++)
		{
			int randNum = randGen.nextInt(81); // generates random from 0 to 80
			
			// check if the randNum is already in the array
			if (isInArray(randNumArray, randNum))
				i--; //de-increment so that the loop goes back to the array index when the for loop increments and tries generating a new random randNum
			
			// randNum was not in the array so add it
			else
				randNumArray[i] = randNum;
		}
		
		return randNumArray;
	}
	
	// method that creates the scoring section for the game
	public void createScoreSec(GridPane scoreGridPane, int totalScoreNum)
	{
		
		Text totalScore = new Text("Your total winnings ($): ");
		Text totalScoreText = new Text(Integer.toString(totalScoreNum));
		Text padding = new Text("    ");
						
		scoreGridPane.add(totalScore, 0, 1);
		scoreGridPane.add(totalScoreText, 1, 1);
		scoreGridPane.add(padding, 2, 0);
	}
	
	// method that adds all the winning numbers, players numbers, matched numbers to the score section
	// pause transitions would go here
	public void addToScoreSec(int iteration, GridPane scoreGridPane, int winningNums[], Integer playerNums[], ArrayList<Integer> matchedNums, int curScoreNum)
	{	
		scoreGridPane.setHgap(10);
		scoreGridPane.setHgap(10);
		
		Text curScore = new Text("Your winnings ($): ");
		Text curScoreText = new Text(Integer.toString(curScoreNum));
		
		Text onDrawingText = new Text("Drawing " + (iteration + 1)+ " | " + "Winning Numbers:");
		scoreGridPane.add(onDrawingText, 3, (iteration * 4));
		scoreGridPane.add(curScore, 24, (iteration * 4));
		scoreGridPane.add(curScoreText, 25, (iteration * 4));
		
		for (int i = 0; i < winningNums.length; i++)
		{
			Text winNum = new Text(Integer.toString(winningNums[i]));
			
			scoreGridPane.add(winNum, (i+4), (iteration * 4));
		}
		
		Text playersNumsText = new Text("Your Numbers:");
		scoreGridPane.add(playersNumsText, 3, (iteration * 4) + 1);
		
		for (int i = 0; i < playerNums.length; i++)
		{
			Text playNum = new Text(Integer.toString(playerNums[i]));
			
			scoreGridPane.add(playNum, (i + 4), (iteration * 4) + 1);
		}
		
		Text matchedNumsText = new Text("Matched Numbers:");
		scoreGridPane.add(matchedNumsText, 3, (iteration * 4) + 2);
		
		for (int i = 0; i < matchedNums.size(); i++)
		{
			Text playNum = new Text(Integer.toString(playerNums[i]));
			
			scoreGridPane.add(playNum, (i + 4), (iteration * 4) + 2);
		}
		
	}
	
	// method that looks for the num in the array. Returns true if it is
	public boolean isInArray(int numArray[], int num)
	{
		for (int i : numArray)
		{
			if (i == num) // num was found in the array
				return true; 
			
			// else keep going
		}
		
		return false; //num was not found in array
	}
	
	/* method that counts how many matching numbers two arrays have and returns how many matched.
	 * also adds the matched numbers to an array list for later use
	 */
	public int matchingNums(int winningNums[], Integer chosenNums[], ArrayList<Integer> matchedNumsArrayList)
	{
		int matching = 0;
		
		// loop through the numbers the client chose and see if they're in the winningNums array
		for (Integer i : chosenNums)
		{
			if(isInArray(winningNums, i))
			{
				matchedNumsArrayList.add(i);
				matching++;
			}
			
			// else do nothing and keep moving
		}
		
		return matching;
	}
	
	// converts a primitive int array into a Integer array and returns that Integer array
	public void primArrToArrList(ArrayList<Integer> randNumsArrayList, int primArray[])
	{	
		randNumsArrayList.removeAll(randNumsArrayList); // empty the list and start with a fresh one
		
		for(int i = 0; i < primArray.length; i++)
			randNumsArrayList.add(primArray[i]); // copy values from the primitive array into the array list
	}
	
	//figures out how much money the player one based on how many they matched and how many spots they wanted play
	public int currentWinnings(int spotsPlayed, int matched)
	{
		int score = 0;
		
		switch(spotsPlayed) {
		
		case 10 : 
			score = tenSpots(matched);
			break;
		
		case 8 : 
			score = eightSpots(matched);
			break;
		
		case 4 : 
			score = fourSpots(matched);
			break;
		
		default : 
			score = oneSpot(matched);
		}
		
		return score;
	}
	
	// if they chose to play 10 spots. returns the palyers score
	public int tenSpots(int matched)
	{
		int score = 0;
		
		switch(matched) {
			case 10 : score = 100000;
				break;
				
			case 9 :
				score = 4250;
				break;
			
			case 8 :
				score = 450;
				break;
				
			case 7 :
				score = 40;
				break;
				
			case 6 :
				score = 15;
				break;
				
			case 5 :
				score = 2;
				break;
				
			case 0 :
				score = 5;
				break;
				
			default : 
				score = 0;
		}
		
		return score;
	}
	
	// if they chose to play 8 spots. returns the players score
	public int eightSpots(int matched)
	{
		int score = 0;
		
		switch(matched) {			
			case 8 :
				score = 10000;
				break;
				
			case 7 :
				score = 750;
				break;
				
			case 6 :
				score = 50;
				break;
				
			case 5 :
				score = 12;
				break;
				
			case 4 :
				score = 2;
				break;
				
			default : 
				score = 0;
		}
		
		return score;
	}
	
	// if they chose to play 4 spots. returns the players score
	public int fourSpots(int matched)
	{
		int score = 0;
		
		switch(matched) {						
			case 4 :
				score = 75;
				break;
				
			case 3 :
				score = 5;
				break;
				
			case 2 :
				score = 1;
				break;
				
			default : 
				score = 0;
		}
		
		return score;
	}
	
	// if they chose to play 1 spot. returns the palayers score
	public int oneSpot(int matched)
	{
		int score = 0;
		
		switch(matched) {
			case 1 :
				score = 2;
				break;
				
			default : 
				score = 0;
		}
		
		return score;
	}
	
	/*
	// for debugging purposes
	public void printPrimArray(int arr[])
	{
		for (int i: arr)
			System.out.print(i + " ");
	}
	
	// for debugging purposes
	public void printArray(Integer arr[])
	{
		for (int i: arr)
			System.out.print(i + " ");
	}
	
	// for debugging purposes
	public void printArrayList(ArrayList<Integer> arrList)
	{
		for (int i: arrList)
			System.out.print(i + " ");
	}*/
}
