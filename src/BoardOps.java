/**
 * Author: Shrill Patel 
 * Revised: April 10, 2021
 * 
 * Description: An interface for all of the possible operations related to the board.
 */

package src;

/**
 * @brief An interface that lists out all board related operations.
 * @details These are the operations that will be implemented by the model potion
 * of the program. 
 */
public interface BoardOps {
    public boolean canMakeMove(TileT[][] board);
    public boolean isGameOver();
    public boolean shiftLeft();
    public boolean shiftRight();
    public boolean shiftUp();
    public boolean shiftDown();
    public boolean mergeLeft();
    public boolean mergeRight();
    public boolean mergeUp();
    public boolean mergeDown();
    public void makeMove(Directions dir);
}
