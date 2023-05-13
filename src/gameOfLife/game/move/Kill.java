package gameOfLife.game.move;

import gameOfLife.game.Player;
import gameOfLife.grid.Cell;
import gameOfLife.grid.CellColor;

public class Kill extends Move {
    public Kill(Player player) {
        super(player);
    }
    @Override
    public void move(Cell cell) {
        cell.setCellColor(CellColor.NO_PLAYER);
    }

    @Override
    public boolean isAllowed(Cell cell) {
        return cell.getAlive() && cell.getCellColor() != player.getPlayerColor();
    }

    public CellColor displayColor() {
        return CellColor.NO_PLAYER;
    }
}
