
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Class that starts a session connection to the Rock Paper Scissors Server
 * @author Zach Wood
 *
 */
public class RPSClient extends Application{
	private static String HOST = "localhost";
	private static int PORT = 8000;
	private int result = -1;
	private int player1 = 0;
	private int player2 = 0;
	private Label topLabel;
	private Label bottomLabel;
	private HBox buttonGroup;
	
	
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	


	/** JavaFX start method for the GUI
	 *
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//Create scene
		
		VBox game = new VBox();
		
		buttonGroup = new HBox();
		topLabel = new Label("Waiting for Start");
		topLabel.setStyle("-fx-font-size:30; -fx-font-weight:bold");
		bottomLabel = new Label("Let's Play!");
		bottomLabel.setStyle("-fx-font-size:30; -fx-font-weight:bold");
		Label scoreLabel = new Label("You : " + player1 + "   Opponent : " + player2 );
		//turn_Label.setStyle("-fx-font-size:50; -fx-font-weight: bold");
		Button rock_btn = new Button("ROCK");
		Button paper_btn = new Button("PAPER");
		Button scissors_btn = new Button("SCISSORS");
		BorderPane borderpane = new BorderPane();
		buttonGroup.getChildren().addAll(rock_btn,paper_btn,scissors_btn);
		buttonGroup.setAlignment(Pos.CENTER);
		buttonGroup.setSpacing(20);
		game.getChildren().addAll(scoreLabel, buttonGroup);
		game.setAlignment(Pos.CENTER);
		game.setSpacing(50);
		borderpane.setTop(topLabel);
		borderpane.setAlignment(topLabel, Pos.CENTER);
		//borderpane.setAlignment(topLabel, Pos.CENTER);
		borderpane.setCenter(game);
		borderpane.setBottom(bottomLabel);
		borderpane.setAlignment(bottomLabel, Pos.CENTER);
		Scene scene = new Scene(borderpane, 300, 300);
		primaryStage.setTitle("Zach's Simple Rock Paper Scissors");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Try connecting to the Server
		try {
			Socket socket = new Socket(HOST,PORT);
			toServer = new DataOutputStream(socket.getOutputStream());
			fromServer = new DataInputStream(socket.getInputStream());
			
			rock_btn.setOnAction(e ->{
				try {
					toServer.writeInt(1);
					toServer.flush();
					fromServer.skipBytes(3);
					result = fromServer.read();
					topLabel.setText(changeResult(result));
					scoreLabel.setText("You : " + player1 + "   Opponent : " + player2 );
					checkWinner();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			});
			paper_btn.setOnAction(e ->{
				try {
					toServer.writeInt(2);
					toServer.flush();
					fromServer.skipBytes(3);
					result = fromServer.read();
					topLabel.setText(changeResult(result));
					scoreLabel.setText("You : " + player1 + "   Opponent : " + player2 );
					checkWinner();
				}
				catch(IOException e2) {
					e2.printStackTrace();
				}
			});
			scissors_btn.setOnAction(e ->{
				try {
					toServer.writeInt(3);
					toServer.flush();
					fromServer.skipBytes(3);
					result = fromServer.read();
					topLabel.setText(changeResult(result));
					scoreLabel.setText("You : " + player1 + "   Opponent : " + player2 );
					checkWinner();
				}
				catch(IOException e3) {
					e3.printStackTrace();
				}
			});
			
			if(result == 0) {
				topLabel.setText("DRAW");
				
			}
		}
		catch(IOException e){
			System.out.println("Connection Refused. Check Server");
		}
	}
	
	
	/** Changes the result from the server to a string for the GUI to display
	 * @param result_int Result from server
	 * @return
	 */
	private String changeResult(int result_int) {
		String result = null;
		if(result_int == 0) {
			result = "DRAW";
		}
		else if(result_int == 1) {
			result = "You Win";
			player1++;
		}
		else if(result_int == 2) {
			result = "You Lose";
			player2++;
		}
		return result;
	}
	
	/** Checks if the server has declared a winner yet
	 * 
	 */
	private void checkWinner() {
		if(player1 > 4) {
			topLabel.setText("GAME OVER");
			bottomLabel.setText("You Win!");
			buttonGroup.setVisible(false);
			
		}
		else if(player2 > 4) {
			topLabel.setText("GAME OVER");
			bottomLabel.setText("You Lose!");
			buttonGroup.setVisible(false);
		}
	}


	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}