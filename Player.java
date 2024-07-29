//* logic for players
public class Player {
    private final int id;
    private final char symbol;

    // constructor
    public Player(int id, char symbol) {
        // "this" refers to the parent object
        this.id = id;
        this.symbol = symbol;
    }

    // getter for player id
    public int getId() {
        return id;
    }
    // getter for player symbol
    public char getSymbol() {
        return symbol;
    }
    
}