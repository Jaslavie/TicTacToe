import javax.swing.*;

public class TicTacToeGame {
    public static void main(String[] args) {
        // initialize the player count and win condition to 0
        int playerCount = 0;
        int playerWinChoice = 0;
        //* prompt the user to enter the number of players
        while (true) {
            try {
                String playerCountInput = JOptionPane.showInputDialog("Enter the number of players between 3 and 10:");
                playerCount = Integer.parseInt(playerCountInput);

                if (playerCount < 3 || playerCount > 10) {
                    // invalidates the input if the number of players is less than 3 or greater than 10 OR is not a number
                    throw new NumberFormatException();
                }
                break;
            //* catch the error if the input is not a number and prompt the user to enter a valid number
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 3 and 10.");
            }
        }

        //* prompt user to enter the win condition
        while (true) {
            try {
                String winConditionPrompt = "Enter the number of elements in a row to win between 3 and " + (playerCount + 1) + ":";
                String playerWinChoiceInput = JOptionPane.showInputDialog(winConditionPrompt);
                playerWinChoice = Integer.parseInt(playerWinChoiceInput);

                if (playerWinChoice < 3 || playerWinChoice > playerCount + 1) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 3 and " + (playerCount + 1) + ".");
            }
        }

        //* initialize the board with the input game logic and starts the game
        GameLogic game = new GameLogic(playerCount, playerWinChoice);
        game.startGame();
    }
}
