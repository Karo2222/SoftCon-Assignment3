package gameOfLife.grid;

import java.awt.*;

public enum CellColor {
    PLAYER1(Color.GREEN.darker(), "Player 1"),
    PLAYER2(Color.ORANGE, "Player 2"),
    NO_PLAYER(Color.GRAY, "No Player");

    private final Color color;
    private final String typeAsString;

    CellColor(Color color, String typeAsString) {
        this.color = color;
        this.typeAsString = typeAsString;
    }

    public Color getColor() {
        return color;
    }

    public String getTypeAsString() {
        return typeAsString;
    }

    public String toString() {
        return getTypeAsString();
    }
}
