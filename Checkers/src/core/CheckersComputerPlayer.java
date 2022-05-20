/**Logic for the AI player
 * 
 * @author Zach Wood
 * @version 2.0
 *
 */
package core;


public class CheckersComputerPlayer {
	char ai = 'o';
	CheckersLogic game;
	public int count = 0;

	
	/** Constructor for creating a new ai player
	 * 
	 * @param session Current session that is calling for an ai player
	 * @param board Board that the player is on
	 */
	public CheckersComputerPlayer(CheckersLogic session, Board board){
		this.ai = 'o';
		this.game = session;
		
	}
	
	
	
	
	/**Finds a move for the ai player. Alternates between traversing through from up to
	 * down or vice versa so that it finds a move more random.
	 * It does make the ai jump if one is available before moving anywhere else.
	 * @param bd Board the ai is playing on.
	 */
	public void aiMove(Board bd) {

		label1:
			if(count % 2 == 0) {
				//If AI can Jump
				if(AIhasJump(bd)) {
					for(int i = 6; i > 0; i--) {
						for(int j = 6; j > 0; j--) {
							if(bd.board[i][j].value == ai) {
								//if has jump to the left
								if(game.hasJump(i,j, ai) == "right") {
									bd.board[i+1][j+1].value = '_';
									bd.board[i+2][j+2].value = ai;
									bd.board[i][j].value = '_';
									printMove(i,j,i+2,j+2,true);
									count++;
									break label1;
								}
								else if(game.hasJump(i,j, ai) == "left") {
									bd.board[i+1][j-1].value = '_';
									bd.board[i+2][j-2].value = ai;
									bd.board[i][j].value = '_';
									printMove(i,j,i+2,j-2,true);
									count++;
									break label1;
								}
							}
						}
					}
				}
				else {
					for(int i = 6; i > 0; i--) {
						for(int j = 6; j > 0; j--) {
							if(bd.board[i][j].value == ai) {
								//if left space is open
								if(bd.board[i+1][j-1].value == '_') {
									bd.board[i+1][j-1].value = ai;
									bd.board[i][j].value = '_';
									printMove(i,j,i+1,j-1,false);
									count++;
									break label1;
								}
								//if right space is open
								else if(bd.board[i+1][j+1].value == '_') {
									bd.board[i+1][j+1].value = ai;
									bd.board[i][j].value = '_';
									printMove(i,j,i+1,j+1,false);
									count++;
									break label1;
								}
							}
						}
					}
				}
			}
			else {
				//If AI can Jump
				if(AIhasJump(bd)) {
					for(int i = 1; i < 6; i++) {
						for(int j = 1; j < 6; j++) {
							if(bd.board[i][j].value == ai) {
								//if has jump to the left
								
								if(game.hasJump(i,j, ai) == "left") {
									bd.board[i+1][j-1].value = '_';
									bd.board[i+2][j-2].value = ai;
									bd.board[i][j].value = '_';
									printMove(i,j,i+2,j-2,true);
									count++;
									break label1;
								}
								else if(game.hasJump(i,j, ai) == "right") {
									bd.board[i+1][j+1].value = '_';
									bd.board[i+2][j+2].value = ai;
									bd.board[i][j].value = '_';
									printMove(i,j,i+2,j+2,true);
									count++;
									break label1;
								}
							}
						}
					}
				}
				else {
					for(int i = 1; i < 7; i++) {
						for(int j = 1; j < 7; j++) {
							if(bd.board[i][j].value == ai) {
								//if left space is open
								if(bd.board[i+1][j-1].value == '_') {
									bd.board[i+1][j-1].value = ai;
									bd.board[i][j].value = '_';
									printMove(i,j,i+1,j-1,false);
									count++;
									break label1;
								}
								//if right space is open
								else if(bd.board[i+1][j+1].value == '_') {
									bd.board[i+1][j+1].value = ai;
									bd.board[i][j].value = '_';
									printMove(i,j,i+1,j+1,false);
									count++;
									break label1;
								}
							}
						}
					}
				}
			}
	}
	
	/**Prints the beginning and ending location of what the ai moved. Also tells the player if
	 * the ai jumped or not.
	 * 
	 * @param i y startpoint
	 * @param j x startpoint
	 * @param next_i y endpoint
	 * @param next_j x endpoint
	 * @param jump if ai jumped.
	 */
	public void printMove(int i, int j, int next_i, int next_j, boolean jump){
		char char_1 = (char)(j + 65);
		char char_2 = (char)(next_j + 65);
		int num1 = 1+i;
		int num2 = next_i+1;
		if(jump)
			System.out.println("Computer Jumped one of your Checkers!!");
		System.out.println(char_1 + "-" + num1 + " to " + char_2 + "-" + num2);
	}
	
	
	/**Checks if the ai has an available jump on the board
	 * @param bd The board in use
	 * @return Returns true if ai has a jump available. False if not.
	 */
	public boolean AIhasJump(Board bd) {
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(bd.board[i][j].value == ai)
					if(game.hasJump(i, j, ai) == "right" || game.hasJump(i, j, ai) == "left") {
						return true;
					}
			}
		}
		return false;
	}
	

	
}
