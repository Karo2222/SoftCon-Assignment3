package gameOfLife.game;

import gameOfLife.game.IOManager.InputManager;
import gameOfLife.game.move.Move;
import gameOfLife.grid.CellColor;
import gameOfLife.grid.Grid;
import gameOfLife.gui.GUI;

import java.awt.*;
import java.util.ArrayList;

public class Game {

    private final ArrayList<Player> players = new ArrayList<>(2);
    private final Grid grid = new Grid();

    private Player current_player;

    /**
     * Initializes game by asking for player names and creating a list for them.
     */
    public Game() {
        players.addAll(InputManager.getInstance().createPlayers());
        this.current_player = players.get(0);
        players.get(0).setPlayerColor(CellColor.PLAYER1);
        players.get(1).setPlayerColor(CellColor.PLAYER2);
    }

    /**
     * Starts the game by creating a GUI instance after the player wrote 'start' into the terminal.
     */
    void start() {
        InputManager.getInstance().startGame();
        new GUI(this);
    }

    public CellColor getCellColor(int x, int y) {
        return grid.getCell(x, y).getCellColor();
    }

    public String getPlayerName(int player) {
        return players.get(player).getName();
    }

    public CellColor getPlayerCellColor() {
        return current_player.getPlayerColor();
    }

    public CellColor getDisplayCellColor() {
        return current_player.getNextMove().displayColor();
    }

    /**
     * Performs the turn on the given coordinates on the grid. Initiating the move and resetting the turns of the
     * player if necessary. Initiates creating the next generation checks if game is still running or if someone has won.
     * @return GameStatus after the move has been performed.
     */
    public GameStatus turn(int x, int y) {
        GameStatus status = GameStatus.STILL_PLAYING;
        //this check is only there to make sure that no operation is done when there are no turns left (shouldn't happen tho)
        if (!current_player.turns_isEmpty()) {
            if (current_player.getNextMove().isAllowed(grid.getCell(x, y))) {
                current_player.getNextMove().move(grid.getCell(x, y));
                current_player.setNextMove();
            }
            if (current_player.turns_isEmpty()) {
                current_player.resetMoves();
                current_player = getNotCurrent_Player();
                grid.nextGeneration();
                status = grid.checkIfWon();
            }
            grid.nextCellCount();
        }
        return status;
    }

    public int getGeneration() {
        return grid.getGeneration();
    }

    public int getP1_cellCount() {
        return grid.getP1_cellCount();
    }

    public int getP2_cellCount() {
        return grid.getP2_cellCount();
    }

    private Player getNotCurrent_Player() {
        return (current_player.getPlayerColor() == CellColor.PLAYER1) ? players.get(1) : players.get(0);
    }

}
