/** Class that handles checker piece creation. 
 * @author Zach Wood
 * @version 2.0
 * 
 * */
package core;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.CheckersGUI;
import static ui.CheckersGUI.block_size;

public class Piece extends StackPane{
	
	private char user;
	private double mouseX, mouseY, oldX, oldY;
	
	/** Default constructor of a blank space.
	 * 
	 */
	public Piece() {
		user = '_';
	}
	
	/** Constructor that creates the correct checker for the user
	 * @param user1 User of the checker being created
	 * @param x x coordinate of the created checker
	 * @param y y coordinate of the created checker
	 */
	public Piece(char user1, int x, int y) {
		user = user1;
		
		move(x, y);
		
		Circle checker = new Circle(CheckersGUI.block_size/3);
		if(user == 'x') {
			checker.setFill(Color.RED);
		}
		if(user == 'o') {
			checker.setFill(Color.GREY);
		}
		checker.setTranslateX(block_size / 6);
		checker.setTranslateY(block_size / 6);
		
		getChildren().addAll(checker);
		
		setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
		
		setOnMouseDragged(e ->{
			relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
		});
	}
	
	/** Moves the checker
	 * @param x New destination of x coordinate
	 * @param y New destination of y coordinate
	 */
	public void move(int x, int y) {
		oldX = x * block_size;
		oldY = y * block_size;
		relocate(oldX, oldY);
	}
	
	/** Getter for obtaining the user of the checker
	 * @return Returns red or black
	 */
	public char getUser() {
		return user;
	}
	
	/** Sets the user for the checker
	 * @param user 
	 */
	public void setUser(char user) {
		this.user = user;
	}
	
	/** Gets starting x coordinate of checker
	 * @return x Coordinate
	 */
	public double getX() {
		return oldX;
	}
	
	/** Gets starting y coordinate of checker
	 * @return y Coordinate
	 */
	public double getY() {
		return oldY;
	}
	
	/** Cancels move if invalid
	 * 
	 */
	public void cancel() {
		relocate(oldX, oldY);
	}
	
	/** Returns the user String for the label
	 * @return String
	 */
	public String getUserString() {
		if(this.getUser() == 'x') {
			return "Player 1";
		}
		else
			return "Player 2";
	}
	
}
