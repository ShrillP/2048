/**
 * Author: Shrill Patel 
 * Revised: April 9, 2021
 * 
 * Description: A class that represents a board of the 2048 game.
 */

package src;

/**
 * @brief An abstract data type representing a 2048 game board.
 * @details This class represents the game board which is made up 
 * of tiles (TileT). It is assumed that the SIZE constant will be 
 * greater than 1 for a proper game to be made with possible moves.
 */
public class Board {

    private final int SIZE = 4;
    private TileT[][] board;
    private int score;

	/**
	 * @brief A constructor which initializes a board object.
	 * @details The board is initialized with 0's which represents
	 * empty positions. The score is set to zero to begin. It is assumed
	 * that the board will embody a square shape (equal number of rows
	 * and columns).
	 */
    public Board() {
        this.board = new TileT[SIZE][SIZE];
        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board.length; j++) {
                TileT tile = new TileT(0, i, j);
                this.board[i][j] = tile;
            }
        }
        this.score = 0;
    }

	/**
	 * @brief A getter for the board.
	 * @return The 2D tile arrays representing the game board.
	 */
    public TileT[][] getBoard() {
        return this.board;
    }

	/**
	 * @brief A getter for the current score.
	 * @return An integer representing the current score.
	 */
    public int getScore() {
        return this.score;
    }

    /**
	 * @brief A getter for the board size.
	 * @return An integer representing the board size.
	 */
    public int getBoardSize() {
        return this.SIZE;
    }

    /**
	 * @brief A getter for retrieving a specific board value.
     * @detials Assumed that the input x and y values are positive and less than
     * the board size.
	 * @param x An integer representing the row of the wanted value.
	 * @param y An integer representing the column of the wanted value.
	 * @return An integer representing a value on the board at specific coordinates.
	 */
    public int getBoardValueAt(int x, int y) {
        return this.board[x][y].getValue();
    }

    /**
	 * @brief A setter for the board.
	 * @details This will be used to update the board whenever any changes
	 * are made to it.
	 * @param currBoard A 2D tile array representing the updated board state.
	 */
    public void setBoard(TileT[][] currBoard) {
        this.board = currBoard;
    }

    /**
	 * @brief A setter for the the score.
	 * @details This will be used to update the score as the game progresses.
	 * @param currBoard An integer representing the current score of the game.
	 * @throws IllegalArgumentException if the score being set is negative.
     */
    public void setScore(int currScore) {
        if(currScore < 0)
            throw new IllegalArgumentException("Score cannot be negative!");
        this.score = currScore;
    }

    /**
	 * @brief A setter for updating the board with a tile.
	 * @details This method will update an existing tile or with a new tile.
	 * @param tile A tile object representing a new/updated tile.
	 */
    public void setBoardValueAt(TileT tile){
        int[] pos = tile.getPosition();
        this.board[pos[0]][pos[1]].setValue(tile.getValue());
    }

}