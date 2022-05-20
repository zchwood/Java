/** GUI controller for the checker game. 
 * @author Zach Wood
 * @version 2.0
 * 
 * */
package ui;
import core.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CheckersGUI extends Application {

	public static final int block_size = 100;
	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;
	//Text Variables
	char black = 'o';
	char red = 'x';
	String p1 = "Player 1";
	String p2 = "Player 2";
	String comp = "Computer Turn";
	String current_User =  p1;
	CheckersLogic game = new CheckersLogic();
	public Space[][] board = new Space[WIDTH][HEIGHT];
	public Group spaceGroup = new Group();
	public Group checkersGroup = new Group();
	public double startX, startY;
	public double endX, endY;
	Button startbutton = new Button("Start New Game");
	Button exit_button = new Button("Exit"); 
	Label user_label = new Label(current_User);
	
	

	
	/** Method to initialize the window
	 * @return null
	 */
	private Parent createBoard() {
		Pane pane = new Pane();
	    pane.setPrefSize(WIDTH * block_size, HEIGHT * block_size+100);
		pane.getChildren().addAll(spaceGroup, checkersGroup,startbutton, user_label, exit_button);
		//buttons and text
		startbutton.setLayoutX(WIDTH);
		startbutton.setLayoutY(block_size * HEIGHT + 20);
		user_label.setLayoutX(WIDTH * block_size / 2-100);
		user_label.setLayoutY(block_size * HEIGHT +20);
		user_label.setFont(Font.font( comp, FontWeight.BOLD, 42));
		exit_button.setLayoutX(WIDTH * block_size * 0.75);
		exit_button.setLayoutY(HEIGHT * block_size + 20);
		
		
		exit_button.setOnAction(e -> {
			Platform.exit();
		});
		
		
		
		
		

		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				Space space = new Space((x + y) % 2 == 0, x, y);
				spaceGroup.getChildren().add(space);
				board[x][y] = space;
				board[x][y].value = '_';
				
				Piece checker = null;
				
				// add checkers
				if(y < 3 && (x+y) % 2 != 0) {
					checker = makeChecker(black, x, y);
					board[x][y].value = black;
				}
				if(y > 4 && (x+y) % 2 != 0) {
					checker = makeChecker(red, x, y);
					board[x][y].value = red;
				}
				if(checker != null) {
					space.setChecker(checker);
					checkersGroup.getChildren().add(checker);
					
				}
			}
		}
		return pane;
	}
	
	
	
	/** Start method that sets the stage.
	 *
	 */
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) throws Exception {
		

		
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(createBoard());
	    primaryStage.setTitle("Zach's Very Basic but semi-functional Checkers Game"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	    
	    startbutton.setOnAction( __ ->
	    {
	      CheckersGUI app = new CheckersGUI();
	      try {
			app.start(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    } );

	}
	
	/** Converts the pixel location to the 2D location
	 * @param pixel pixel position of the selected checker
	 * @return Returns the new value of the converted index
	 */
	private int toBoard(double pixel) {
		return (int)(pixel + block_size / 2) / block_size;
	}

	/** Handles the movements and actions of the checkers
	 * @param user The current user
	 * @param x X coordinate to create the checker
	 * @param y Y coordinate to create the checker
	 * @return Returns the checker with the move rules.
	 */
	private Piece makeChecker(char user, int x, int y)  {
		Piece checker = new Piece(user, x , y);
		
		checker.setOnMouseReleased(e ->{
			int newX = toBoard(checker.getLayoutX());
			int newY = toBoard(checker.getLayoutY());
			
			int x0 = toBoard(checker.getX());
			int y0 = toBoard(checker.getY());
			
			if(hasMoves(checker) && isValid(newX,newY,checker) && board[newX][newY].hasChecker && current_User == checker.getUserString()) {
				
				if(checker.getUser() == black) {
					if(board[newX][newY].value == '_') {
						// left
						if(newX < x0) {
							if(board[newX + 1][newY - 1].value == red) {
								Piece opponent = board[newX + 1][newY - 1].getChecker();
								checker.move(newX, newY);
								board[x0][y0].setChecker(null);
								board[newX][newY].setChecker(checker);
								board[newX ][newY].setvalue(black);
								checkersGroup.getChildren().remove(opponent);
								board[newX +1][newY - 1].setChecker(null);
								board[newX+1][newY-1].setvalue('_');	
								next_Turn(current_User);
								user_label.setText(current_User);
							}
							
						}
						// right
						if(newX > x0) {
							if(board[newX - 1][newY - 1].value == red) {
								Piece opponent = board[newX - 1][newY - 1].getChecker();
								checker.move(newX, newY);
								board[x0][y0].setChecker(null);
								board[newX][newY].setChecker(checker);
								board[newX ][newY].setvalue(black);
								checkersGroup.getChildren().remove(opponent);
								board[newX -1][newY - 1].setChecker(null);
								board[newX-1][newY-1].setvalue('_');
								next_Turn(current_User);
								user_label.setText(current_User);
								
							}
						}
					}	
					
				}
				if(checker.getUser() == red) {
					if(board[newX][newY].value == '_') {
						// left
						if(newX < x0) {
							if(board[newX + 1][newY + 1].value == black) {
								Piece opponent = board[newX + 1][newY + 1].getChecker();
								checker.move(newX, newY);
								board[x0][y0].setChecker(null);
								board[newX][newY].setChecker(checker);
								board[newX ][newY].setvalue(red);
								checkersGroup.getChildren().remove(opponent);
								board[newX +1][newY + 1].setChecker(null);
								board[newX+1][newY+1].setvalue('_');
								next_Turn(current_User);
								user_label.setText(current_User);
								
							}
						}
						// right
						if(newX > x0) {
							if(board[newX-1][newY+1].value == black) {
								Piece opponent = board[newX - 1][newY + 1].getChecker();
								checker.move(newX, newY);
								board[x0][y0].setChecker(null);
								board[newX][newY].setChecker(checker);
								board[newX ][newY].setvalue(red);
								checkersGroup.getChildren().remove(opponent);
								board[newX -1][newY + 1].setChecker(null);
								board[newX-1][newY+1].setvalue('_');
								next_Turn(current_User);
								user_label.setText(current_User);
								
							}
						}
					}
				}
				
				if(board[newX][newY].value == '_') {
					checker.move(newX, newY);
					board[x0][y0].setChecker(null);
					board[newX][newY].setChecker(checker);
					board[x0][y0].setvalue('_');
					board[newX][newY].setvalue(checker.getUser());
					next_Turn(current_User);
					user_label.setText(current_User);
					
				}
				
			}
			else
				checker.cancel();
		});
		return checker;
	}
	
	
	/** Checks if the current selected checker has any available moves
	 * @param checker Current checker
	 * @return Returns true if moves are available, false if not
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private boolean hasMoves(Piece checker) throws ArrayIndexOutOfBoundsException{
		int x = toBoard(checker.getX());
		int y = toBoard(checker.getY());
		try {
			if(checker.getUser() == black) {
				if(board[x - 1][y + 1].value == '_' || board[x + 1][y + 1].value == '_') {
					return true;
				}
				if(board[x - 1][y + 1].value == 'x') {
					if(board[x - 2][y + 2].value == '_') {
						return true;
					}
				}
				if(board[x + 1][y + 1].value == 'x') {
					if(board[x + 2][y + 2].value == '_') {
						return true;
					}
				}
			}
			if(checker.getUser() == red) {
				if(board[x - 1][y - 1].value == '_' || board[x + 1][y - 1].value == '_') {
					return true;
				}
				if(board[x - 1][y - 1].value == 'o') {
					if(board[x - 2][y - 2].value == '_') {
						return true;
					}
				}
				if(board[x + 1][y - 1].value == 'o') {
					if(board[x + 2][y - 2].value == '_') {
						return true;
					}
				}
			}
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid Move");
		}
		return false;
	}
	
	
	/** Checks if selected checker move is Valid
	 * @param newX destination of checker (x value)
	 * @param newY destination of checker (y value)
	 * @param checker checker doing the move
	 * @return Returns true if the move is valid, false if invalid
	 */
	private boolean isValid(int newX, int newY, Piece checker) throws ArrayIndexOutOfBoundsException {
		int oldX = toBoard(checker.getX());
		int oldY = toBoard(checker.getY());
		try {
			if(checker.getUser() == black) {
				if(board[newX][newY].value == '_') {
					return true;
				}
				// going right
				if(oldX < newX) {
					if(board[newX][newY].value == red) {
						if(board[newX + 2][newY + 2].value == '_') {
							return true;
						}
					}
				}
				// going left
				if(oldX > newX) {
					if(board[newX][newY].value == red) {
						if(board[newX - 2][newY + 2].value == '_') {
							return true;
						}
					}
				}
			}
			if(checker.getUser() == red) {
				if(board[newX][newY].value == '_') {
					return true;
				}
				// going right
				if(oldX < newX) {
					if(board[newX][newY].value == black) {
						if(board[newX + 2][newY - 2].value == '_') {
							return true;
						}
					}
				}
				// going left
				if(oldX > newX) {
					if(board[newX][newY].value == black) {
						if(board[newX - 2][newY - 2].value == '_') {
							return true;
						}
					}
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid Move");
		}
		return true;
	}
	
	/** Switches turns of the current Player
	 * @param current_User2 The current Player
	 */
	public void next_Turn(String current_User2) {
		if(current_User2 == p1.toString()) {
			current_User = p2;
		}
		else
			current_User = p1;
	}
	


	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
} 