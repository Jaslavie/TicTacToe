import javax.swing.*;

public class TicTacToeGame {
    public static void main(String[] args) {
        //* prompt the user to enter the number of players
        String playerCountInput = JOptionPane.showInputDialog("Enter the number of players between 3 and 10:");
        int playerCount = Integer.parseInt(playerCountInput);

        //* prompt user to enter the win condition
        String winConditionPrompt = "Enter the number of elements in a row to win between 3 and " + (playerCount + 1) + ":";
        String playerWinChoiceInput = JOptionPane.showInputDialog(winConditionPrompt);
        int playerWinChoice = Integer.parseInt(playerWinChoiceInput);

        //* initialize the board with the input game logic and starts the game
        GameLogic game = new GameLogic(playerCount, playerWinChoice);
        game.startGame();
    }
}
