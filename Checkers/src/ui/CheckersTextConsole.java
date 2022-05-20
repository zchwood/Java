/** Console logic for checkers game handling all the ui 
 * @author Zach Wood
 * @version 2.0
 * 
 * */
package ui;
import java.util.Scanner;
import core.*;
public class CheckersTextConsole {
	
	/** Designates player 1 as 'x'
	 * 
	 */
	public static char player1 = 'x';
	
	/** Designates player 2 as 'o'
	 * 
	 */
	public static char player2 = 'o';
	public static char ai = 'o';
	public static Scanner sc = new Scanner(System.in);
	
	
	
	
	

	/** Main Method creates checkers session and loops through until a winner is found
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("*******************Welcome to Checkers!!!**********************\n\nPlayers: 2");
		System.out.println("\nType GUI for the Graphical Checkers Game or Press Enter to continue to the console : ");
		String gui_ans = sc.nextLine();
		if(gui_ans.equalsIgnoreCase("gui")) {
			CheckersGUI.main(args);
		}
		System.out.println("To enter coordinates, enter [Letter][Number], dont worry about case sensitivity!");
		System.out.println("ex. A3 or g2");
		System.out.println("Play against a computer?  [yes, no]  : ");
		String ans = sc.nextLine();
		System.out.println(ans);
		if(ans.equalsIgnoreCase("no")) {

			CheckersLogic session = new CheckersLogic();
			session.game.setBoard();

			//session.game.printBoardtest();
			while(session.isRunning()) {
				if(session.player1Turn()) {
					if(!session.userhasMoves(player1) || session.checkCheckers(player1) < 1) {
						System.out.print("********************Game over! Player 2 wins********************");
						break;
					}
					session.game.printBoard();
					System.out.println("Player 1 Turn!    You are 'X'");
					int[] user = getInput(session, player1);
					session.userMove(player1, user[0],user[1],user[2],user[3]);
					session.incrementTurn();
				}
				else if(!session.player1Turn()) {
					if(!session.userhasMoves(player2) || session.checkCheckers(player2) < 1) {
						System.out.print("********************Game over! Player 1 wins********************");
					}
					session.game.printBoard();
					System.out.println("Player 2 Turn!    You are 'O'");
					int[] user = getInput(session, player2);
					session.userMove(player2, user[0],user[1],user[2],user[3]);
					session.incrementTurn();

				}
			}
		}
		else {
			CheckersLogic session = new CheckersLogic();
			CheckersComputerPlayer AI = new CheckersComputerPlayer(session, session.game);
			session.game.setBoard();
			while(session.isRunning()) {
				if(session.player1Turn()) {
					if(!session.userhasMoves(player1) || session.checkCheckers(player1) < 1) {
						System.out.print("********************Game over! Computer wins********************");
						break;
					}
					session.game.printBoard();
					System.out.println("Player 1 Turn!   You are 'X'");
					int[] user = getInput(session, player1);
					session.userMove(player1, user[0], user[1], user[2], user[3]);
					session.incrementTurn();
					
				}
				else if(!session.player1Turn()) {
					if(!session.userhasMoves(ai) || session.checkCheckers(ai) < 1) {
						System.out.println("****************Game over! You Win!!*************************");
						break;
					}
					System.out.println("Computer Playing...");
					AI.aiMove(session.game);
					session.incrementTurn();
				}
			}
		}
	
	}

	/** Gets the input of the users selected checker and destination in form of char and int ex. A3 & B4
	 * 
	 * @return Returns the array of the two coordinates
	 */
	public static int[] getInput(CheckersLogic session, char user) {

		while(true) {
			String data;
			int[] final_data = new int[4];

			try {
				System.out.println();
				System.out.println("Select a Checker to move  (Letter,Number)");
				data = sc.nextLine().toLowerCase();
				System.out.println("Select a Location to move or jump (Letter,Number)");

				data += sc.nextLine().toLowerCase();
				final_data[0] = data.charAt(1) - 46 - 3;
				final_data[1] = data.charAt(0) - 97;
				final_data[2] = data.charAt(3) - 46 - 3;
				final_data[3] = data.charAt(2) - 97;

				if((data.charAt(0) < 'a' || data.charAt(0) > 'z' || Character.getNumericValue(data.charAt(1)) < 1 || Character.getNumericValue(data.charAt(1)) > 8) && session.game.board[final_data[0]][final_data[1]].value == user){
					System.out.println("********************************Invalid Coordinates***************************");
					throw new Exception();
				}
				if(data.charAt(2) < 'a' || data.charAt(2) > 'z' || Character.getNumericValue(data.charAt(3)) < 1|| Character.getNumericValue(data.charAt(3)) > 8) {
					System.out.println("********************************Invalid Destination***************************");
					throw new Exception();
				}
				if(session.game.board[final_data[0]][final_data[1]].value != user) {
					System.out.println("********************************Wrong Team!!!!***************************");
					throw new Exception();
				}
				else if(!session.game.board[final_data[0]][final_data[1]].hasChecker || !session.game.board[final_data[2]][final_data[3]].hasChecker) {
					System.out.println("*******************************Invalid Square*****************************");
					throw new Exception();
				}
				else {
					return final_data;
				}
			}
			catch(Exception e){
				System.out.println("\n********************************Try Again***************************");
			}  

		}
	}

}
