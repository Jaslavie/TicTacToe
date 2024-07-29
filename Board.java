//* Initialize the board structure for tictactoe game
// Board = (playerCount +1) x (playerCount +1) matrix

// import GUI elements from the java library
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class Board {
    // create an instance variable to store the dimensions of the board as an array
    private int[][] board;
    private int playerCount;
    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;

    //* construct the board
    public Board(int playerCount) {
        // use "this" keyword to refer to the instance variable of the Board class
        this.playerCount = playerCount;
        // create a new 2D array object with the dimensions of the board
        this.board = new int[playerCount + 1][playerCount + 1];
        // buttons are added for each cell of the board
        this.buttons = new JButton[playerCount + 1][playerCount + 1];

        initializeBoard();
        displayBoard();
    }
    //* initialize the board with null values
    public void initializeBoard() {
        // loop through the dimensions of the board
        for (int i = 0; i < playerCount +1; i++) {
            for (int j = 0; j < playerCount + 1; j++) {
                // initialize all cells of the board to a null value
                board[i][j] = 0;
            }
        }
    }
    //* display the board with the GUI interface
    public void displayBoard() {
        // create a window object to store the board
        frame = new JFrame("Jasmine's Tic Tac Toe Game");
        //create reusable container object to store the buttons
        panel = new JPanel();
        // set the layout of the panel to a grid layout (premade component from the java library)
        panel.setLayout(new GridLayout(playerCount + 1, playerCount + 1));

        //* add buttons to the each cell container in the grid
        for (int i = 0; i < playerCount + 1; i ++) {
            for (int j = 0; j < playerCount +1; j++) {
                // create a new button object with "-" as a placeholder
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Inter", Font.BOLD, 50));
                // store current row and column in the button object
                int row = i;
                int col = j;
                // add an action listener to the button object
                // create a lambda expression to handle the button click event more concisely
                buttons[i][j].addActionListener(e -> handleButtonClick(row, col));
                // add the button object to the panel object
                panel.add(buttons[i][j]);
            }
        }
        //* add the panel object to the frame object
        frame.add(panel);
        frame.setSize(800, 800);
        // close the frame when the user clicks the close button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make the frame visible
        frame.setVisible(true);

    }
    //* Handle button click
    private void handleButtonClick(int row, int col) {
            // check if the cell is empty
            if (board[row][col] == 0) {
                // update board with the player symbol
                updateBoard(row, col, 'X');
                // update the button text with the player symbol
                buttons[row][col].setText("X");
                // check if the player has won
                
            }
        }

    //* check if the player has won
    public void checkWin() {
        
    }
    //* check if the board is full
    public boolean checkFull() {
        for (int i = 0; i < playerCount + 1; i++) {
            for (int j = 0; j < playerCount + 1; j++) {
                // if there are still empty spaces in the board then return false
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //* update the board
    public void updateBoard(int row, int col, char symbol){
        // update board cells with the player's symbol
        board[row][col] = symbol; 
    }
}