/**
 * Author: Shrill Patel 
 * Revised: April 10, 2021
 * 
 * Description: A class used to control the board operations (acts as controller)
 * in the MVC design pattern.
 */

package src;

import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * @brief An abstract data type that is in charge of controlling board operations.
 * @details This class is an ADT because this implementation requires for a new game
 * to be initiated when the new game button is pressed. This class implements Java's
 * interface KeyListener interface.
 */
public class Controller implements KeyListener {

    private static GameGUI game;
    private static int endGameFlag;
    private static int goFurther;
    private static boolean flag = false;

    /**
	 * @brief A constructor which initializes a new game.
	 * @details The constructor also adds a KeyListener to the entire game frame so
	 * that board moves can be controlled by the user. 
	 */
    public Controller() {
        game = new GameGUI();
        game.addKeyListener(this);
    }

    /**
     * @brief This method performs a combination of board operations when certain
     * keys are pressed.
     * @details This method will shift the board left, right, up, or down when the 
     * corresponding arrow key is pressed by the player. After each move the game board
     * is refreshed and updated. It also checks if the game was won by the player. If 
     * the player lost the game they are prompted with a window to close the game.
     * @param e A KeyEvent object used to keep track if a certain key is pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(!game.getModel().isGameOver()) {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                game.getModel().makeMove(Directions.right);
                game.refreshBoard(game.updateBoard(game.getModel().getBoard()));
            } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                game.getModel().makeMove(Directions.left);
                game.refreshBoard(game.updateBoard(game.getModel().getBoard()));
            } else if(e.getKeyCode() == KeyEvent.VK_UP) {
                game.getModel().makeMove(Directions.up);
                game.refreshBoard(game.updateBoard(game.getModel().getBoard()));
            } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                game.getModel().makeMove(Directions.down);
                game.refreshBoard(game.updateBoard(game.getModel().getBoard()));
            }
            game.repaintScore();
            if(game.getModel().does2048Exist() && !flag)
                gameWon();
        } else {
            endGameFlag = JOptionPane.showConfirmDialog(game.getContentPane(), "Thank you for playing!", "Game Over", JOptionPane.OK_OPTION);
            if(endGameFlag == JOptionPane.YES_OPTION)
                System.exit(1);
        }
    }

    /**
     * @brief A method to see what character was typed by the user.
     * @details This method was not implemented because it is not used in this
     * implementation of the game.
     * @param e A KeyEvent object used to keep track if a certain key is pressed.
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * @brief A method to see what character was released by the user.
     * @details This method was not implemented because it is not used in this
     * implementation of the game.
     * @param e A KeyEvent object used to keep track if a certain key is released.
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * @brief A method to tell the user that they beat the game.
     * @details This method will be used when a 2048 tile is reached. If the user
     * to continue playing, then the game will continue. Otherwise, if they 
     * select no, then the game will terminate.
     */
	private void gameWon() {
        goFurther = JOptionPane.showConfirmDialog(game.getContentPane(), "Click yes to keep going or no to quit.", "You Win", JOptionPane.YES_NO_OPTION);
        if(goFurther == JOptionPane.YES_OPTION) {
            flag = true;
        } else if(goFurther == JOptionPane.NO_OPTION) {
            System.exit(1);
        }
    }

}