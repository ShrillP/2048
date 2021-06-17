/**
 * Author: Shrill Patel 
 * Revised: April 10, 2021
 * 
 * Description: A class that adds logical operations to the board (Model).
 */

package src;

import java.util.*;

/**
 * @brief A ADT that gives a board all of its logical operations.
 * @details This class is responsible of giving the board all of its functionalities.
 * This includes all of the board movements included in the game such as shifting 
 * and merging certain tiles on the board (up, down, left, or right).
 */
public class Model implements BoardOps {

    private Board board;

    /**
     * @brief A constructor used to initialize the board with two randomly generated tiles.
     * @details The board will always be randomly generated with two tiles that
     * can spawn at any location on the board, but can only have a value of either 
     * 2 or 4. 
     */
    public Model() {
        this.board = new Board();
        TileT tile1 = generateTile();
        this.board.setBoardValueAt(tile1);
        TileT tile2 = generateTile();
        this.board.setBoardValueAt(tile2);
    }

    /**
     * @brief A getter to retrieve the current state of the board.
     * @return An object of type Board representing the current state of the board.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * @brief A method to check if there are any valid moves left in the current game.
     * @details This method will return true if it sees an empty tile (0) because if 
     * there exists an empty spot on the board a new tile can be spawned there or 
     * an existing tile can be shifted into that spot. This method will also return 
     * true if any adjacent cells, in any direction, are the same because that means 
     * that they can be merged with a valid move. Otherwise, this methods returns false.
     * @param b A 2D sequence of tiles on a board.
     * @return A boolean representing if a valid move can be executed.
     */
    public boolean canMakeMove(TileT[][] b) {
        for(int i = 0; i < b.length; i++) {
            for(int j = 0; j < b.length; j++) {
                if(this.board.getBoardValueAt(i, j) == 0)
                    return true;
                else if(this.isValueOnBoard(i - 1, j) && this.board.getBoardValueAt(i, j) == this.board.getBoardValueAt(i - 1, j))
                    return true;
                else if(this.isValueOnBoard(i + 1, j) && this.board.getBoardValueAt(i, j) == this.board.getBoardValueAt(i + 1, j))
                    return true;
                else if(this.isValueOnBoard(i, j - 1) && this.board.getBoardValueAt(i, j) == this.board.getBoardValueAt(i, j - 1))
                    return true;
                else if(this.isValueOnBoard(i, j + 1) && this.board.getBoardValueAt(i, j) == this.board.getBoardValueAt(i, j + 1))
                    return true;   
            }
        }
        return false;
    }

    /**
     * @brief A method used to determine if a tile with a value of 2048 exists.
     * @details This method is used to determine if the player has won the game.
     * @return A boolean if it find a tile with a value of 2048.
     */
    public boolean does2048Exist() {
        for(int i = 0; i < this.board.getBoardSize(); i++) {
            for(int j = 0; j < this.board.getBoardSize(); j++) {
                if(this.board.getBoard()[i][j].getValue() == 2048)
                    return true;
            }
        }
        return false;
    }

    /**
     * @brief A method used to determine if we can continue playing the game.
     * @details A game is over if we cannot possibly make any more valid moves.
     * @return A boolean if the game has ended.
     */
    public boolean isGameOver() {
        return !this.canMakeMove(this.board.getBoard()); 
    }

    /**
     * @brief A method to execute a move on the board.
     * @details A move is associated with a specified shift and/or merge in the
     * given direction. When we execute a shift or a merge or both, we must
     * generate a new random tile on the board. A move is made only if there 
     * exists a valid move.
     * @param dir An enumerate direction to specify in which direction to execute
     * a move in.
     */
    public void makeMove(Directions dir) {
        boolean shifted = false;
        boolean merged = false;
        if(this.canMakeMove(this.board.getBoard())) {
            if(dir == Directions.up) {
                shifted = this.shiftUp();
                merged = this.mergeUp();
            } else if(dir == Directions.down) {
                shifted = this.shiftDown();
                merged = this.mergeDown();
            } else if(dir == Directions.right) {
                shifted = this.shiftRight();
                merged = this.mergeRight();
            } else if(dir == Directions.left) {
                shifted = this.shiftLeft();
                merged = this.mergeLeft();
            }
        }
        if(shifted || merged)
            this.board.setBoardValueAt(this.generateTile());
    }

