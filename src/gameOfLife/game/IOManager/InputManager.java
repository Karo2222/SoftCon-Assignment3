package gameOfLife.game.IOManager;

import gameOfLife.game.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {

    private static final InputManager INSTANCE = new InputManager();

    private final static int NUMBER_OF_PLAYERS = 2;

    private InputManager() {
    }

    public static InputManager getInstance() {
        return INSTANCE;
    }

    /**
     * takes the input of the users via terminal and stores Player names in Player instances
     * @return ArrayList<Player> with both Player instances in it
     */
    public ArrayList<Player> createPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the name of the first player:");
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("Please enter a non-empty name.");
                continue;
            }
            Player newPlayer = new Player(name);
            if (playerList.contains(newPlayer)) {
                System.out.println("Please enter a name which hasn't been used yet.");
                continue;
            }
            playerList.add(newPlayer);
            System.out.println("Player #" + playerList.size() + " added. (" + name + ")");

            if (playerList.size() == NUMBER_OF_PLAYERS) {
                System.out.println("Players saved.");
                break;
            }
            System.out.println("Please enter the next name.");
        }
        playerList.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));

        return playerList;
    }

    /**
     * asks the users to start the game by taking the input "start"
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in 'start' to start the Game.");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.equals("start")) {
                System.out.println("Your only option is to start the game. So please type 'start' to start the Game.");
                continue;
            }
            break;
        }

    }
}
