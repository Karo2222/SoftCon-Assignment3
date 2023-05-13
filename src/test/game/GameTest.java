package game;

import gameOfLife.game.Game;
import gameOfLife.game.IOManager.InputManager;
import gameOfLife.grid.CellColor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void gameTest() {
        System.setIn(new ByteArrayInputStream(
                """
                        PlayerOne
                        PlayerTwo
                        start
                        """
                        .getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Game testGame = new Game();
        assertEquals(CellColor.PLAYER2, testGame.getCellColor(15, 31));
        assertEquals("PlayerOne", testGame.getPlayerName(0));
        assertEquals(5, testGame.getP1_cellCount());
        assertEquals(5, testGame.getP2_cellCount());
        assertEquals(0, testGame.getGeneration());
        testGame.turn(0, 0);
        testGame.turn(15,28);
        testGame.turn(15, 31);
        testGame.turn(15, 28);
        testGame.turn(0, 0);
        testGame.turn(14, 26);
        testGame.turn(0, 0);
        testGame.turn(15, 32);
    }

}
