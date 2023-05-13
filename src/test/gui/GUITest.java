package gui;
import gameOfLife.game.Game;
import gameOfLife.gui.GUI;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class GUITest {

    @Test
    public void newPanelTest() {

        int x = 20;
        int y = 50;

        System.setIn(new ByteArrayInputStream(
                """
                        Aplayer
                        Bplayer
                        """
                        .getBytes()));

        GUI gui = new GUI(new Game());
        JPanel panel = gui.newPanel(x, y);
        assertEquals(x, panel.getLocation().getX());
        assertEquals(y, panel.getLocation().getY());
    }
    @Test
    public void newLabelTest() {

        System.setIn(new ByteArrayInputStream(
                """
                        Aplayer
                        Bplayer
                        """
                        .getBytes()));

        GUI gui = new GUI(new Game());
        JLabel label1 = gui.newLabel(1);
        JLabel label2 = gui.newLabel(2);
        assertEquals("Player 1: Aplayer", label1.getText());
        assertEquals("Player 2: Bplayer", label2.getText());
    }

}
