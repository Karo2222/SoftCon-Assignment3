package gameOfLife.gui;

import gameOfLife.game.Game;
import gameOfLife.game.GameStatus;
import gameOfLife.grid.CellColor;
import gameOfLife.grid.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI implements MouseMotionListener, MouseListener {
    JFrame frame;
    JLabel label_generation;
    JLabel label_player_turn;
    JButton endButton;
    JLabel label_p1_cellCount;
    JLabel label_p2_cellCount;
    private final int PANEL_SIZE = 400;
    private final int SPACING = 1;
    private int mouse_x = 0;
    private int mouse_y = 0;
    boolean game_has_ended = false;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension dim = tk.getScreenSize();
    int height = (int) dim.getHeight();
    int width = (int) dim.getWidth();
    int size = (((width/10)*9)-(((width/10)*9)%Grid.getWIDTH()))/Grid.getWIDTH();
    int start_grid_y = 120;
    int start_grid_x = ((width-size*Grid.getWIDTH())-((width-size*Grid.getWIDTH())%2))/2;
    private final Game GAME;

    /**
     * basically creates all the necessary labels and panels which get displayed on the JFrame
     *
     */
    public GUI(Game game) {
        this.GAME = game;
        frame = new JFrame("Game Of Life");
        frame.setBackground(Color.DARK_GRAY);

        //panel+label of Player 1
        JPanel panel_player_1 = newPanel(25, 10);
        JLabel label_player_1 = newLabel(1);
        label_player_1.setForeground(CellColor.PLAYER1.getColor());
        panel_player_1.add(label_player_1);

        //panel+label of Player 2
        JPanel panel_player_2 = newPanel(25, 40);
        JLabel label_player_2 = newLabel(2);
        label_player_2.setForeground(CellColor.PLAYER2.getColor());
        panel_player_2.add(label_player_2);

        //panel+label that displays Generation-Number
        JPanel panel_generation = newPanel(start_grid_x,start_grid_y-30);
        label_generation = newGenerationLabel();
        panel_generation.add(label_generation);

        //button to close the window at the end of game
        endButton = new JButton("Click Here to close the Game");
        endButton.setBounds(width/2-150,70,300,30);
        frame.add(endButton);
        endButton.setVisible(false);
        endButton.addActionListener(ef -> frame.dispose());

        //panel+label that displays which player is on turn
        JPanel panel_player_turn = newPanel(width/2-200, 40);
        label_player_turn = newPlayerTurnLabel();
        panel_player_turn.add(label_player_turn);

        //creates 2 panels+labels that display the number of alive cells for each player
        newCellCountLabel();

        //creates a JPanel which represents the Grid
        Board board = new Board();
        board.addMouseMotionListener(this);
        board.addMouseListener(this);

        frame.add(board);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * @param x (coordinate where to place the new panel)
     * @param y (coordinate where to place the new panel)
     * @return JPanel
     */
    public JPanel newPanel(int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setLocation(x, y);
        panel.setSize(PANEL_SIZE, 30);
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);
        return panel;
    }

    public JLabel newLabel(int player_number) {
        JLabel label = new JLabel("Player " + player_number + ": " + GAME.getPlayerName(player_number - 1));
        setFontAndSizeOfLabel(label, PANEL_SIZE, Font.PLAIN);
        return label;
    }

    /**
     * creates the label which displays the number of generations
     */
    public JLabel newGenerationLabel() {
        label_generation = new JLabel("Generation #0");
        label_generation.setForeground(Color.WHITE);
        setFontAndSizeOfLabel(label_generation, PANEL_SIZE /2, Font.PLAIN);
        return label_generation;
    }

    /**
     * creates the label which displays which player's turn it is
     */
    public JLabel newPlayerTurnLabel() {
        label_player_turn = new JLabel( CellColor.PLAYER1.toString().toUpperCase()+"'s TURN", SwingConstants.CENTER);
        label_player_turn.setForeground(CellColor.PLAYER1.getColor());
        setFontAndSizeOfLabel(label_player_turn, PANEL_SIZE, Font.BOLD);
        return label_player_turn;
    }

    /**
     * creates the label which displays the amount of alive cells of each player
     */
    public void newCellCountLabel() {
        JPanel panel_p1_cellCount = newPanel(width - PANEL_SIZE, start_grid_y-60);
        JPanel panel_p2_cellCount = newPanel(width - PANEL_SIZE, start_grid_y-30);
        label_p1_cellCount = new JLabel();
        label_p2_cellCount = new JLabel();
        setNewCellCountLabel();
        label_p1_cellCount.setForeground(CellColor.PLAYER1.getColor());
        label_p2_cellCount.setForeground(CellColor.PLAYER2.getColor());
        setFontAndSizeOfLabel(label_p1_cellCount, PANEL_SIZE, Font.PLAIN);
        setFontAndSizeOfLabel(label_p2_cellCount, PANEL_SIZE, Font.PLAIN);

        panel_p1_cellCount.add(label_p1_cellCount);
        panel_p2_cellCount.add(label_p2_cellCount);

    }

    public void setFontAndSizeOfLabel(JLabel label, int size, int font) {
        label.setSize(size, 30);
        label.setFont(new Font("Serif", font, 25));
    }

    /**
     * updates the number of alive cells each player has after a new generation
     */
    public void setNewCellCountLabel() {
        label_p1_cellCount.setText("# of " + GAME.getPlayerName(0) + "'s Cells: " + GAME.getP1_cellCount());
        label_p2_cellCount.setText("# of " + GAME.getPlayerName(1)+"'s Cells: "+ GAME.getP2_cellCount());

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouse_x = e.getX();
        mouse_y = e.getY();
        frame.repaint();
    }

    /**
     * checks if a click happened inside a cell on the grid and then calls GAME.turn() if click was valid
     * if the game hasn't ended yet, all the labels get updated. Else, displayWinner(gameStatus) and setGame_has_ended() are called
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        mouse_x = e.getX();
        mouse_y = e.getY();
        int x = inBoxX(true);
        int y = inBoxX(false);
        if(!game_has_ended) {
            if (x != -1 && y != -1) {
                GameStatus gameStatus = GAME.turn(y, x);
                frame.repaint();
                if (gameStatus != GameStatus.STILL_PLAYING) {
                    display_Winner(gameStatus);
                    setGame_has_ended();
                }
                else {
                    updateLabels();
                }
            }
        }
    }

    /**
     * sets the endButton visibility and game_has_ended to true
     */
    private void setGame_has_ended() {
        game_has_ended = true;
        endButton.setVisible(true);
    }

    private void updateLabels() {
        label_generation.setText("Generation #"+ GAME.getGeneration());
        label_player_turn.setText(GAME.getPlayerCellColor().getTypeAsString().toUpperCase()+"'s TURN");
        label_player_turn.setForeground(GAME.getPlayerCellColor().getColor());
        setNewCellCountLabel();
    }

    /**
     * updates all labels according to the winner
     * @param win
     */
    public void display_Winner(GameStatus win){
        String winner;
        Color winner_color;
        if (win == GameStatus.P1_WIN) {
            winner = GAME.getPlayerName(0);
            winner_color = CellColor.PLAYER1.getColor();
        } else if(win == GameStatus.P2_WIN) {
            winner = GAME.getPlayerName(1);
            winner_color = CellColor.PLAYER2.getColor();
        }
        else {
            winner_color = CellColor.NO_PLAYER.getColor();
            winner = "Nobody.";
        }
        label_player_turn.setForeground(winner_color);
        label_player_turn.setText(win.toString());
        label_generation.setText("Generation #"+ GAME.getGeneration());
        setNewCellCountLabel();
        System.out.println(win);
        System.out.println("The Winner is: " + winner);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * returns the x or y coordinate of a cell a player has clicked on
     * depending on the input (boolean), either the x or the y coordinate of that cell are returned
     */
    public int inBoxX(boolean x) {
        for (int i = 0; i < Grid.getWIDTH(); i++) {
            for (int j = 0; j < Grid.getHEIGHT(); j++) {
                if (mouse_x >= SPACING + i * size+start_grid_x && mouse_x < SPACING + i * size + size - 2 * SPACING +start_grid_x
                        && mouse_y >= start_grid_y + j * size && mouse_y < start_grid_y + j * size + size) {
                    if (x) {
                        return i;
                    } else {
                        return j;
                    }
                }

            }
        }
        return -1;
    }

    public class Board extends JPanel {

        /**
         * paints the grid (all cells) + paints the cell on which the mouse is currently positioned on in a different color (depending on the current move)
         */
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));

            for (int i = 0; i < Grid.getWIDTH(); i++) {
                for (int j = 0; j < Grid.getHEIGHT(); j++) {
                    g.setColor(GAME.getCellColor(j, i).getColor());
                    g.fillRect(SPACING + i * size+start_grid_x, SPACING + j * size + start_grid_y, size - 2 * SPACING, size - 2 * SPACING);
                    if(!game_has_ended) {
                        if (mouse_x >= SPACING + i * size + start_grid_x && mouse_x < SPACING + i * size + size - 2 * SPACING + start_grid_x
                                && mouse_y >= start_grid_y + j * size && mouse_y < start_grid_y + j * size + size) {
                            g.setColor(GAME.getPlayerCellColor().getColor());
                            g.fillRect(SPACING + i * size + start_grid_x, SPACING + j * size + start_grid_y, size - 2 * SPACING, size - 2 * SPACING);
                            //if current move is "Kill", draw "X"
                            if (GAME.getDisplayCellColor() == CellColor.NO_PLAYER) {
                                g.setColor(Color.RED);
                                g.drawLine(SPACING + i * size + 1 + start_grid_x, SPACING + j * size + start_grid_y + 1, SPACING + i * size + size - 2 * SPACING - 1 - 1 + start_grid_x, SPACING + j * size + start_grid_y + size - 2 * SPACING - 1 - 1);
                                g.drawLine(SPACING + i * size + 1 + start_grid_x, SPACING + j * size + start_grid_y + size - 2 * SPACING - 1 - 1, SPACING + i * size + size - 2 * SPACING - 1 - 1 + start_grid_x, SPACING + j * size + start_grid_y + size - 2 * SPACING - 1 - size + 3 * SPACING + 1);
                            }
                        }
                    }
                }
            }
        }
    }
}
