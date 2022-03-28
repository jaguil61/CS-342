/* Project 4 - Tic Tac Toe
 * Jose M. Aguilar (jaguil61)
 * 
 * This class only creates the GUI
 * 
 * JavaFXTemplate.java
 */

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Tic Tac Toe!");
		
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		
		 // switches the scenes
		pause.setOnFinished(e-> {
			try {
				primaryStage.setScene(createGameScene());
			} catch (Exception e1) {
				// Auto-generated catch block
				e1.printStackTrace();
				System.exit(1); // OOPS something happened
			}
		});
		
		pause.play();
		
		Parent root = FXMLLoader.load(getClass().getResource("/LoadScreen.fxml"));
		
		Scene loadScreen = new Scene(root,600, 250);
		
		
		primaryStage.setScene(loadScreen);
		primaryStage.show();
	}
	
	// method that creates the game scene 
	public Scene createGameScene() throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("/GameScreen.fxml"));
		
		return new Scene(root, 1100, 550);
	}
}
