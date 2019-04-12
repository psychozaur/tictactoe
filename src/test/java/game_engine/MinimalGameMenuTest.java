package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MinimalGameMenuTest {

    Symbol symbol1;
    Symbol symbol2;
    Board board;
    Player currentPlayer;
    List<Player> players;
    GameManager gm;
    GameParser eog;
    BoardDisplay bd;
    MinimalGameMenu menu;
    List<Integer> moves;

    @Before
    public void setUp(){
        symbol1 = new Cross();
        symbol2 = new Nought();
        board = new Board(3);
        eog = new GameParser(board);
        players = Arrays.asList(new HumanPlayer(board),
                new HumanPlayer(board));
        players.get(0).setName("Krzychu");
        players.get(1).setName("Zbychu");
        players.get(0).setPlayerSymbol(symbol1);
        players.get(1).setPlayerSymbol(symbol2);
        gm = new GameManager(players, eog);
        bd = new BoardDisplay(board);
        menu = new MinimalGameMenu(gm,bd);
        moves = Arrays.asList(5,4,1,9,2,3,8,6,7);
    }

    @Test
    public void testNamesDisplay(){
        menu.displayPlayerNamesAndSymbols();
    }

    @Test
    public void testNamesWithBoardDisplay(){
        menu.displayPlayerNamesAndSymbols();
        bd.display();
    }

    @Test
    public void testMoveDisplay(){
        for (Integer move : moves){
            menu.readMoveAndDisplayAll(move);
        }

        assertEquals(GameState.GAME_WON, eog.getGameState());
    }


}