    /**
     * @brief A method that shifts the tiles on the board towards the left.
     * @details The shift will only take place when the tile we are looking at
     * has a non-zero value and its left adjacent tile is a zero, indicating 
     * that the tile we are looking at can be shifted left. This process is repeated for
     * tiles on the board.
     * @return A boolean if a leftward shift was executed and the board's state changed. 
     */
    public boolean shiftLeft() {
        boolean shifted = false;
        for(int i = 0; i < this.board.getBoardSize(); i++) {
            for(int x = 0; x < this.board.getBoard()[i].length - 1; x++) {
                for(int j = 1; j < this.board.getBoard()[i].length; j++) {
                    if(this.board.getBoardValueAt(i, j - 1) == 0 && this.board.getBoardValueAt(i, j) != 0) {
                        this.changeValueOnBoard(this.board.getBoardValueAt(i, j), i, j - 1);
                        this.changeValueOnBoard(0, i, j);
                        shifted = true;
                    }
                }
            }
        }
        return shifted;
    }

    /**
     * @brief A method that shifts the tiles on the board towards the right.
     * @details The shift will only take place when the tile we are looking at
     * has a non-zero value and its right adjacent tile is a zero, indicating 
     * that the tile we are looking at can be shifted right. This process is repeated for
     * tiles on the board.
     * @return A boolean if a rightward shift was executed and the board's state changed. 
     */
    public boolean shiftRight() {
        boolean shifted = false;
        for(int i = 0; i < this.board.getBoardSize(); i++) {
            for(int x = 0; x < this.board.getBoard()[i].length - 1; x++) {
                for(int j = 0; j < this.board.getBoard()[i].length - 1; j++) {
                    if(this.board.getBoardValueAt(i, j + 1) == 0 && this.board.getBoardValueAt(i, j) != 0) {
                        this.changeValueOnBoard(this.board.getBoardValueAt(i, j), i, j + 1);
                        this.changeValueOnBoard(0, i, j);
                        shifted = true;
                    }
                }
            }
        }
        return shifted;
    }

    /**
     * @brief A method that shifts the tiles on the board towards the top of the board.
     * @details The shift will only take place when the tile we are looking at
     * has a non-zero value and its upwards adjacent tile is a zero, indicating 
     * that the tile we are looking at can be shifted up. This process is repeated for
     * tiles on the board.
     * @return A boolean if an upward shift was executed and the board's state changed. 
     */
    public boolean shiftUp() {
        boolean shifted = false;
        for(int j = 0; j < this.board.getBoardSize(); j++) {
            for(int x = 0; x < this.board.getBoard()[j].length - 1; x++) {
                for(int i = this.board.getBoard()[j].length - 1; i > 0; i--) {
                    if(this.board.getBoardValueAt(i - 1, j) == 0 && this.board.getBoardValueAt(i, j) != 0) {
                        this.changeValueOnBoard(this.board.getBoardValueAt(i, j), i - 1, j);
                        this.changeValueOnBoard(0, i, j);
                        shifted = true;
                    }
                }
            }
        }
        return shifted;
    }

    /**
     * @brief A method that shifts the tiles on the board towards the bottom of the board.
     * @details The shift will only take place when the tile we are looking at
     * has a non-zero value and its downwards adjacent tile is a zero, indicating 
     * that the tile we are looking at can be shifted down. This process is repeated for
     * tiles on the board.
     * @return A boolean if a downwards shift was executed and the board's state changed. 
     */
    public boolean shiftDown() {
        boolean shifted = false;
        for(int j = 0; j < this.board.getBoardSize(); j++) {
            for(int x = 0; x < this.board.getBoard()[j].length - 1; x++) {
                for(int i = 0; i < this.board.getBoard()[j].length - 1; i++) {
                    if(this.board.getBoardValueAt(i + 1, j) == 0 && this.board.getBoardValueAt(i, j) != 0) {
                        this.changeValueOnBoard(this.board.getBoardValueAt(i, j), i + 1, j);
                        this.changeValueOnBoard(0, i, j);
                        shifted = true;
                    }
                }
            }
        }
        return shifted;
    }

