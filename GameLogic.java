import java.util.ArrayList;
import java.util.List;

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
        // create an array of symbols
        char[] symbols = {'X', 'O', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        // populate the list of players with player objects
        for (int i = 0; i < playerCount; i++) {
            // type cast the player symbol to a character
            char symbol = symbols[i];
            // add player objects with a unique id and symbol to the players list
            players.add(new Player(i, symbol));
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
