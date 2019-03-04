package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameMenuTest {

    Symbol symbol;
    Symbol aiSymbol;
    Board board;
    Player currentPlayer;
    List<Player> players;
    List<Player> players2;
    GameManager gm;
    EndOfGame eog;
    BoardDisplay bd;
    GameMenu menu;

    int numberInput;
    String nameInput;

    @Before
    public void setUp(){
        symbol = new Cross();
        aiSymbol = new Nought();
        board = new Board(3);
        eog = new EndOfGame(board);
        players = Arrays.asList(new HumanPlayer(board),
                new AIPlayer(board));
        players2 = Arrays.asList(new HumanPlayer(board),
                new HumanPlayer(board));
        gm = new GameManager(players, eog);
        bd = new BoardDisplay(board);
        menu = new GameMenu(gm, bd);
    }

    @Test
    public void testIfIsQuit(){

        boolean result;
        menu.displayGameTitle();

        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.processGameState(
                (currentPlayer) -> GameState.GAME_SETUP,
                (currentPlayer) -> GameState.GAME_QUIT
        );

        result = menu.isQuit();

        assertTrue(result);

    }

    @Test
    public void testInitialDisplay(){

        menu.displayGameTitle();
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.processGameState(
                (currentPlayer) -> GameState.GAME_SETUP,
                (currentPlayer) -> GameState.GAME_QUIT
        );
    }

    @Test
    public void testIsSetup(){

        menu.displayGameTitle();

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        menu.processGameState(
                (currentPlayer) -> GameState.GAME_SETUP,
                (currentPlayer) -> GameState.GAME_QUIT
        );

        assertEquals(GameState.GAME_SETUP,menu.getSelector());

    }

    @Test
    public void testSetupDisplay(){

        menu.displayGameTitle();

//        InputStream stdin = System.in;
//        String input = "1";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
        menu.processGameState(
                (currentPlayer) -> GameState.GAME_SETUP,
                (currentPlayer) -> GameState.GAME_QUIT
        );

//        System.setIn(stdin);
//        input = "1";
//        InputStream in2 = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in2);
        menu.processGameState(
                (currentPlayer) -> players,
                (currentPlayer) -> players2
        );
//        System.setIn(stdin);

        assertEquals(players.get(0),menu.getPlayerOne());
        assertEquals(players.get(1),menu.getPlayerTwo());

    }

}