    /**
     * @brief A method that merges the tiles on the board that have the same value 
     * towards the left of the board.
     * @details The merge will only take place when the tile we are looking at
     * and its left adjacent tile both have a non-zero value and both the tiles are 
     * of the same value. If these conditions are met, both tiles will be collapsed 
     * into the left tile, the combined tile will have a value twice as greater, and
     * the score will be increment by the value of the new combined tile.
     * @return A boolean if a left merge was executed and the board's state changed. 
     */
    public boolean mergeLeft() {
        boolean merged = false;
        for(int i = 0; i < this.board.getBoardSize(); i++) {
            for(int j = 1; j < this.board.getBoardSize(); j++) {
                if(this.board.getBoardValueAt(i, j) != 0 && this.board.getBoardValueAt(i, j - 1) != 0) {
                    if(this.board.getBoardValueAt(i, j) == this.board.getBoardValueAt(i, j - 1)) {
                        this.changeValueOnBoard(this.board.getBoardValueAt(i, j) * 2, i, j - 1);
                        this.changeValueOnBoard(0, i, j);
                        this.board.setScore(this.board.getScore() + this.board.getBoardValueAt(i, j - 1));
                        merged = true;
                        this.shiftLeft();
                    }
                }
            }
        }
        return merged;
    }

    /**
     * @brief A method that merges the tiles on the board that have the same value 
     * towards the right of the board.
     * @details The merge will only take place when the tile we are looking at
     * and its right adjacent tile both have a non-zero value and both the tiles are 
     * of the same value. If these conditions are met, both tiles will be collapsed 
     * into the right tile, the combined tile will have a value twice as greater, and
     * the score will be increment by the value of the new combined tile.
     * @return A boolean if a right merge was executed and the board's state changed. 
     */
    public boolean mergeRight() {
        boolean merged = false;
        for(int i = 0; i < this.board.getBoardSize(); i++) {
            for(int j = 0; j < this.board.getBoardSize() - 1; j++) {
                if(this.board.getBoardValueAt(i, j) != 0 && this.board.getBoardValueAt(i, j + 1) != 0) {
                    if(this.board.getBoardValueAt(i, j) == this.board.getBoardValueAt(i, j + 1)) {
                        this.changeValueOnBoard(this.board.getBoardValueAt(i, j) * 2, i, j + 1);
                        this.changeValueOnBoard(0, i, j);
                        this.board.setScore(this.board.getScore() + this.board.getBoardValueAt(i, j + 1));
                        merged = true;
                        this.shiftRight();
                    }
                }
            }
        }
        return merged;
    }

    /**
     * @brief A method that merges the tiles on the board that have the same value 
     * towards the top of the board.
     * @details The merge will only take place when the tile we are looking at
     * and its upwards adjacent tile both have a non-zero value and both the tiles are 
     * of the same value. If these conditions are met, both tiles will be collapsed 
     * into the above tile, the combined tile will have a value twice as greater, and
     * the score will be increment by the value of the new combined tile.
     * @return A boolean if an upwards merge was executed and the board's state changed. 
     */
    public boolean mergeUp() {
        boolean merged = false;
        for(int j = 0; j < this.board.getBoardSize(); j++) {
            for(int i = 0; i < this.board.getBoard()[j].length - 1; i++) {
                if(this.board.getBoardValueAt(i, j) != 0 && this.board.getBoardValueAt(i + 1, j) != 0) {
                    if(this.board.getBoardValueAt(i, j) == this.board.getBoardValueAt(i + 1, j)) {
                        this.changeValueOnBoard(this.board.getBoardValueAt(i, j) * 2, i + 1, j);
                        this.changeValueOnBoard(0, i, j);
                        this.board.setScore(this.board.getScore() + this.board.getBoardValueAt(i + 1, j));
                        merged = true;
                        this.shiftUp();
                    }
                }
            }
        }
        return merged;
    }

