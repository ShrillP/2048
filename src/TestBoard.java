/**
 * Author: Shrill Patel
 * Revised: April 12, 2021
 * 
 * Description: Test class for the game board.
 */

package src;

import java.lang.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestBoard {

	private Board board;

	@Before 
	public void setUp() {
		board = new Board();
	}

	@After
	public void tearDown() {
		board = null;
	}

	@Test
	public void testGetBoard() {
		TileT[][] output = board.getBoard();
		int[][] expected = new int[][] {{0,0,0,0},
										{0,0,0,0},
										{0,0,0,0},
										{0,0,0,0}};
		for(int i = 0; i < output.length; i++) {
			for(int j = 0; j < output.length; j++) {
				assertTrue(expected[i][j] == output[i][j].getValue());
			}
		}
	}

	@Test 
	public void testGetScore1() {
		assertTrue(board.getScore() == 0);
	}

	@Test 
	public void testGetScore2() {
		board.setScore(10);
		assertTrue(board.getScore() == 10);
	}

	@Test 
	public void testGetBoardSize() {
		assertTrue(board.getBoardSize() == 4);
	}

	@Test
	public void testGetBoardValueAt1() {
		assertTrue(board.getBoardValueAt(0,3) == 0);
	}

	@Test
	public void testGetBoardValueAt2() {
		board.setBoardValueAt(new TileT(8, 1, 1));
		assertTrue(board.getBoardValueAt(1, 1) == 8);
	}

	@Test 
	public void testSetBoard() {
		TileT[][] arr = new TileT[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT((int) Math.pow(2, j), i, j);
			}
		}
		board.setBoard(arr);
		TileT[][] output = board.getBoard();
		int[][] expected = new int[][] {{1,2,4,8},
										{1,2,4,8},
										{1,2,4,8},
										{1,2,4,8}};
		for(int i = 0; i < output.length; i++) {
			for(int j = 0; j < output.length; j++) {
				assertTrue(expected[i][j] == output[i][j].getValue());
			}
		}
	}

	@Test 
	public void testSetScore1() {
		board.setScore(100);
		assertTrue(board.getScore() == 100);
	}

	@Test 
	public void testSetScore2() {
		board.setScore(0);
		assertTrue(board.getScore() == 0);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetScoreException() {
		board.setScore(-1);
	}

	@Test
	public void testSetBoardValueAt() {
		board.setBoardValueAt(new TileT(64, 3, 3));
		assertTrue(board.getBoardValueAt(3, 3) == 64);
	}
}