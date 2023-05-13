package gameOfLife.game;

public enum GameStatus {
    P1_WIN("Player 1 has won!"),
    P2_WIN("Player 2 has won!"),
    DRAW("No player has won. It's a draw!"),
    STILL_PLAYING("Game is still running.");

    private final String print_status;

    GameStatus(String print_status) {
        this.print_status = print_status;

    }

    public String getPrint_status() {
        return print_status;
    }

    public String toString() {
        return getPrint_status();
    }
}
