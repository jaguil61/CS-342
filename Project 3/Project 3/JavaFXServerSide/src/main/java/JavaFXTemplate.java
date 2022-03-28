/* Jose M. Aguilar (jaguil61)
 * Project 3 - Baccarat Server Program
 * 
 * "[This] implementation will be a two player game where each player is a separate client
 * and the game is run by a server. Your server and clients will use the same machine;
 * with the server choosing a port on the local host and clients knowing the local host and
 * port number"
 * 
 * Not done but I got pretty far :^)
 * All the required classes work though.
 * 
 * JavaFXTemplate.java
 */

import java.util.ArrayList;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFXTemplate extends Application {
	
	Server serverConnection;
	ListView<String> resultsC1List; //results for client one
	ListView<String> resultsC2List; //results for client two
	
	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Baccarat Server Side");
				
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
	
	// A method that creates the server intro screen
	public Scene createStartScreen()
	{
		GridPane startGrid = new GridPane();
		Text enterPortText = new Text("Enter Port Number:");
		TextField portNumberField = new TextField();
		Button startButton = new Button("Start Server");
		
		// set the borders (make it look pleasant)
		startGrid.setAlignment(Pos.CENTER);
		startGrid.setHgap(10);
		startGrid.setVgap(10);
		
		// will create the info screen
		EventHandler<ActionEvent> startInfoEvent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage infoStage = new Stage();
				
				infoStage.setTitle("Baccarat Server Information");
				
				// start up the server
				serverConnection = new Server(data -> {
					Platform.runLater(()->{
						resultsC1List.getItems().add(data.toString());
					});

				});
				
				infoStage.setScene(createInfoScreen());
				infoStage.show();
			}
			
		};
		
		startButton.setOnAction(startInfoEvent);
		
		// add objects to GridPane
		startGrid.add(enterPortText, 0, 0);
		startGrid.add(portNumberField, 1, 0);
		startGrid.add(startButton, 1, 1);
		
		return new Scene(startGrid, 500, 500); 
	}
	
	// A method that creates the server info screen
	public Scene createInfoScreen()
	{
		BorderPane infoPane = new BorderPane();
		GridPane clientInfoGrid = new GridPane();
		resultsC1List = new ListView<String>(); //results for client one
		resultsC2List = new ListView<String>(); //results for client two
		Text numClientsText = new Text("Number of Clients Connected:");
		Text clientJoinText = new Text("A new client joined!");
		Text clientDropText = new Text("A client dropped off the server!");
		Text anthrHandC1Text = new Text("(Client 1) Playing Another Hand:"); 
		Text anthrHandC2Text = new Text("(Client 2) Playing Another Hand:"); 
		Button closeServerButton = new Button("Close Server");
		
		// set the borders (make it look pleasant)
		BorderPane.setAlignment(closeServerButton, Pos.CENTER);
		infoPane.setPadding(new Insets(25, 25, 25, 25));
		clientInfoGrid.setPadding(new Insets(25, 25, 25, 25));
		clientInfoGrid.setHgap(5);
		clientInfoGrid.setVgap(50);
		
		// add objects to the GridPane and BorderPane
		clientInfoGrid.add(numClientsText, 0, 0);
		clientInfoGrid.add(clientJoinText, 0, 1);
		clientInfoGrid.add(clientDropText, 0, 2);
		clientInfoGrid.add(anthrHandC1Text, 0, 3);
		clientInfoGrid.add(anthrHandC2Text, 0, 4);
		
		infoPane.setTop(closeServerButton);
		infoPane.setLeft(resultsC1List);
		infoPane.setRight(resultsC2List);
		infoPane.setCenter(clientInfoGrid);
		
		return new Scene(infoPane, 1000, 500);
	}
}
