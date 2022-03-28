/* Project 5
 * Jose M. Aguilar (jaguil61)
 * 
 * This class stores are the logic behind the GUI (Action Events)
 * 
 * StartController.java
 */ 

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController implements Initializable{

	@FXML
	private Button serverBut;
	
	@FXML
	private Button clientBut;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	// method that changes scenes once the server button is pressed
	public void serverAction(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/ServerScreen.fxml"));
		
		// grab current scene
		Stage stage = (Stage) serverBut.getScene().getWindow();
		
		// swap scene
		stage.setScene(new Scene(root));
	}
	
	// method that changes scenes once the client button is pressed
	public void clientAction(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/ClientScreen.fxml"));
		
		// grab current scene
		Stage stage = (Stage) clientBut.getScene().getWindow();
		
		// swap scene
		stage.setScene(new Scene(root));
	}
}
