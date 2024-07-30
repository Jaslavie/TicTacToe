import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;


public class GameLogic {
    private Board board;
    // create a list array of players
    private final List<Player> players;
    private final int winCondition;
    private int currentPlayerIndex;

    // constructor
    public GameLogic(int playerCount, int winCondition) {
        //* initialize the board
        this.winCondition = winCondition;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        initializePlayers(playerCount);
        this.board = new Board(playerCount, winCondition, this);
    }

    private void initializePlayers(int playerCount) {
        //* hash set to store used symbols attached to specific player IDs
        Set<Character> usedSymbols = new HashSet<>();
        // loop through the number of players selected
        for (int i = 0; i < playerCount; i++) {
            while (true) {
                // prompt the user to enter a unique symbol for each player
                String symbolInput = JOptionPane.showInputDialog("Enter a unique symbol for player " + (i + 1) + ":");
                
                //* validate the character is not null and is 1 character long
                if (symbolInput != null && symbolInput.length() == 1) {
                    // create a character to store the symbol from the modal
                    char symbol = symbolInput.charAt(0);
                    //* check if the symbol is not already used by checking if its in the useSymbol set
                    if (!usedSymbols.contains(symbol)) {
                        // add the symbol to the used symbols set
                        usedSymbols.add(symbol);
                        // add the player and the new symbol to the list
                        players.add(new Player(i, symbol));
                        break;
                    } else {
                        //* error handling for duplicate symbols
                        JOptionPane.showMessageDialog(null, "Symbol already taken. Please enter a unique symbol.");
                    }
                } else { 
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a single character.");
                }
            }
        }
    }

    public void startGame() {
        board.displayBoard();
    }

    //* switch to the next player
    public void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public char getCurrentPlayerSymbol() {
        // get the current player's symbol based on the current player index
        return players.get(currentPlayerIndex).getSymbol();
    }

    public void startNewGame() {
        this.board = new Board(players.size(), winCondition, this);
        board.initializeBoard();
        board.displayBoard();
    }
}
