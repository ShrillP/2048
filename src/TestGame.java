/**
 * Author: Shrill Patel
 * Revised: April 12, 2021
 * 
 * Description: Testing the logic behind the game (model).
 * The controller is not being tested because it directly uses 
 * the functions in the model class for conducting logical game
 * moves. The individual shift and merge methods are not tested 
 * because the make move method uses all of them, so testing that 
 * method alone will test all shift and merge methods.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestGame {

	private Model m;
	private TileT[][] arr;

	@Before
	public void setUp() {
		m = new Model();
		arr = arr = new TileT[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT((int) Math.pow(2, j), i, j);
			}
		}
	}

	@After
	public void tearDown() {
		m = null;
	}

	@Test 
	public void testGameSetUp() {
		int tiles = 0;
		TileT[][] arr = m.getBoard().getBoard();
		for(int i = 0; i < m.getBoard().getBoardSize(); i++) {
			for(int j = 0; j < m.getBoard().getBoardSize(); j++) {
				if(arr[i][j].getValue() != 0)
					tiles++;
			}
		}
		assertTrue(m.getBoard().getScore() == 0);
		assertTrue(tiles == 2);
	}

	@Test
	public void testGetBoard() {
		m.getBoard().setBoard(arr);
		TileT[][] result = m.getBoard().getBoard();
		int[][] expected = new int[][] {{1,2,4,8},
										{1,2,4,8},
										{1,2,4,8},
										{1,2,4,8}};
		for(int i = 0; i < result.length; i++) {
			for(int j = 0; j < result.length; j++) {
				assertTrue(expected[i][j] == result[i][j].getValue());
			}
		}
	}

	@Test
	public void testCanMakeMove() {
		for(int i = 0; i < 4; i++) {
			arr[i][0] = new TileT(0, i, 0);
		}
		assertTrue(m.canMakeMove(arr));
	}

	@Test
	public void testDoes2048Exist1() {
		assertFalse(m.does2048Exist());
	}

	@Test
	public void testDoes2048Exist2() {
		arr[1][2] = new TileT(2048, 1, 2);
		m.getBoard().setBoard(arr);
		assertTrue(m.does2048Exist());
	}

	@Test
	public void testIsGameOver() {
		assertFalse(m.isGameOver());
	}

	@Test
	public void testMakeMoveNotPossible1() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT(0, i, j);
			}
		}
		arr[3][0] = new TileT(2, 3, 0);
		arr[3][1] = new TileT(4, 3, 1);
		m.getBoard().setBoard(arr);
		m.makeMove(Directions.left);
		int[][] expected = new int[][] {{0,0,0,0},
										{0,0,0,0},
										{0,0,0,0},
										{2,4,0,0}};
		TileT[][] out = m.getBoard().getBoard();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				assertTrue(expected[i][j] == out[i][j].getValue());
			}
		}

	}

	@Test
	public void testMakeMoveNotPossible2() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT(0, i, j);
			}
		}
		arr[3][3] = new TileT(2, 3, 3);
		arr[3][2] = new TileT(4, 3, 2);
		m.getBoard().setBoard(arr);
		m.makeMove(Directions.right);
		int[][] expected = new int[][] {{0,0,0,0},
										{0,0,0,0},
										{0,0,0,0},
										{0,0,4,2}};
		TileT[][] out = m.getBoard().getBoard();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				assertTrue(expected[i][j] == out[i][j].getValue());
			}
		}

	}

	@Test
	public void testMakeMoveNotPossible3() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT(0, i, j);
			}
		}
		arr[0][3] = new TileT(2, 0, 3);
		arr[1][3] = new TileT(4, 1, 3);
		m.getBoard().setBoard(arr);
		m.makeMove(Directions.up);
		int[][] expected = new int[][] {{0,0,0,2},
										{0,0,0,4},
										{0,0,0,0},
										{0,0,0,0}};
		TileT[][] out = m.getBoard().getBoard();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				assertTrue(expected[i][j] == out[i][j].getValue());
			}
		}

	}

	@Test
	public void testMakeMoveNotPossible4() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT(0, i, j);
			}
		}
		arr[3][1] = new TileT(2, 3, 1);
		arr[2][1] = new TileT(4, 2, 1);
		m.getBoard().setBoard(arr);
		m.makeMove(Directions.down);
		int[][] expected = new int[][] {{0,0,0,0},
										{0,0,0,0},
										{0,4,0,0},
										{0,2,0,0}};
		TileT[][] out = m.getBoard().getBoard();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				assertTrue(expected[i][j] == out[i][j].getValue());
			}
		}

	}

	@Test
	public void testMakeMove1() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT(0, i, j);
			}
		}
		arr[0][0] = new TileT(2, 0, 0);
		arr[1][3] = new TileT(4, 1, 3);
		arr[2][1] = new TileT(4, 2, 1);
		arr[3][0] = new TileT(2, 3, 0);
		arr[3][3] = new TileT(2, 3, 3);
		m.getBoard().setBoard(arr);
		m.makeMove(Directions.left);
		int[][] expected = new int[][] {{2,0,0,0},
										{4,0,0,0},
										{4,0,0,0},
										{4,0,0,0}};
		TileT[][] out = m.getBoard().getBoard();
		int diff = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(expected[i][j] != out[i][j].getValue()) {
						diff++;
				} else {
					assertTrue(expected[i][j] == out[i][j].getValue());
				}
			}
		}
		assertTrue(diff == 1);
	}

	@Test
	public void testMakeMove2() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT(0, i, j);
			}
		}
		arr[0][0] = new TileT(2, 0, 0);
		arr[1][3] = new TileT(4, 1, 3);
		arr[2][1] = new TileT(4, 2, 1);
		arr[3][0] = new TileT(2, 3, 0);
		arr[3][3] = new TileT(2, 3, 3);
		m.getBoard().setBoard(arr);
		m.makeMove(Directions.right);
		int[][] expected = new int[][] {{0,0,0,2},
										{0,0,0,4},
										{0,0,0,4},
										{0,0,0,4}};
		TileT[][] out = m.getBoard().getBoard();
		int diff = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(expected[i][j] != out[i][j].getValue()) {
						diff++;
				} else {
					assertTrue(expected[i][j] == out[i][j].getValue());
				}
			}
		}
		assertTrue(diff == 1);
	}

	@Test
	public void testMakeMove3() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT(0, i, j);
			}
		}
		arr[0][0] = new TileT(2, 0, 0);
		arr[1][3] = new TileT(4, 1, 3);
		arr[2][1] = new TileT(4, 2, 1);
		arr[3][0] = new TileT(2, 3, 0);
		arr[3][3] = new TileT(2, 3, 3);
		m.getBoard().setBoard(arr);
		m.makeMove(Directions.up);
		int[][] expected = new int[][] {{4,4,0,4},
										{0,0,0,2},
										{0,0,0,0},
										{0,0,0,0}};
		TileT[][] out = m.getBoard().getBoard();
		int diff = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(expected[i][j] != out[i][j].getValue()) {
						diff++;
				} else {
					assertTrue(expected[i][j] == out[i][j].getValue());
				}
			}
		}
		assertTrue(diff == 1);
	}

	@Test
	public void testMakeMove4() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = new TileT(0, i, j);
			}
		}
		arr[0][0] = new TileT(2, 0, 0);
		arr[1][3] = new TileT(4, 1, 3);
		arr[2][1] = new TileT(4, 2, 1);
		arr[3][0] = new TileT(2, 3, 0);
		arr[3][3] = new TileT(2, 3, 3);
		m.getBoard().setBoard(arr);
		m.makeMove(Directions.down);
		int[][] expected = new int[][] {{0,0,0,0},
										{0,0,0,0},
										{0,0,0,4},
										{4,4,0,2}};
		TileT[][] out = m.getBoard().getBoard();
		int diff = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(expected[i][j] != out[i][j].getValue()) {
						diff++;
				} else {
					assertTrue(expected[i][j] == out[i][j].getValue());
				}
			}
		}
		assertTrue(diff == 1);
	}

}