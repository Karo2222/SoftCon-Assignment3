package gameOfLife.game;

import gameOfLife.game.move.Create;
import gameOfLife.game.move.Kill;
import gameOfLife.game.move.Move;
import gameOfLife.grid.CellColor;

import java.util.Stack;

public class Player {

    private final String name;
    private Stack<Move> turns;
    private CellColor playerColor = CellColor.NO_PLAYER;

    public Player(String name) {
        this.name = name;
        this.turns = new Stack<>();
        init();
    }

    /**
     * initializes a new set of both moves after a Player's turn
     */
    private void init() {
        this.turns.removeAllElements(); //this.turns is empty at this point but this is here to *ensure* that it is actually empty
        this.turns.add(new Create(this));
        this.turns.add(new Kill(this));
    }

    /**
     * @return name of the Player (String)
     */
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * method to compare two Player instances to check equality
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        return this.name.equals(((Player) obj).getName());
    }

    /**
     * Precondition: this.turns is not empty
     * @return the next move in this.turns
     */
    public Move getNextMove() {
        return turns.peek();
    }

    /**
     * pops the move on top of the stack
     */
    public void setNextMove() {
        turns.pop();
    }

    /**
     * resets this.turns with both moves in it
     */
    public void resetMoves() {
        init();
    }

    public boolean turns_isEmpty() {
        return turns.isEmpty();
    }

    public void setPlayerColor(CellColor cellColor) {
        this.playerColor = cellColor;
    }

    public CellColor getPlayerColor() {
        return playerColor;
    }
}
