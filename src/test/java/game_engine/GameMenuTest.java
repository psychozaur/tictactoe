package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameMenuTest {

    Symbol symbol;
    Symbol aiSymbol;
    Board board;
    Player currentPlayer;
    List<Player> players;
    GameManager gm;
    GameParser eog;
    BoardDisplay bd;
    GameMenu menu;

    int numberInput;
    String nameInput;

    @Before
    public void setUp(){
        symbol = new Cross();
        aiSymbol = new Nought();
        board = new Board(3);
        eog = new GameParser(board);
        players = Arrays.asList(new HumanPlayer(board),
                new AIPlayer(board));
        gm = new GameManager(players, eog);
        bd = new BoardDisplay(board);
        menu = new GameMenu(gm);
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
//        menu.processGameState();
        bd.display();
    }

    @Test
    public void testSetupDisplay(){

        menu.displayGameTitle();
//        menu.processGameState();
        bd.display();

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
//        menu.processChoice();

//        menu.processGameState();

        assertFalse(menu.isQuit());

    }
}
