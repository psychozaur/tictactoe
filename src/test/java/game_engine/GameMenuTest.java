package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    public void testIfItDisplaysProperMessagesForUser(){

        menu.displayGameTitle();
        menu.display();
    }
}
