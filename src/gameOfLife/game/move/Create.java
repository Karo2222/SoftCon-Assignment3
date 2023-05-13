package gameOfLife.game.move;

import gameOfLife.game.Player;
import gameOfLife.grid.Cell;
import gameOfLife.grid.CellColor;

public class Create extends Move {
    public Create(Player player) {
        super(player);
    }
    public void move(Cell cell) {
        cell.setCellColor(player.getPlayerColor());
    }
    @Override
    public boolean isAllowed(Cell cell) {
        return !cell.getAlive();
    }
    public CellColor displayColor() {
        return player.getPlayerColor();
    }
}
