package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class GameMenuTest {

    Symbol symbol;
    Symbol aiSymbol;
    Board board;
    Player currentPlayer;
    List<Player> players;
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
        players = Arrays.asList(new HumanPlayer(symbol, board, "Marcin"),
                new AIPlayer(aiSymbol, board));
        gm = new GameManager(players, eog);
        bd = new BoardDisplay(board);
        menu = new GameMenu(gm);
    }

    @Test
    public void testIfIsQuit(){

        boolean result;
        menu.displayGameTitle();
        numberInput = 2;

        result = menu.isQuit(0,2);

        assertTrue(result);

    }

    @Test
    public void testInitialDisplay(){

        menu.displayGameTitle();
        menu.displayOptions(1);
        bd.display();
    }
}