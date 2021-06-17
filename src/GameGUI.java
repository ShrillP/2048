/**
 * Author: Shrill Patel 
 * Revised: April 10, 2021
 * 
 * Description: A class that builds the graphical user interface (GUI) for the game.
 */

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @brief This ADT class defines the GUI of the game being implemented.
 * @details This class implements the methods outlined by the ActionListener
 * interface and uses the JFrame swing framework to create the GUI on. This 
 * class is an ADT because new GUIs need to be created to load new games (added
 * extra functionality to the game). It is assumed that loading in a new game
 * is not a part of the controller rather a GUI related functionality.
 */
public class GameGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static Model m = new Model();

    private static JPanel board = new JPanel();
    private static JPanel info = new JPanel();
    private static JPanel score = new JPanel();
    private static JLabel gameName = new JLabel("2048");
    private static JLabel scoreName = new JLabel("SCORE");
    private static JLabel scoreValue;
    public JButton newGame = new JButton("New Game");

    /**
     * @brief A constructor that sets up the initial GUI for the game.
     * @details The constructor sets up the initial frame where all of the GUI
     * components are placed. It will also load in all of the scoring related 
     * fields and then the board will be refreshed and made visible. 
     */
    public GameGUI() {
        this.setLayout(null);
        this.setSize(550, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(250, 248, 239));
        loadRestart();
        loadBoard();
        loadInfo();
        loadGameName();
        loadScorePanel();
        loadScore(m.getBoard().getScore());
        refreshBoard(updateBoard(m.getBoard()));
        this.setVisible(true);
    }

    /**
     * @brief A getter for the current model representing the board. 
     * @details This method is used as an access point to retrieve all the 
     * information regarding the state of the board and its logical moves.
     * @return The current model object that represents the state of the board.
     */
	public Model getModel() {
        return m;
    }

    /**
     * @brief A getter for the new game button on the GUI. 
     * @return A JButton representing the current state of the button.
     */
    public JButton getNewButton() {
        return this.newGame;
    }

    /**
     * @brief A setter to update the current model state.
     * @param model An object of type Model representing the new/updated model.
     */
    public void setModel(Model model) {
        m = model;
    }

    /**
     * @brief A method used to initialize the layout of the game board.
     * @details The game board uses a grid layout to visualize the game board which
     * gives each individual tile its own separate location. The board's visual
     * appearance features are also set in this method.
     */
    private void loadBoard() {
        board.setLayout(new GridLayout(m.getBoard().getBoardSize(), m.getBoard().getBoardSize(), 10, 10));
        board.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        board.setBackground(new Color(189, 175, 162));
        board.setBounds(25, 145, 500, 500);
        this.add(board);
    }

    /**
     * @brief A method used to initialize the layout of the game information (scoring).
     * @details The scoring panel's visual appearance features are also set in this method.
     */
    private void loadInfo() {
        info.setLayout(null);
        info.setBackground(new Color(250, 248, 239));
        info.setBounds(25, 10, 515, 125);        
        this.add(info);
    }

	/**
     * @brief A method used to initialize the name label of the game.
     * @details The label's visual appearance features are also set in this method.
     */
    private void loadGameName() {
        gameName.setForeground(new Color(119, 110, 101));
        gameName.setFont(new Font("Helvetica Neue", Font.BOLD, 75));
        gameName.setBounds(5, 5, 300, 75);
        info.add(gameName);
    }

    /**
     * @brief A method used to add the labels needed to represent the score to the player.
     * @details The representation of the score is composed of the actual panel
     * and the label's name. The panel's visual appearance features are also set in this method.
     */
    private void loadScorePanel() {
        score.setLayout(null);
        score.setBackground(new Color(187, 173, 160));
        score.setBounds(325, 5, 175, 60);
        scoreName.setForeground(new Color(238, 228, 218));
        scoreName.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        scoreName.setBounds(60, 5, 75, 18);
        score.add(scoreName);
        info.add(score);
    }

	/**
     * @brief A method used to update the score value onto the scoring panel.
     * @details The scoring value is retrieved form the model and it's visual appearance
     * features are also set in this method.
     */
    private void loadScore(int s) {
        scoreValue = new JLabel();
        scoreValue.setText(Integer.toString(m.getBoard().getScore()));
        scoreValue.setHorizontalAlignment(JLabel.CENTER);
        scoreValue.setForeground(new Color(255, 255, 255));
        scoreValue.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        scoreValue.setBounds(8, 23, 160, 30);
        score.add(scoreValue);
    }

    /**
     * @brief A method used to show the new game button on the GUI.
     * @details It's visual appearance features are also set in this method.
     * This button will help to load in a new board and restart a new game.
     */
    private void loadRestart() {
        newGame.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        newGame.setBounds(355, 90, 165, 40);
        newGame.setOpaque(true);
        newGame.setBorderPainted(false);
        newGame.setBackground(new Color(143, 122, 102));
        newGame.setForeground(new Color(255, 255, 255));
        newGame.setFocusable(false);
        newGame.addActionListener(this);
        this.add(newGame);
    }

    /**
     * @brief A method to update how the board looks.
     * @details This method will update how the board looks after each move
     * and when a new game is started. Since tiles were never assigned a 
     * colour when they were created (to avoid overwriting), the tile colours 
     * are set as we update the board tile by tile.
     * @param b A Board object representing an updated version of current board
     * or a completely new board.
     * @return A 2D sequence of JPanels representing the tiles on the board.
     */
    public JPanel[][] updateBoard(Board b) {
        TileT[][] tiles = b.getBoard();
        JPanel[][] allTiles = new JPanel[tiles.length][tiles.length];
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                tiles[i][j].setTileColour(tiles[i][j].getValue());
                if(tiles[i][j].getValue() != 0) {
                    JPanel tile = new JPanel(new GridBagLayout());
                    JLabel val = new JLabel(Integer.toString(tiles[i][j].getValue()));
                    val.setForeground(new Color(119, 110, 101));
                    val.setFont(new Font("Helvetica Neue", Font.BOLD, 50));
                    tile.setBackground(tiles[i][j].getTileColour());
                    tile.add(val);
                    allTiles[i][j] = tile;
                } else {
                    JPanel tile = new JPanel(new GridBagLayout());
                    tile.setBackground(tiles[i][j].getTileColour());
                    allTiles[i][j] = tile;
                }
            }
        }
        return allTiles;
    }

    /**
     * @brief A method to refresh the board.
     * @details This method is used to refresh the visual board when a move is made
     * or when a new game is to be loaded.
     * @param tiles A 2D sequence of JPanels representing the new sequence of tiles.
     */
    public void refreshBoard(JPanel[][] tiles) {
        board.removeAll();
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                board.add(tiles[i][j]);
            }
        }
    }

    /**
     * @brief A method to refresh the score label for player to see.
     */
    public void refreshScore() {
        int currScore = m.getBoard().getScore();
        loadScore(currScore);
    }

    /**
     * @brief A method used to make the score update visible on the game frame.
     * @details The label must be repainted to avoid overlap updating of the score.
     */
    public void repaintScore() {
        score.remove(scoreValue);
        refreshScore();
        setVisible(true);
        scoreValue.repaint();
    }

    /**
     * @brief A method to keep track of any mouse actions on the game frame.
     * @details If the new game button is pressed, a new model will be created
     * and the board on the frame will be visually refreshed along with the score
     * being reset.
     * @param e A ActionEvent object used to keep track if the button was pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGame)  {
            this.dispose();
            m = new Model();
            refreshBoard(updateBoard(m.getBoard()));
            score.remove(scoreValue);
            refreshScore();
            this.setVisible(true);
            scoreValue.repaint();
        }
    }

}