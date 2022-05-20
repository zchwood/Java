/** All of the logic for the Checkers game
 * 
 * 
 * @author Zach Wood
 * @version 2.0
 * 
 * */
package core;


public class CheckersLogic {
	

	/** Boolean to hold the value to if their is a winner
	 * 
	 */
	public boolean winner = false;
	
	/** Number of turns
	 * 
	 */
	public int turn = 0;
	public Board game;
	
	
	
	/** Constructor for the checkers logic
	 * 
	 */
	public CheckersLogic(){
		game = new Board();
	}
	
	/** function that returns whether it is player 1's turn
	 * 
	 * @return boolean
	 */
	public boolean player1Turn() {
		if(turn % 2 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Returns the current Turn count
	 * 
	 * @return turn count
	 */
	public int getTurnCount() {
		return turn;
	}
	
	/** Checks if their is a winner
	 * 
	 * @return boolean
	 */
	public boolean isRunning(){
		if(winner == false) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Increments the turn count by 1
	 * 
	 */
	public void incrementTurn() {
		turn++;
	}
	
	/** Ends the current session
	 * @param user current user
	 */
	public void gameOver(char user) {
		winner = true;
	}
	
	/** Iterates through the checker board counting current users checker count 
	 * 
	 * @param user current user
	 * @return Returns 1 if user has checkers, Returns 0 if user is out and changes winner boolean to true
	 */
	public int checkCheckers(char user) {
		int count = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(game.board[i][j].value == user) {
					count++;
				}
			}
		}
		if(count == 0) {
			winner = true;
			return 0;
		}
		return 1;
	}
	
	
	/** Checks whether the user has any moves available
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean userhasMoves(char user) {
		char opponent;
		if(user == 'x') {
			opponent = 'o';
		}
		else
			opponent = 'x';
		
		if(user == 'x') {
			for(int row = 0; row < 8; row++) {
				for(int col = 0; col < 8; col++) {
					if(row < 7 && row > 0 && col > 0 && col < 7) {
						if(game.board[row][col].hasChecker && game.board[row][col].value == user) {
							if(game.board[row-1][col-1].value == '_' || game.board[row-1][col+1].value == '_') {
								return true;
							}
							if(game.board[row-1][col-1].value == opponent) {
								if(game.board[row-2][col-2].value == '_') {
									return true;
								}
							}
							if(game.board[row-1][col+1].value == opponent) {
								if(game.board[row-2][row+2].value == '_') {
									return true;
								}
							}
						}
					}
				}
			}
		}
		if(user == 'o') {
			for(int row = 0; row < 8; row++) {
				for(int col = 0; col < 8; col++) {
					if(row < 7 && row > 0 && col > 0 && col < 7) {
						if(game.board[row][col].hasChecker && game.board[row][col].value == user) {
							if(game.board[row+1][col-1].value == '_' || game.board[row+1][col+1].value == '_') {
								return true;
							}
							if(game.board[row+1][col-1].value == opponent) {
								if(game.board[row+2][col-2].value == '_') {
									return true;
								}
							}
							if(game.board[row+1][col+1].value == opponent) {
								if(game.board[row+2][row+2].value == '_') {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	
	/** Takes the coordinates of the users checker piece selected followed by the coordinates of their move,
	 * also checks if the move is a jump and jumps if so.
	 * 
	 * @param user The current user moving
	 * @param row1 
	 * @param col1
	 * @param row2
	 * @param col2
	 */
	public void userMove(char user, int row1, int col1, int row2, int col2) {
		//System.out.println("user : " + user + "  " + row1+","+col1+","+row2+","+col2);
		char opponent;
		if(user == 'x') {
			opponent = 'o';
		}
		else
			opponent = 'x';
		if(row2 < 8 && row2 > 0 && col2 >= 0 && col2 < 8) {
			if(game.board[row1][col1].value != user) {
				System.out.println("Wrong Team!!");
			}
			else if(game.board[row1][col1].value == user) {
				if(game.board[row2][col2].value == '_') {
					game.board[row2][col2].value = user;
					game.board[row1][col1].value = '_';
				}
				if(user == 'x') {
					if(game.board[row2][col2].value == opponent && col2 -1 >= 0) {
						if(game.board[row2-1][col2-1].value == '_' && col1 > col2) {
							game.board[row2-1][col2-1].value = user;
							game.board[row2][col2].value = '_';
							game.board[row1][col1].value = '_';
						}
						else if(game.board[row2-1][col2+1].value == '_' && col1 < col2 && col2 + 1 <= 7) {
							game.board[row2-1][col2+1].value = user;
							game.board[row2][col2].value = '_';
							game.board[row1][col1].value = '_';
						}
					}
					
				}
				if(user == 'o') {
					if(game.board[row2][col2].value == opponent) {
						if(game.board[row2+1][col2+1].value == '_' && col1 < col2) {
							game.board[row2+1][col2+1].value = user;
							game.board[row2][col2].value = '_';
							game.board[row1][col1].value = '_';
						}
						else if(game.board[row2+1][col2 - 1].value == '_' && col1 > col2) {
							game.board[row2+1][col2-1].value = user;
							game.board[row2][col2].value = '_';
							game.board[row1][col1].value = '_';
						}
					}
				}
				
			}
			
		}
		else
			System.out.println("invalid move");
	
	}
	
	/** Checks to see whether the checker at selected coordinate has a jump available. Was made but not implemented
	 * but could be for a rule where you have to take a jump if there is one.
	 * 
	 * @param i x coordinate of checker
	 * @param j y coordinate of checker
	 * @return Returns string "left" "right" or "none" depending on whether there is a jump
	 */
	public String hasJump(int i, int j, char user) {
		char opponent;
		if(user == 'x') {
			opponent = 'o';
		}
		else
			opponent = 'x';
		if(user == 'x') {
			if(j - 2 >= 0 && j + 2 <= 7) { 
				if(game.board[i-1][j-1].value == opponent && game.board[i-2][j-2].value == '_') {
					return "left";
				}
				else if(game.board[i-1][j+1].value == opponent && game.board[i-2][j+2].value == '_') {
					return "right";
				}
			}
		}
		else {
			if(j - 2 >= 0 && j + 2 <= 7) {
				if(game.board[i+1][j-1].value == opponent && game.board[i+2][j-2].value == '_') {
					return "left";
				}
				else if(game.board[i+1][j+1].value == opponent && game.board[i+2][j+2].value == '_') {
					return "right";
				}
			}
		}
		return "none";

	}
	
	
	
	
}
