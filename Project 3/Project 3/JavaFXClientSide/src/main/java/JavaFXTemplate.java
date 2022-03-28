/* Jose M. Aguilar (jaguil61)
 * Project 3 - Baccarat Client Program
 * 
 * "[This] implementation will be a two player game where each player is a separate client
 * and the game is run by a server. Your server and clients will use the same machine;
 * with the server choosing a port on the local host and clients knowing the local host and
 * port number"
 * 
 * Not done but I got pretty far :^)
 * 
 * JavaFXTemplate.java
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFXTemplate extends Application {

	Client clientConnection;
	ListView<String> resultsList;
	String choiceStr; // stores who the client bet on
	BaccaratInfo gameInfo;
	
	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Baccarat Client Side");
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		primaryStage.setScene(createStartScreen());
		primaryStage.show();
	}
	
	// method that creates the client start screen
	public Scene createStartScreen()
	{
		GridPane startGrid = new GridPane();
		Text portNumText = new Text("Enter Port Number:");
		Text ipAddyText = new Text("Enter IP Address:");
		Button connectButton = new Button("Connect to Server");
		TextField portNumField = new TextField();
		TextField ipAddyField = new TextField();
		
		// set the borders (make it look pleasant)
		startGrid.setAlignment(Pos.CENTER);
		startGrid.setHgap(10);
		startGrid.setVgap(10);
		
		// will create the game screen
		EventHandler<ActionEvent> gameScreenEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage gameStage = new Stage();
				
				gameStage.setTitle("Baccarat!");
				
				// connect to server 
				clientConnection = new Client(data->{
					Platform.runLater(()->{resultsList.getItems().add(data.toString());
									});
					});
					
				clientConnection.start();
				
				gameStage.setScene(createGameScreen());
				gameStage.show();
			}
		
		};
		
		connectButton.setOnAction(gameScreenEvent);
		
		startGrid.add(portNumText, 0, 0);
		startGrid.add(portNumField, 1, 0);
		startGrid.add(ipAddyText, 0, 1);
		startGrid.add(ipAddyField, 1, 1);
		startGrid.add(connectButton, 1, 2);
		
		return new Scene(startGrid, 500, 500);
	}
	
	// method that creates the client game screen
	public Scene createGameScreen()
	{
		BorderPane mainPane = new BorderPane();
		BorderPane gameBoardPane = new BorderPane(); // Will show the cards
		
		HBox playerCardsBox = new HBox(); // Where the player cards will sit
		HBox dealerCardsBox = new HBox(); // Where the dealer cards will sit
		
		GridPane winningsGrid = new GridPane(); // Will store the players winnings
		GridPane betAreaGrid = new GridPane(); // Will store the objects that allows the client to place bets
		
		resultsList = new ListView<String>();
		
		Text whoWonText = new Text("Who Won Goes Here"); // delete later?
		Text curWinsText = new Text("Your Current Winnings:");
		Text totalWinsText = new Text("Your Total Winnings:");
		Text betText = new Text("Enter bet:");
		
		TextField betField = new TextField(); // Much you're going to bet
		
		ToggleButton playerTButton = new ToggleButton("Player");
		ToggleButton dealerTButton = new ToggleButton("Dealer");
		ToggleButton tieTButton = new ToggleButton("Draw"); // "Tie"
		ToggleGroup betTGroup = new ToggleGroup();
		betTGroup.getToggles().addAll(playerTButton, dealerTButton, tieTButton);
		
		Button playButton = new Button("Play");
		playButton.setDisable(true);
		Button quitButton = new Button("Quit Game");
		
		// set the borders (make it look pleasant)
		BorderPane.setAlignment(quitButton, Pos.CENTER);
		BorderPane.setAlignment(playerCardsBox, Pos.CENTER);
		BorderPane.setAlignment(dealerCardsBox, Pos.CENTER);
		
		mainPane.setPadding(new Insets(25, 25, 25, 25));
		gameBoardPane.setPadding(new Insets(25, 25, 25, 25));
		winningsGrid.setPadding(new Insets(25, 25, 25, 25));
		betAreaGrid.setPadding(new Insets(25, 25, 25, 25));
		
		winningsGrid.setHgap(10);
		winningsGrid.setVgap(10);
		betAreaGrid.setHgap(10);
		betAreaGrid.setVgap(10);
		
		
		
		EventHandler<ActionEvent> toggleButEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ToggleButton b1 = (ToggleButton)event.getSource();
				 
				
				// enable the play button
				if (b1.isSelected())
				{
					choiceStr = b1.getText();
					playButton.setDisable(false);
				}
				
				else
				{
					choiceStr = "";
					playButton.setDisable(true);
				}
			}
			
		};
		
		playerTButton.setOnAction(toggleButEvent);
		dealerTButton.setOnAction(toggleButEvent);
		tieTButton.setOnAction(toggleButEvent);
		
		// send stuff to server
		playButton.setOnAction(e->{clientConnection.send(betField.getText()); betField.clear();});
		
		// add everything to appropriate BorderPanes and GridPanes
		winningsGrid.add(curWinsText, 0, 0);
		winningsGrid.add(totalWinsText, 0, 1);
		
		betAreaGrid.add(betText, 0, 0);
		betAreaGrid.add(betField, 1, 0);
		betAreaGrid.add(playerTButton, 2, 0);
		betAreaGrid.add(dealerTButton, 3, 0);
		betAreaGrid.add(tieTButton, 4, 0);
		betAreaGrid.add(playButton, 5, 0);
		
		gameBoardPane.setLeft(playerCardsBox);
		gameBoardPane.setRight(dealerCardsBox);
		gameBoardPane.setCenter(whoWonText);
		
		mainPane.setCenter(gameBoardPane);
		mainPane.setTop(quitButton);
		mainPane.setLeft(winningsGrid);
		mainPane.setRight(resultsList);
		mainPane.setBottom(betAreaGrid);
		
		return new Scene(mainPane, 1000, 500);
	}
}
