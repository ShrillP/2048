/**
 * Author: Shrill Patel 
 * Revised: April 9, 2021
 * 
 * Description: A class that represents a tile on the game board.
 */

package src;
import java.awt.Color;

/**
 * @brief An abstract data type representing a tile on the game board.
 * @details This class represents a tile that makes up the game board. It is 
 * represented by a value, its position on the board, and its colour. It is
 * assumed that the tile will be powers of 2 with the smallest possible number 
 * being a 2. It is also assumed that the colour for each tile will be set in
 * the view (GUI) of the program (a value of 0 represents empty tile).
 */
public class TileT {

    private int value;
    private int[] position;
    private Color tileColour;

    /**
	 * @brief A constructor which initializes a tile object.
	 * @details It will be assumed that the tile object will always be created 
	 * using x and y coordinates that fall in the range of the board size. 
	 * @param val An integer representing the value associated with the tile.
	 * @param x An integer representing the x-coordinate of the tile's location.
	 * @param y An integer representing the y-coordinate of the tile's location.
	 */
    public TileT(int val, int x, int y) {
        this.value = val;
        this.position = new int[] {x, y};
    }

    /**
	 * @brief A getter for the value of the tile object.
	 * @return The integer representing the value of the tile.
	 */
    public int getValue() {
        return this.value;
    }

    /**
	 * @brief A getter for the tile's position on the board.
	 * @return An integer array representing the tile's position.
	 */
    public int[] getPosition() {
        return this.position;
    }

    /**
	 * @brief A getter for the tile's colour.
	 * @details The tile's colour is represented using three integers which make 
	 * up an RGB colour code.
	 * @return A colour object representing the tile's current colour.
	 */
    public Color getTileColour() {
        return this.tileColour;
    }

    /**
	 * @brief A setter for the tile's value.
	 * @details This will be used to update the tile's value needs to be updated.
	 * @param val A integer representing the tile's new updated value.
     * @throws IllegalArgumentException if the value being set is negative.
	 */
    public void setValue(int val) {
        if(val < 0)
            throw new IllegalArgumentException("Tile value cannot be negative!");
        this.value = val;
    }

    /**
	 * @brief A setter for the tile's position.
	 * @details This will be used to update the tile's position on the game board
	 * when it needs to be shifted or moved. It is assumed that the values of the
     * input sequence will be positive and on the board.
	 * @param newCoords A integer array representing the tile's new position.
     * @throws IllegalArgumentException if the length of input array does not equal two 
	 */
    public void setPosition(int[] newCoords) {
        if(newCoords.length != 2) {
            throw new IllegalArgumentException("Position coordinate can only have 2 values!");
        }
        this.position = newCoords;
    }

    /**
	 * @brief A setter for the tile's colour.
	 * @details This will be used to update the tile's colour based on the value
	 * that it has. It is assumed that the value will only be unique tile colours
	 * for tile values up to 2048, any value greater than this will be black.
	 * @param val A integer representing the tile's current value.
     * @throws IllegalArgumentException if the value given is negative.
	 */
    public void setTileColour(int val) {
        if(val < 0)
            throw new IllegalArgumentException("Tile value cannot be negative!");
        if(val > 2048)
            this.tileColour = new Color(0,0,0);
        else {
            if(val == 0)
                this.tileColour = new Color(205, 193, 180);
            else if(val == 2)
                this.tileColour = new Color(238, 228, 218);
            else if (val == 4)
                this.tileColour = new Color(236, 224, 202);
            else if (val == 8)
                this.tileColour = new Color(242, 177, 121);
            else if (val == 16)
                this.tileColour = new Color(236, 141, 85);
            else if (val == 32)
                this.tileColour = new Color(247, 124, 95);
            else if (val == 64)
                this.tileColour = new Color(234, 90, 56);
            else if (val == 128)
                this.tileColour = new Color(244, 216, 107);
            else if (val == 256)
                this.tileColour = new Color(242, 208, 75);
            else if (val == 512)
                this.tileColour = new Color(228, 193, 42);
            else if (val == 1024)
                this.tileColour = new Color(227, 186, 19);
            else if (val == 2048)
                this.tileColour = new Color(236, 196, 2);
        }
    }

}