package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BoardDisplayTest {

    Symbol symbol;
    Symbol aiSymbol;
    Board board;
    Player currentPlayer;
    List<Player> players;
    GameManager gm;
    GameParser eog;
    BoardDisplay bd;
    String name;
    List<Integer> movesToDraw;

    @Before
    public void setUp(){
        name = "Marcin";
        symbol = new Cross();
        aiSymbol = new Nought();
        board = new Board(3);
        eog = new GameParser(board);
        players = Arrays.asList(new HumanPlayer(board),
                new AIPlayer(board));
        players.get(0).setPlayerSymbol(symbol);
        players.get(1).setPlayerSymbol(aiSymbol);
        gm = new GameManager(players, eog);
        bd = new BoardDisplay(board);

        movesToDraw = Arrays.asList(5,1,8,2,3,7,4,6,9);
    }

    @Test
    public void testHowItDisplaysEmptyBoard(){

        bd.display();

    }

    @Test
    public void testHowItDisplaysFullBoard(){

        try {
            for (Integer move : movesToDraw){
                gm.readMove(move);
            }

            bd.display();

            fail();
        } catch (GameHasEndedException e) {
            assertNotEquals("Empty",gm.getCurrentPlayer().getPlayerSymbol().toString());
            assertEquals("Game has ended already!", e.getMessage());
        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void testHowItDisplaysPartiallyFullBoard(){


            try {
                for (int i = 1; i <= (board.getSize() * board.getSize()); i++) {
                    gm.readMove(i);
                }

                bd.display();

                fail();
            } catch (GameHasEndedException e) {
                assertNotEquals("Empty", gm.getCurrentPlayer().getPlayerSymbol().toString());
                assertEquals("Game has ended already!", e.getMessage());
            } catch (Exception e) {
                fail();
            }

    }
}
