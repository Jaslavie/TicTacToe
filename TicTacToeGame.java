//* launch the game
import javax.swing.*;
public class TicTacToeGame {
    public static void main(String[] args) {
        //* prompt the user to enter the number of players
        String playerCount = JOptionPane.showInputDialog("Enter the number of players: ");
        // type cast the player count from a string input to an integer
        int PlayerCount = Integer.parseInt(playerCount);

        //* prompt user to enter the win condition
        String playerWinChoice = JOptionPane.showInputDialog("Enter the number of elements in a row to win: ");
        int PlayerWinChoice = Integer.parseInt(playerWinChoice);

        // initialize the board with a new board object
        new Board(PlayerCount, PlayerWinChoice);
       
       
    }
}