    /**
     * @brief A method that merges the tiles on the board that have the same value 
     * towards the bottom of the board.
     * @details The merge will only take place when the tile we are looking at
     * and its downwards adjacent tile both have a non-zero value and both the tiles are 
     * of the same value. If these conditions are met, both tiles will be collapsed 
     * into the below tile, the combined tile will have a value twice as greater, and
     * the score will be increment by the value of the new combined tile.
     * @return A boolean if a downwards merge was executed and the board's state changed. 
     */
    public boolean mergeDown() {
        boolean merged = false;
        for(int j = 0; j < this.board.getBoardSize(); j++) {
            for(int i = this.board.getBoard()[j].length - 1; i > 0 ; i--) {
                if(this.board.getBoardValueAt(i, j) != 0 && this.board.getBoardValueAt(i - 1, j) != 0) {
                    if(this.board.getBoardValueAt(i, j) == this.board.getBoardValueAt(i - 1, j)) {
                        this.changeValueOnBoard(this.board.getBoardValueAt(i, j) * 2, i - 1, j);
                        this.changeValueOnBoard(0, i, j);
                        this.board.setScore(this.board.getScore() + this.board.getBoardValueAt(i - 1, j));
                        merged = true;
                        this.shiftDown();
                    }
                }
            }
        }
        return merged;
    }

    /**
     * @brief A method to check if a tile exists at given coordinates on the board.
     * @param x An integer representing the row in which we are looking in.
     * @param y An integer representing the column in which we are looking in.
     * @return A boolean representing if there exists a tile at specified coordinates.
     */
    private boolean isValueOnBoard(int x, int y) {
        if(x >= 0 && x < this.board.getBoardSize() && y >= 0 && y < this.board.getBoardSize())
            return true;
        return false;
    }

    /**
     * @brief A method to get the available spots on the board for new tiles.
     * @details An empty spot is assumed to have a value of zero.
     * @return A sequence of sequence containing coordinates of empty tile locations.
     */
    private ArrayList<int[]> getEmptySpots() {
        ArrayList<int[]> zeros = new ArrayList<int[]>();
        for(int i = 0; i < this.board.getBoardSize(); i++) {
            for(int j = 0; j < this.board.getBoardSize(); j++) {
                if(this.board.getBoardValueAt(i, j) == 0) {
                    int[] coord = new int[] {i, j};
                    zeros.add(coord);
                }
            }
        }
        return zeros;
    }

    /**
     * @brief A method to select a random empty tile location on the board.
     * @details This method is used to help decide where a new tile should be placed
     * after a successful move.
     * @return A sequence of coordinates representing the random empty location
     * on the board. 
     */
    private int[] randomAvailableCoords() {
        Random random = new Random();
        ArrayList<int[]> emptySpots = getEmptySpots();
        return emptySpots.get(random.nextInt(emptySpots.size()));
    }

    /**
     * @brief A method used to change the value of a tile on the board.
     * @details This method is used to change the value of a tile after a shift
     * or merge move has been successfully executed only if there exists a 
     * possible tile location at the specified board coordinates. 
     * @param val An integer representing the new value of the tile.
     * @param x An integer representing the x-coordinate on the board.
     * @param y An integer representing the y-coordinate on the board.
     */
    private void changeValueOnBoard(int val, int x, int y) {
        if(isValueOnBoard(x, y))
            this.board.getBoard()[x][y].setPosition(new int[] {x, y});
            TileT tile = new TileT(val, x, y);
            this.board.setBoardValueAt(tile);
    }

    /**
     * @brief A method used to generate a random tile.
     * @details This method generates a random tile (2 at the beginning and
     * one every time we successfully shift or merge) at a random available 
     * location. It has a 70% probability of generating a tile with a value of
     * 2 and a 30% probability of generating a tile with a value of 4.
     * @return A TileT object for the newly generated tile.
     */
    private TileT generateTile() {
        TileT tile;
        int[] coords = randomAvailableCoords();
        Random random = new Random();
        double chance = random.nextDouble();
        if(chance < 0.7) 
            tile = new TileT(2, coords[0], coords[1]);
        else
            tile = new TileT(4, coords[0], coords[1]);
        return tile;
    }

}