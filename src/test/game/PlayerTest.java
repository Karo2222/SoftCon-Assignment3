package game;

import gameOfLife.game.Player;
import gameOfLife.game.move.Create;
import gameOfLife.game.move.Kill;
import gameOfLife.grid.CellColor;
import gameOfLife.grid.Grid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void equals() {
        Player p_one = new Player("Person1");
        Player p_two = new Player("Person1");
        Player p_three = new Player("Person2");
        Grid test = new Grid();
        Player p_four = null;
        assertEquals(p_one, p_two);
        assertNotEquals(p_one, p_three);
        assertNotEquals(p_one, p_four);
        assertNotEquals(p_one, test);
    }

    @Test
    public void getName() {
        Player p = new Player("Person1");
        assertEquals("Person1", p.getName());
    }

    @Test
    public void moveMethods() {
        Player p = new Player("Person1");
        assertSame(Kill.class, p.getNextMove().getClass());
        p.setNextMove();
        assertSame(Create.class, p.getNextMove().getClass());
        p.setNextMove();
        assertTrue(p.turns_isEmpty());
        p.resetMoves();
        assertSame(Kill.class, p.getNextMove().getClass());
        p.setPlayerColor(CellColor.PLAYER1);
        assertEquals(CellColor.PLAYER1, p.getPlayerColor());
    }

    @Test
    public void hashCodeTest() {
        Player p1 = new Player("Person");
        Player p2 = new Player("Person");
        assertEquals(p1.hashCode(), p2.hashCode());
    }

}
