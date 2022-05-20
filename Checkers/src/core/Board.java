/** The Board class handles all of the components of the board from
 * creating one to printing it out for the users.
 * 
 * @author Zach Wood
 * @version 2.0
 * 
 * */
package core;

public class Board {

	/** A 2D array of spaces creating a checker board
	 * 
	 */
	public Space[][] board = new Space[8][8];

	
	/** Constructor for creating a 8x8 board with empty squares
	 * 
	 */
	public Board() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				this.board[i][j] = new Space();
				if(i % 2 == j % 2) {
					this.board[i][j].hasChecker = false;
					
				}
			}
		}
	}
	
	
	/** Prints the current state of the board with axis labels
	 * 
	 */
	public void printBoard() {
		int a = 1;
		for(int i = -1; i < 8; i++) {
			for(int j = -1; j < 8; j++) {
				if(j == -1 && i != -1 && a > 0) {
					System.out.print((a++) + "|");
				}
				if(i != -1 && j != -1) {
					System.out.print(board[i][j].getvalue());
					System.out.print("|");
				}

			}
			System.out.println();
			

		}
		System.out.println("\n  A B C D E F G H\n");
	}
	

	
	/** Initializes the board with the appropriate checkers x and o in their starting positions
	 * 
	 */
	public void setBoard() {
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j].hasChecker) {
					if(i < 3) {
						board[i][j].value = 'o';
					}
					else if(i > 4) {
						board[i][j].value = 'x';
					}
				}
			}
		}
		
	}
	
}
