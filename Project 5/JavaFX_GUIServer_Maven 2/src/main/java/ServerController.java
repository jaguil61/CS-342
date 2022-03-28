/* Project 5 - GUI Server Improvement
 * Jose M. Aguilar (jaguil61)
 * 
 * This class displays what the clients are sending to eachother. Original code taken from class example. 
 * 
 * ServerController.java
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ServerController implements Initializable{

	Server serverConnection;
	
	@FXML
	private ListView<String> listItems;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		serverConnection = new Server(data -> {
			Platform.runLater(()->{
				listItems.getItems().add(data.toString());
			});

		});
	}

}
