/** This class is used as each space on the checkers board, whether it has a checker or not
 * @author Zach Wood
 * @version 2.0
 * 
 * */
package core;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.CheckersGUI;

public class Space extends Rectangle{

	/** x for red o for black and _ for empty */
	public char value;
	/** boolean checker to see if block can have checker */
	public boolean hasChecker = true;
	private Piece checker;
	
	
	
	/**
	 * constructor for space class that initially creates all empty blocks 
	 */
	public Space(){
		
		this.value = '_';
		setWidth(CheckersGUI.block_size);
		setHeight(CheckersGUI.block_size);
		
		
	}
	
	
	/** Gets the value of the Space
	 * 
	 * @return char value of block
	 */
	public char getvalue() {
		return this.value;
	}
	
	public void setvalue(char value) {
		this.value = value;
	}
	
	public Space(boolean light, int x, int y) {
		setWidth(CheckersGUI.block_size);
		setHeight(CheckersGUI.block_size);
		
		relocate(x* CheckersGUI.block_size, y * CheckersGUI.block_size);
		setFill(light ? Color.valueOf("#DEB887") : Color.valueOf("#000000"));
		hasChecker = !light;
		
	}
	
	public void setChecker(Piece checker) {
		this.checker = checker;
		
	}
	
	public boolean hasPiece() {
		return checker != null;
	}
	
	public Piece getChecker() {
		return checker;
	}
}

