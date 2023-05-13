package game.move;
import gameOfLife.game.Player;
import gameOfLife.game.move.Create;
import gameOfLife.game.move.Move;
import gameOfLife.grid.CellColor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KillTest {

    @Test
    public void displayColorTest() {
        Move c = new Create(new Player("Test"));

        assertEquals(c.displayColor(), CellColor.NO_PLAYER);
    }
}
