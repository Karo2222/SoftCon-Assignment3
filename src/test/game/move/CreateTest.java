package game.move;
import gameOfLife.game.Player;
import gameOfLife.game.move.Create;
import gameOfLife.game.move.Kill;
import gameOfLife.game.move.Move;
import gameOfLife.grid.CellColor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateTest {

    @Test
    public void displayColorTest() {
        Move m = new Kill(new Player("Test"));

        assertEquals(m.displayColor(), CellColor.NO_PLAYER);
    }
}
