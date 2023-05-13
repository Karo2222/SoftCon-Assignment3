package game.IOManager;
import gameOfLife.game.IOManager.InputManager;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class InputManagerTest {

    private final String SEPARATOR = System.getProperty("line.separator");

    @Test
    public void startGameTest(){
        System.setIn(new ByteArrayInputStream(
                """
                           
                        kek          
                        start
                        """
                        .getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputManager.getInstance().startGame();
        assertEquals("Type in 'start' to start the Game." + SEPARATOR +
                "Your only option is to start the game. So please type 'start' to start the Game." + SEPARATOR +
                "Your only option is to start the game. So please type 'start' to start the Game." + SEPARATOR
                ,outContent.toString());
    }

    @Test
    public void createPlayersTest(){
        System.setIn(new ByteArrayInputStream(
                """
                        
                        PlayerOne
                        PlayerOne
                        PlayerTwo
                        """
                        .getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputManager.getInstance().createPlayers();

        assertEquals("Please enter the name of the first player:" + SEPARATOR +
                "Please enter a non-empty name." + SEPARATOR +
                "Player #1 added. (PlayerOne)" + SEPARATOR +
                "Please enter the next name." + SEPARATOR +
                "Please enter a name which hasn't been used yet." + SEPARATOR +
                "Player #2 added. (PlayerTwo)" + SEPARATOR +
                "Players saved." + SEPARATOR
                ,outContent.toString());

    }


}
