/* Project 5 - GUI Server Improvement
 * Jose M. Aguilar (jaguil61)
 * 
 * This class displays the messages to the clients. Original code taken from class example. 
 * 
 * ClientController.java
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class ClientController implements Initializable{

	Client clientConnection;
	MessageInfo message;
	ToggleGroup clientTog;
	ObservableList<Button> obsvList;
	String curMess = "everybody"; // who the client is currently messaging
	
	@FXML
	private TextField c1; // send button
	
	@FXML
	private ListView<String> listItems2; // messages 
	
	@FXML
	private ListView<Button> listItems3; // clients on server
	
	@FXML
	private Text messText; // who the client is messaging
	
	// this method will create a list of users that are currently on the server
	public void initList(ArrayList<Integer> theList)
	{
		obsvList = FXCollections.observableArrayList();
		
		Platform.runLater(()->{listItems3.getItems().removeAll(obsvList);});
		Platform.runLater(()->{obsvList.clear();});
		
		for (Integer i: theList) // add items to list
		{
			
			Button b1 = new Button(i.toString());
			b1.setOnAction(chooseAction());
			
			Platform.runLater(()->{obsvList.add(b1);});
		}
		
		Button b2 = new Button("everybody");
		b2.setOnAction(chooseAction());
		
		Platform.runLater(()->{obsvList.add(b2);});
		
		Platform.runLater(()->{listItems3.setItems(obsvList);});
	}
	
	// this ActionEvent will be for choosing who to message
	public EventHandler<ActionEvent> chooseAction()
	{
		EventHandler<ActionEvent> chooseEvent = new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						Button b1 = (Button)event.getSource();
						curMess = b1.getText();
						
						messText.setText("The client you are currently messaging: "+ curMess);
						
					}
				};
		
		return chooseEvent;
	}
	
	// this ActionEvent sends the message to the server and who the message is for
	public void sendAction(ActionEvent event) throws IOException
	{
		SendMessageInfo data = new SendMessageInfo(c1.getText(), curMess);
		
		clientConnection.send(data);
		
		c1.clear();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		clientConnection = new Client(data->{
			
			message = (MessageInfo) data;
			
			if (message.getMessage().equals("connect") || message.getMessage().equals("disconnect"))
				initList(message.getList());
			
			else	
				Platform.runLater(()->{listItems2.getItems().add(message.getMessage());});	
		});
		
		clientConnection.start();
	}
	
}
