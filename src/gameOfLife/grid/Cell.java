package gameOfLife.grid;

public class Cell {
    private CellColor cellColor;
    private int p1_neighbors = 0;
    private int p2_neighbors = 0;

    public Cell() {
        this.cellColor = CellColor.NO_PLAYER;
    }

    public CellColor getCellColor() {
        return cellColor;
    }

    public boolean getAlive() {
        return (cellColor != CellColor.NO_PLAYER);
    }

    public void setNeighbors(int p1_neighbors, int p2_neighbors) {
        this.p1_neighbors = p1_neighbors;
        this.p2_neighbors = p2_neighbors;
    }

    public void setCellColor(CellColor color) {
        this.cellColor = color;
    }

    /**
     * sets number of neighbors (of any color) to zero
     */
    public void resetNeighbors() {
        p1_neighbors = 0;
        p2_neighbors = 0;
    }

    /**
     * generates the cell color of a cell in the next generation based on its neighbors
     */
    public void generateNewCellColor() {
        int neighboursBoth = p1_neighbors + p2_neighbors;
        if (getAlive()) {
            if (!(neighboursBoth == 2 || neighboursBoth == 3)) {
                setCellColor(CellColor.NO_PLAYER);
            }
        } else {
            if (neighboursBoth == 3) {
                if (p1_neighbors > p2_neighbors) {
                    setCellColor(CellColor.PLAYER1);
                } else {
                    setCellColor(CellColor.PLAYER2);
                }
            }
        }
    }
}
