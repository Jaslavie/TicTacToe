import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class Board {
    // create an instance variable to store the dimensions of the board as an array
    private final char[][] board;
    private final int playerCount;
    private final int playerWinChoice;
    private JFrame frame;
    private JPanel panel;
    private final JButton[][] buttons;
    // initialize last move
    private final int[] lastMove = new int[2];
    // add a reference to the GameLogic class
    private final GameLogic gameLogic;

    //* construct the board
    public Board(int playerCount, int playerWinChoice, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.board = new char[playerCount + 1][playerCount + 1];
        // use "this" keyword to refer to the instance variable of the Board class
        this.playerCount = playerCount;
        this.playerWinChoice = playerWinChoice;
        this.buttons = new JButton[playerCount + 1][playerCount + 1];
        initializeBoard();
        displayBoard();
    }

    //* initialize the board with null values
    public final void initializeBoard() {
        // loop through the dimensions of the board
        for (int i = 0; i < playerCount + 1; i++) {
            for (int j = 0; j < playerCount + 1; j++) {
                // initialize all cells of the board to a null value
                board[i][j] = '-';
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
        for (int i = 0; i < playerCount + 1; i++) {
            for (int j = 0; j < playerCount + 1; j++) {
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
        if (board[row][col] == '-') {
            // update board with the player symbol
            char currentPlayerSymbol = gameLogic.getCurrentPlayerSymbol();
            updateBoard(row, col, currentPlayerSymbol);
            // update the button text with the player symbol
            buttons[row][col].setText(String.valueOf(currentPlayerSymbol));

            //* store the last move in an array
            lastMove[0] = row;
            lastMove[1] = col;

            // check if the player has won
            if (checkWin(row, col)) {
                endGameModal("Player " + currentPlayerSymbol + " wins!");
            //* checks for ties
            } else if (checkFull()) {
                endGameModal("It's a draw!");
            } else {
                // switch to the next player
                gameLogic.switchPlayer();
            }
        } else {
            // display an error message if the cell is not empty
            JOptionPane.showMessageDialog(frame, "Invalid move. Try again.");
        }
    }

    //* check if the player has won
    public boolean checkWin(int row, int col) {
        // initialize the win condition to the player's choice
        int winCondition = playerWinChoice;
        // type cast the player's symbol at the column to a character
        char playerSymbol = board[row][col];

        //* check rows 
        int count = 0;
        for (int i = 0; i < playerCount + 1; i++) {
            if (board[row][i] == playerSymbol) {
                count++;
                if (count == winCondition) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        //* check columns
        count = 0;
        for (int i = 0; i < playerCount + 1; i++) {
            if (board[i][col] == playerSymbol) {
                count++;
                if (count == winCondition) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        //* check main diagonal
        count = 0;
        // initialize the starting row and column to the user's current coordinate position
        int startRow = row;
        int startCol = col;
        // loop through the diagonal cells
        while (startRow > 0 && startCol > 0) {
            startRow--;
            startCol--;
        }
        // check the diagonal cells
        while (startRow < playerCount + 1 && startCol < playerCount + 1) {
            // if the diagonal cell contains the player's symbol, increment the count
            if (board[startRow][startCol] == playerSymbol) {
                count++;
                if (count == winCondition) {
                    return true;
                }
            } else {
                count = 0;
            }
            // increment the row and column to check the next diagonal cell
            startRow++;
            startCol++;
        }

        //* check anti-diagonal
        count = 0;
        startRow = row;
        startCol = col;
        while (startRow > 0 && startCol < playerCount) {
            startRow--;
            startCol++;
        }
        while (startRow < playerCount + 1 && startCol >= 0) {
            if (board[startRow][startCol] == playerSymbol) {
                count++;
                if (count == winCondition) {
                    return true;
                }
            } else {
                count = 0;
            }
            startRow++;
            startCol--;
        }

        return false;
    }

    //* check if the board is full
    public boolean checkFull() {
        for (int i = 0; i < playerCount + 1; i++) {
            for (int j = 0; j < playerCount + 1; j++) {
                // if there are still empty spaces in the board then return false
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    //* update the board
    public boolean updateBoard(int row, int col, char symbol) {
        // update board cells with the player's symbol
        board[row][col] = symbol;
        return true;
    }

    //* modal for end of game
    public void endGameModal(String message) {
        // create a modal dialog box to display the end of the game
        int response = JOptionPane.showConfirmDialog(
            frame, // parent component
            message + "\nPlay again?",  // message
            "Game Over",  // title
            // prompt user to click a button to restart the game
            JOptionPane.YES_NO_OPTION,  // option type
            JOptionPane.QUESTION_MESSAGE  // message type
        );
        if (response == JOptionPane.YES_OPTION) {
            frame.dispose();
            gameLogic.startNewGame();
        } else {
            frame.dispose();
            // close the window
            System.exit(0);
        }
    }

    //* get player's move from buttons GUI by returning coordinates of the row and column of clicked button
    public int[] getMove(char playerSymbol) {
        return lastMove;
    }
}
