package test;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.*;


public class CheckersComputerPlayerTest {
	
	private static CheckersLogic checkersLogic;
	private CheckersLogic checkersLogic1;
	private CheckersComputerPlayer AI;

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
		AI = new CheckersComputerPlayer(checkersLogic1, checkersLogic1.game);
	}

	@After
	public void tearDown() throws Exception {
		checkersLogic1 = null;
	}


	@Test
	public void testAiMove() {
		
		AI.aiMove(checkersLogic1.game);
		
		checkersLogic1.game.board[3][2].setvalue('x');
		checkersLogic1.game.board[3][6].setvalue('x');
		
		AI.aiMove(checkersLogic1.game);
		
		
		
		
		AI.aiMove(checkersLogic1.game);
		AI.aiMove(checkersLogic1.game);
		AI.aiMove(checkersLogic1.game);
		AI.aiMove(checkersLogic1.game);
		AI.aiMove(checkersLogic1.game);
	
		AI.aiMove(checkersLogic1.game);
		AI.aiMove(checkersLogic1.game);
		checkersLogic1.game.board[6][1].setvalue('_');
		checkersLogic1.game.board[6][3].setvalue('_');
		checkersLogic1.game.board[6][5].setvalue('_');
		checkersLogic1.game.board[6][7].setvalue('_');
		AI.aiMove(checkersLogic1.game);
		AI.aiMove(checkersLogic1.game);
		AI.aiMove(checkersLogic1.game);
		

	}
	
	@Test
	public void testAihasJump() {
		assertFalse(AI.AIhasJump(checkersLogic1.game));
		checkersLogic1.game.board[3][2].setvalue('x');
		assertTrue(AI.AIhasJump(checkersLogic1.game));
	}
	
	@Test
	public void testPrintMove() {
		AI.printMove(0, 0, 0, 0, false);
		AI.printMove(0, 0, 0, 0, true);
	}
	
}














