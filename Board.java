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
    private int playerWinChoice = 0;
    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private char currentPlayer = 'X';
    //* construct the board
    public Board(int playerCount) {
        // use "this" keyword to refer to the instance variable of the Board class
        this.playerCount = playerCount;
        // create a new 2D array object with the dimensions of the board
        this.board = new int[playerCount + 1][playerCount + 1];
        // buttons are added for each cell of the board
        this.buttons = new JButton[playerCount + 1][playerCount + 1];
        currentPlayer = 'X';
        initializeBoard();
        displayBoard();
    }
    //* initialize the board with null values
    public final void initializeBoard() {
        // loop through the dimensions of the board
        for (int i = 0; i < playerCount +1; i++) {
            for (int j = 0; j < playerCount + 1; j++) {
                // initialize all cells of the board to a null value
                board[i][j] = 0;
            }
        }
    }
    //* display the board with the GUI interface
    public final void displayBoard() {
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
    //* switch players
    public void switchPlayers() {
        // sets player to O if current player is X, else sets player to X
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    //* Handle button click
    private void handleButtonClick(int row, int col) {
            // check if the cell is empty
            if (board[row][col] == 0) {
                // update board with the player symbol
                updateBoard(row, col, currentPlayer);
                // update the button text with the player symbol
                buttons[row][col].setText(String.valueOf(currentPlayer));
                // check if the player has won
                if (checkWin(row, col) || checkFull()) {
                    endGameModal();
                } else{
                    switchPlayers();
                }
                
            }
        }

    //* check if the player has won
    public boolean checkWin(int row, int col) {
        // initialize the win condition to the player's choice
        int winCondition = playerWinChoice;
        // type cast the player's symbol at the column to a character
        char playerSymbol = (char) board[row][col];

        //* check rows 
        for (int i = 0; i < winCondition; i++) {
            // for each column in the row, check if the player symbol is present
            if (board[row][i] != playerSymbol) {
                break;
                // if the player symbol is present in all colums of the win condition, return true
            } else if (i == winCondition - 1) {
                return true;
            }
        }

        //* check columns
        for (int i = 0; i < winCondition; i++) {
            if (board[i][col] != playerSymbol) {
                break;
            } else if (i == winCondition - 1) {
                return true;
            }
        }
        //* check diagonals
       
            for (int i = 0; i < winCondition; i++) {
                if (board[i][i] != playerSymbol) {
                    break;
                } else if (i == winCondition - 1) {
                    return true;
                }
            }
   
        return false;
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

    //* modal for end of game
    private void endGameModal() {
        // create a modal dialog box to display the end of the game
        int response = JOptionPane.showConfirmDialog(
            frame, // parent component
            "Game Over! Play again?",  // message
            "Game Over",  // title
            JOptionPane.YES_NO_OPTION, // option type
            JOptionPane.QUESTION_MESSAGE // message type
        );
        // if the user clicks yes, reset the board
        if (response == JOptionPane.YES_OPTION){
            frame.dispose();
            // create a new board object
            new Board(playerCount);
        } else {
            frame.dispose();
        }
        
    }
}