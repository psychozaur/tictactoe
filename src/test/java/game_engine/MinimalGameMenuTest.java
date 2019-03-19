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
    EndOfGame eog;
    BoardDisplay bd;
    MinimalGameMenu menu;
    List<Integer> moves;

    @Before
    public void setUp(){
        symbol1 = new Cross();
        symbol2 = new Nought();
        board = new Board(3);
        eog = new EndOfGame(board);
        players = Arrays.asList(new HumanPlayer(board),
                new HumanPlayer(board));
        players.get(0).setName("Krzychu");
        players.get(1).setName("Zbychu");
        players.get(0).setPlayerSymbol(symbol1);
        players.get(1).setPlayerSymbol(symbol2);
        gm = new GameManager(players, eog);
        bd = new BoardDisplay(board);
        menu = new MinimalGameMenu(gm);
        moves = Arrays.asList(5,4,1,9,2,3,6,8,7);
    }

    @Test
    public void testNamesDisplay(){
        menu.displayNamesAndSymbols();
    }

    @Test
    public void testNamesWithBoardDisplay(){
        menu.displayNamesAndSymbols();
        bd.display();
    }

    @Test
    public void testMoveDisplay(){
        for (Integer move : moves){
            menu.displayNamesAndSymbols();

            try {
                gm.readMove(move);
            } catch (GameHasEndedException e) {
                System.out.println(gm.getCurrentPlayer().getName().orElse("") + " won.");
            } finally {
                bd.display();
            }

        }

        assertEquals(GameState.GAME_DRAW, eog.getGameState());
    }
}
