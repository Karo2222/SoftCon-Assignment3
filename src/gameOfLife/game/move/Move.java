package gameOfLife.game.move;
import gameOfLife.game.Player;
import gameOfLife.grid.Cell;
import gameOfLife.grid.CellColor;

public abstract class Move {
    Player player;
    public Move(Player player) {
        this.player = player;
    }

    /**
     * makes a move (changes color of cell)
     */
    public abstract void move(Cell cell);

    /**
     * checks if the current move is valid -> returns true if move possible, else false
     * @return boolean
     */
    public abstract boolean isAllowed(Cell cell);
    public abstract CellColor displayColor();
}
