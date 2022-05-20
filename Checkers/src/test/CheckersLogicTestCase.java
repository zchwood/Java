package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.*;


public class CheckersLogicTestCase {
	
	private static CheckersLogic checkersLogic;
	private CheckersLogic checkersLogic1;

	//private List emptyList;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		checkersLogic = new CheckersLogic();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		checkersLogic = null;
	}
	/**
	* Sets up the test fixture (Called before every test case method.)
	*/
	@Before
	public void setUp() throws Exception {
	//emptyList = new ArrayList();
		checkersLogic1 = new CheckersLogic();
		checkersLogic1.game.setBoard();
		
	}
	
	@After
	public void tearDown() throws Exception {
		checkersLogic1 = null;
	}
	
	@Test
	public void testSetUpBoard(){
		checkersLogic1.game.printBoard();
		//Checks if game is running after initialization
		assertEquals(true, checkersLogic1.isRunning());
		
		//Makes sure turn count is set at 0 and then increments
		assertEquals(0, checkersLogic1.getTurnCount());
		checkersLogic1.incrementTurn();
		assertEquals(1,checkersLogic1.getTurnCount());
		checkersLogic1.incrementTurn();
		
		
	}
	
	@Test
	public void testHasCheckers() {
		//Checks if each players checkers were created
		assertEquals(1, checkersLogic1.checkCheckers('x'));
		assertEquals(1, checkersLogic1.checkCheckers('o'));
	}
	
	@Test
	public void testUserhasMoves() {
		assertEquals(false, checkersLogic.userhasMoves('x'));
		assertEquals(false, checkersLogic.userhasMoves('o'));
	}

	@Test
	public void testUserInvalidMoves() {
		//Checks move method
		//Check wrong team
		checkersLogic1.userMove('x', 2, 1, 1, 0);
		checkersLogic1.incrementTurn();
		checkersLogic1.userMove('o', 2, 1, 1, 0);
		checkersLogic1.incrementTurn();

		//check invalid move
		//a6 b5
		checkersLogic1.userMove('x', 2, 1, 0, 0);
		checkersLogic1.incrementTurn();
		
		//d3 c4
		checkersLogic1.userMove('o', 2, 1, 0, 0);
		checkersLogic1.incrementTurn();
		
		
	}
	
	@Test
	public void testMoves() {
		//checkersLogic1.game.printBoard();
		checkersLogic1.userMove('x',5,0,4,1);
		assertEquals(true, checkersLogic1.userhasMoves('x'));
		assertEquals(true, checkersLogic1.userhasMoves('o'));
		checkersLogic1.userMove('o',2,3,3,2);
		assertEquals(true, checkersLogic1.userhasMoves('x'));
		assertEquals(true, checkersLogic1.userhasMoves('o'));
		checkersLogic1.userMove('x',4,1,3,2);
		assertEquals(true, checkersLogic1.userhasMoves('x'));
		assertEquals(true, checkersLogic1.userhasMoves('o'));
		checkersLogic1.userMove('o',1,2,2,3);
		assertEquals(true, checkersLogic1.userhasMoves('x'));
		assertEquals(true, checkersLogic1.userhasMoves('o'));
		checkersLogic1.userMove('x',5,2,4,3);
		assertEquals(true, checkersLogic1.userhasMoves('x'));
		assertEquals(true, checkersLogic1.userhasMoves('o'));
		checkersLogic1.userMove('o',3, 4,4,3);
		assertEquals(true, checkersLogic1.userhasMoves('x'));
		assertEquals(true, checkersLogic1.userhasMoves('o'));
		checkersLogic1.userMove('x', 6, 3, 5, 2);
		assertEquals(true, checkersLogic1.userhasMoves('x'));
		assertEquals(true, checkersLogic1.userhasMoves('o'));
		//checkersLogic1.game.printBoard();

	}
	
	@Test
	public void testXhasJump() {
		
		assertEquals("none", checkersLogic1.hasJump(0, 0,'x'));
		
		//setting up jump situation
		checkersLogic1.game.board[4][3].setvalue('o');
		//checkersLogic1.game.board[5][2].setvalue('i');
		
		assertEquals("right", checkersLogic1.hasJump(5, 2, 'x'));
		
		assertEquals("left", checkersLogic1.hasJump(5, 4, 'x'));
		
	}
	
	@Test
	public void testOhasJump() {
		assertEquals("none", checkersLogic1.hasJump(0, 0,'o'));
		
		
		checkersLogic1.game.board[3][4].value = 'x';
		//checkersLogic1.game.board[5][3].value = 'x';
		
		
		assertEquals("right", checkersLogic1.hasJump(2, 3, 'o'));
		assertEquals("left", checkersLogic1.hasJump(2, 5, 'o'));
	}

}
























