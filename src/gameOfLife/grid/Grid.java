package gameOfLife.grid;

import gameOfLife.game.GameStatus;

public class Grid {
    private final static int WIDTH = 60;
    private final static int HEIGHT = 30;
    private final static int gapInitialFormation = 1;

    private final Cell[][] grid;

    private int generation;
    private int p1_cellCount;

    private int p2_cellCount;

    /**
     * Initializes the grid by creating a two-dimensional array which contains WIDTH*HEIGHT Cell instances.
     */
    public Grid() {
        grid = new Cell[HEIGHT][WIDTH];
        generation = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                grid[j][i] = new Cell();
            }
        }
        initializeStartingCells();
    }

    /**
     * Creating the starting formation for both players, which is adapted to the height, width and gap parameters.
     */
    private void initializeStartingCells() {
        int middle_x = WIDTH/2;
        int middle_y = HEIGHT/2;
        grid[middle_y][middle_x-gapInitialFormation-1].setCellColor(CellColor.PLAYER1);
        grid[middle_y][middle_x-gapInitialFormation-2].setCellColor(CellColor.PLAYER1);
        grid[middle_y][middle_x-gapInitialFormation-3].setCellColor(CellColor.PLAYER1);
        grid[middle_y-1][middle_x-gapInitialFormation-1].setCellColor(CellColor.PLAYER1);
        grid[middle_y-2][middle_x-gapInitialFormation-2].setCellColor(CellColor.PLAYER1);
        p1_cellCount = 5;

        int middle_x_adjusted = (int)Math.ceil(WIDTH/2.0);
        grid[middle_y][middle_x_adjusted+gapInitialFormation].setCellColor(CellColor.PLAYER2);
        grid[middle_y][middle_x_adjusted+gapInitialFormation+1].setCellColor(CellColor.PLAYER2);
        grid[middle_y][middle_x_adjusted+gapInitialFormation+2].setCellColor(CellColor.PLAYER2);
        grid[middle_y-1][middle_x_adjusted+gapInitialFormation].setCellColor(CellColor.PLAYER2);
        grid[middle_y-2][middle_x_adjusted+gapInitialFormation+1].setCellColor(CellColor.PLAYER2);
        p2_cellCount = 5;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public int getGeneration() {
        return generation;
    }
    public int getP1_cellCount() {
        return p1_cellCount;
    }

    public int getP2_cellCount() {
        return p2_cellCount;
    }

    /**
     * Generates the next generation of the cells on the grid by first counting the neighbors of all the cells in the
     * grid and then letting them generate their new color.
     */
    public void nextGeneration() {
        this.generation += 1;
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                Cell currentCell = getCell(x, y);
                currentCell.resetNeighbors();
                countNeighbors(x, y);
            }
        }
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                Cell currentCell = getCell(x, y);
                currentCell.generateNewCellColor();
            }
        }
    }

    /**
     * Counts the cells of both players to update them to the correct value.
     */
    public void nextCellCount() {
        int new_p1_cellCount = 0;
        int new_p2_cellCount = 0;
        for (int x = 0; x < HEIGHT; x++) {
            for (int y = 0; y < WIDTH; y++) {
                Cell currentCell = getCell(x, y);
                if (currentCell.getCellColor() == CellColor.PLAYER1) {
                    new_p1_cellCount += 1;
                } else if (currentCell.getCellColor() == CellColor.PLAYER2) {
                    new_p2_cellCount += 1;
                }
            }
        }
        p1_cellCount = new_p1_cellCount;
        p2_cellCount = new_p2_cellCount;
    }

    /**
     * Counting the neighbors of a given cell, by going through the eight surrounding cells and checking their color.
     */
    public void countNeighbors(int x, int y) {
        int p1_neighbors = 0;
        int p2_neighbors = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!((i < 0 || i >= HEIGHT) || (j < 0 || j >= WIDTH))) {
                    if (!(i == x && j == y)) {
                        CellColor color = getCell(i, j).getCellColor();
                        if (color == CellColor.PLAYER1) {
                            p1_neighbors += 1;
                        } else if (color == CellColor.PLAYER2) {
                            p2_neighbors += 1;
                        }
                    }
                }
            }
        }
        getCell(x, y).setNeighbors(p1_neighbors, p2_neighbors);
    }

    /**
     * Counting the cells of each player and checking if the game is over and someone has won, or if it's a draw.
     * Or if both players still have cells, then the game continues.
     * @return GameStatus (either Player one won, Player two won, draw or still playing).
     * */
    public GameStatus checkIfWon(){
        int cellsPlayerOne = 0;
        int cellsPlayerTwo = 0;
        for(int x = 0; x < HEIGHT; x++){
            for (int y = 0; y < WIDTH; y++){
                if(getCell(x,y).getCellColor() == CellColor.PLAYER1){
                    cellsPlayerOne += 1;
                } else if (getCell(x,y).getCellColor() == CellColor.PLAYER2) {
                    cellsPlayerTwo += 1;
                }
            }
        }
        if(cellsPlayerOne == 0 && cellsPlayerTwo == 0) {
            return GameStatus.DRAW;
        }
        if (cellsPlayerOne == 0){
            return GameStatus.P2_WIN;
        } else if (cellsPlayerTwo == 0) {
            return GameStatus.P1_WIN;
        }
        else {
            return GameStatus.STILL_PLAYING;
        }
    }
}
