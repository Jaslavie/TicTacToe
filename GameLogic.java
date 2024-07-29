import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private final Board board;
    // create a list array of players
    private final List<Player> players;
    private final int winCondition;
    private int currentPlayerIndex;  // This should not be final

    // constructor
    public GameLogic(int playerCount, int winCondition) {
        //* initialize the board
        this.winCondition = winCondition;
        this.board = new Board(playerCount, winCondition);
        // initialize an empty list of players of type Player
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        initializePlayers(playerCount);
    }

    private void initializePlayers(int playerCount) {
        // populate the list of players with player objects
        for (int i = 0; i < playerCount; i++) {
            // type cast the player symbol to a character
            char symbol = (char) ('A' + i);
            // add player objects with a unique id and symbol
            players.add(new Player(i, symbol));
        }
    }

    public void startGame() {
        board.displayBoard();
        while (true) {
            // get the current player index
            Player currentPlayer = players.get(currentPlayerIndex);
            // get the current player's move from the board
            int[] move = board.getMove(currentPlayer.getSymbol());
            
        }
    }
}
