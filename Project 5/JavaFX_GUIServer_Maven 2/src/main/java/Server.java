/* Project 5 - GUI Server Improvement
 * Jose M. Aguilar (jaguil61)
 * 
 * This class handles sending and receiving stuff from the clients. Original code taken from class example. 
 * 
 * Server.java
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;


public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	
	
	
	Server(Consumer<Serializable> call){
	
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(5555);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
		
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			MessageInfo myMessage;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			public synchronized void updateAllClients(MessageInfo theMessage) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(theMessage);
					 //printClients(theMessage.getList());
					 /*System.out.println("Tried Sending To clients: " + theMessage.getMessage() + 
							 "\nFrom client: " + theMessage.getClientNum()); // for debugging purposes*/
					}
					catch(Exception e) {}
				}
			}
			
			// this method will decide which client to send the message to
			public synchronized void updateClient(MessageInfo theMessage, String toClient)
			{
				int clientNum = Integer.valueOf(toClient);
				int toIndex = findClient(clientNum); // index of who it's being sent to
				int fromIndex = findClient(count); // index of who it's from
				
				//System.out.println("fromIndex: " + fromIndex + " | toIndex: " + toIndex); // for debugging purposes
				
				if(toIndex != -1) // found a client thread with that name/count
				{
					// send it to both clients (from and to)
					ClientThread t = clients.get(toIndex);
					ClientThread t2 = clients.get(fromIndex);
					try {
					 t.out.writeObject(theMessage);
					 t2.out.writeObject(theMessage); 
					}
					catch(Exception e) {}
				}
				
				else // intended client was not on the server
				{
					ClientThread t2 = clients.get(fromIndex);
					try {
						MessageInfo failedMes = new MessageInfo("Client " + toClient + " is not online. Maybe message someone else?", count, updateList());
						t2.out.writeObject(failedMes); 
						}
						catch(Exception e) {}
				}
			}
			
			// this method will return the index of the client thread based on the specific client num that was passed into it
			public synchronized int findClient(int theClientNum)
			{
				int clientNum = -1;
				
				for(int i = 0; i < clients.size(); i++)
				{
					ClientThread t = clients.get(i);
					
					if (t.count == theClientNum) // found it
						clientNum = i;
					
					//else keep going
				}
				
				return clientNum;
			}
			
			// this method will create an updated the list of clients that are online so that all the clients know who's online
			public synchronized ArrayList<Integer> updateList()
			{
				ArrayList<Integer> clientList = new ArrayList<>();
				
				for(ClientThread i: clients)
					clientList.add(i.count);
				
				return clientList;
			}
			
			public void run(){
				
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
				myMessage = new MessageInfo("connect", count, updateList()); // new client connected
				updateAllClients(myMessage);
					
				 while(true) {
					    try {
					    	SendMessageInfo data = (SendMessageInfo) in.readObject();
					    	
					    	callback.accept("client: " + count + " sent: " + data.getMessage() + " to Client(s): " + data.getToClient());
					    	
					    	if (data.getToClient().equals("everybody")) // message received from client is intended for everybody
					    	{
						    	myMessage = new MessageInfo("client #" + count + " said: " + data.getMessage(), count, updateList());
						    	updateAllClients(myMessage);
					    	}
					    	
					    	else // message received from client was intended for only one client
					    	{
					    		myMessage = new MessageInfo("client #" + count + " said: " + data.getMessage(), count, updateList());
					    		updateClient(myMessage, data.getToClient());
					    	}	
					    }
					    
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");				
					    	clients.remove(this);				
					    	myMessage = new MessageInfo("disconnect", count, updateList()); // client disconnected
					    	updateAllClients(myMessage);
					    	
					    	break;
					    }
					}
				}//end of run
	
				
			/*// this method is for debugging purposes
			public void printClients(ArrayList<Integer> theClientNums)
			{
				System.out.println("Online Clients:");
				
				for (Integer i: theClientNums)
					System.out.println("Client " + i + ": Online");
				
				System.out.println();
			}*/
			
			
		}//end of client thread
}


	
	

	
