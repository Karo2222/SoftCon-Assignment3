package grid;
import gameOfLife.game.GameStatus;
import gameOfLife.grid.Cell;
import gameOfLife.grid.CellColor;
import gameOfLife.grid.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void gridTest(){
        Grid testGrid = new Grid();
        assertSame(Cell.class, testGrid.getCell(0,0).getClass());
        assertEquals(30, Grid.getHEIGHT());
        assertEquals(60, Grid.getWIDTH());
        assertEquals(0, testGrid.getGeneration());
        assertEquals(5, testGrid.getP1_cellCount());
        assertEquals(5, testGrid.getP2_cellCount());
        testGrid.nextGeneration();
        testGrid.nextCellCount();
        assertEquals(5, testGrid.getP1_cellCount());
        assertEquals(5, testGrid.getP2_cellCount());
        assertEquals(GameStatus.STILL_PLAYING, testGrid.checkIfWon());
    }

    @Test
    public void playerOneWins(){
        Grid testGrid = new Grid();
        testGrid.getCell(15, 31).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 32).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 33).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(14, 31).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(13, 32).setCellColor(CellColor.NO_PLAYER);
        testGrid.nextCellCount();
        assertEquals(5, testGrid.getP1_cellCount());
        assertEquals(0, testGrid.getP2_cellCount());
        assertEquals(GameStatus.P1_WIN, testGrid.checkIfWon());
    }

    @Test
    public void playerTwoWins(){
        Grid testGrid = new Grid();
        testGrid.getCell(15, 28).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 27).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 26).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(14, 28).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(13, 27).setCellColor(CellColor.NO_PLAYER);
        testGrid.nextCellCount();
        assertEquals(0, testGrid.getP1_cellCount());
        assertEquals(5, testGrid.getP2_cellCount());
        assertEquals(GameStatus.P2_WIN, testGrid.checkIfWon());
    }

    @Test
    public void draw(){
        Grid testGrid = new Grid();
        testGrid.getCell(15, 31).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 32).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 33).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(14, 31).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(13, 32).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 28).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 27).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(15, 26).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(14, 28).setCellColor(CellColor.NO_PLAYER);
        testGrid.getCell(13, 27).setCellColor(CellColor.NO_PLAYER);
        testGrid.nextCellCount();
        assertEquals(0, testGrid.getP1_cellCount());
        assertEquals(0, testGrid.getP2_cellCount());
        assertEquals(GameStatus.DRAW, testGrid.checkIfWon());
    }
}
