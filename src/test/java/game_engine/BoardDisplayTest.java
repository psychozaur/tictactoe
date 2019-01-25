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
    EndOfGame eog;
    BoardDisplay bd;
    String name;

    @Before
    public void setUp(){
        name = "Marcin";
        symbol = new Cross();
        aiSymbol = new Nought();
        board = new Board(3);
        eog = new EndOfGame(board);
        players = Arrays.asList(new HumanPlayer(symbol, board, name),
                new AIPlayer(aiSymbol, board));
        gm = new GameManager(players, eog);
        bd = new BoardDisplay(board);
    }

    @Test
    public void testHowItDisplaysEmptyBoard(){

        bd.display();

    }

    @Test
    public void testHowItDisplaysFullBoard(){

        try {
            gm.readMove(5);
            gm.readMove(1);
            gm.readMove(8);
            gm.readMove(2);
            gm.readMove(3);
            gm.readMove(7);
            gm.readMove(4);
            gm.readMove(6);
            gm.readMove(9);

            fail();
        } catch (GameHasEndedException e) {
            assertNotEquals("Empty",gm.getCurrentPlayer().getPlayerSymbol().toString());
            assertEquals("Game has ended already!", e.getMessage());
        } catch (Exception e){
            fail();
        } finally {
            bd.display();
        }

    }

    @Test
    public void testHowItDisplaysPartiallyFullBoard(){

        try {
            for (int i = 1; i <= (board.getSize() * board.getSize()); i++){
                gm.readMove(i);
            }

            fail();
        } catch (GameHasEndedException e) {
            assertNotEquals("Empty",gm.getCurrentPlayer().getPlayerSymbol().toString());
            assertEquals("Game has ended already!", e.getMessage());
        } catch (Exception e){
            fail();
        } finally {
            bd.display();
        }

    }
}
