/** Server that manages multiple Rock Paper Scissors games 
 * @author Zach Wood
 * @version 2.0
 * 
 * */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Server_v2 {
	private static String HOST = "localhost";
	private static int PORT = 8000;
	private int client_num;
	
	/**Constructor that creates a new server instance
	 * 
	 */
	public Server_v2() {
		
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Running on port " + PORT);
			
			client_num = 0;
			
			while (true) {
				Socket player1_socket = server.accept();
				System.out.println("Player 1 connected from " + player1_socket.getInetAddress()+":"+player1_socket.getLocalPort()+" on " + new Date());
				Socket player2_socket = server.accept();
				System.out.println("Player 2 connected from " + player2_socket.getInetAddress()+":"+player2_socket.getLocalPort()+" on " + new Date());
				
				//Create Thread for match
				HandleMatch match = new HandleMatch(player1_socket, player2_socket);
				new Thread(match).start();
				client_num = client_num+ 2;
				System.out.println("Match started on new thread...");
				System.out.println("Number of Players currently on server : " + client_num);
			}
			
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**Inner Class to handle matches
	 * 
	 *
	 */
	class HandleMatch implements Runnable{
		private Socket player1_socket;
		private Socket player2_socket;
		private int player1_score = 0;
		private int player2_score = 0;
		private int result = -1;
		//Thread constructor
		public HandleMatch(Socket player1, Socket player2) {
			this.player1_socket = player1;
			this.player2_socket = player2;
		}
		
		public void run() {
			try {
				//PLayer 1 Data Streams
				DataInputStream fromPlayer1 = new DataInputStream(player1_socket.getInputStream());
				DataOutputStream toPlayer1 = new DataOutputStream(player1_socket.getOutputStream());
				
				//Player 2 Data Streams
				DataInputStream fromPlayer2 = new DataInputStream(player2_socket.getInputStream());
				DataOutputStream toPlayer2 = new DataOutputStream(player2_socket.getOutputStream());
				
				//while(player1_score <= 6 || player2_score <= 6) {
				while(player1_score < 5 && player2_score < 5) {
				
					//Recieve play from player 1
					int player1_move = fromPlayer1.readInt();
					
					
					//Recieve play from player 2
					int player2_move = fromPlayer2.readInt();
					
					//Game Logic
					if(player1_move == player2_move) {
						result = 0;
						System.out.println("Player1 move : " + player1_move + "\nPlayer2 move : " + player2_move);
						
					}
					
					else if(player1_move == 1 && player2_move == 2) {
						result = 2;
						System.out.println("Player1 move : " + player1_move + "\nPlayer2 move : " + player2_move);
						player2_score++;
					}
					
					else if(player1_move == 1 && player2_move == 3) {
						result = 1;
						System.out.println("Player1 move : " + player1_move + "\nPlayer2 move : " + player2_move);
						player1_score++;
					}
					
					else if(player1_move == 2 && player2_move == 1) {
						result = 1;
						System.out.println("Player1 move : " + player1_move + "\nPlayer2 move : " + player2_move);
						player1_score++;
					}
					
					else if(player1_move == 2 && player2_move == 3) {
						result = 2;
						System.out.println("Player1 move : " + player1_move + "\nPlayer2 move : " + player2_move);
						player2_score++;
					}
					
					else if(player1_move == 3 && player2_move == 1) {
						result = 2;
						System.out.println("Player1 move : " + player1_move + "\nPlayer2 move : " + player2_move);
						player2_score++;
					}
					
					else if(player1_move == 3 && player2_move == 2) {
						result = 1;
						System.out.println("Player1 move : " + player1_move + "\nPlayer2 move : " + player2_move);
						player1_score++;
					}
					
					if(result == 0) {
						toPlayer1.writeInt(0);
						toPlayer2.writeInt(0);
					}
					if(result == 1) {
						toPlayer1.writeInt(1);
						toPlayer2.writeInt(2);
					}
					if(result == 2) {
						toPlayer1.writeInt(2);
						toPlayer2.writeInt(1);
					}

				}
				
				
				player1_socket.close();
				player2_socket.close();
				client_num = client_num - 2;
				
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**Main method 
	 * @param args
	 */
	public static void main(String args[]) {
		new Server_v2();
	}
}
