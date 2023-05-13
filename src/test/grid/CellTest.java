package grid;
import gameOfLife.grid.Cell;
import gameOfLife.grid.CellColor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void cellColor(){
        Cell testCell = new Cell();
        assertEquals(CellColor.NO_PLAYER, testCell.getCellColor());
        assertFalse(testCell.getAlive());
        testCell.setCellColor(CellColor.PLAYER2);
        assertEquals(CellColor.PLAYER2, testCell.getCellColor());
        assertTrue(testCell.getAlive());
    }

    @Test
    public void neighbourAndGenerateColor(){
        Cell testCell = new Cell();
        testCell.setCellColor(CellColor.PLAYER1);
        testCell.generateNewCellColor();
        testCell.generateNewCellColor();
        testCell.setNeighbors(2,1);
        testCell.generateNewCellColor();
        testCell.resetNeighbors();
        testCell.generateNewCellColor();
        testCell.setNeighbors(1,2);
        testCell.generateNewCellColor();
        testCell.setNeighbors(1,1);
        testCell.generateNewCellColor();
        testCell.setNeighbors(2,1);
        testCell.generateNewCellColor();

    }
}
