package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameManagerTest {

    Symbol symbol;
    Symbol aiSymbol;
    Board board;
    Player currentPlayer;
    List<Player> players;
    GameManager gm;
    GameParser eog;
    String name;

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
    }

    @Test
    public void testIfGameManagerGotInitializedWithPlayers(){

        assertSame(players,gm.getPlayers());
    }

    @Test
    public void testIfPlayersMoveIsRead(){

        gm.readMove(5);

        assertSame(board,gm.getCurrentPlayer().getGameBoard());
    }

    @Test
    public void testIfPlayerMovesAreReadAlternately(){


        gm.readMove(5);
        gm.readMove(8);

        assertSame(symbol,gm.getCurrentPlayer().getGameBoard().get(1,1));
        assertSame(aiSymbol,gm.getCurrentPlayer().getGameBoard().get(2,1));
    }

    @Test
    public void testIfMovesArentReadWhenDraw(){

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
            assertEquals("Empty",eog.checkWin().toString());
            assertEquals(e.getMessage(),"Game has ended already!");
        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void testIfMovesArentReadWhenWon(){

        try {
            gm.readMove(5);
            gm.readMove(1);
            gm.readMove(8);
            gm.readMove(2);
            gm.readMove(3);
            gm.readMove(7);
            gm.readMove(4);
            gm.readMove(9);
            gm.readMove(6);

            fail();
        } catch (GameHasEndedException e) {
            assertNotEquals("Empty",gm.getCurrentPlayer().getPlayerSymbol().toString());
            assertEquals("Game has ended already!", e.getMessage());
        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void testIfInputIsPassedToBoard(){

        int input = 5;
        List<Integer> coordinates = gm.getCurrentPlayer().getCellAddressAfterInput(5);

        gm.readMove(input);

        assertFalse(board.isCellEmpty(coordinates.get(0),coordinates.get(1)));
    }

    @Test
    public void testIfCorrectPlayerSymbolIsPassedToBoard(){

        int input = 5;
        List<Integer> coordinates = gm.getCurrentPlayer().getCellAddressAfterInput(input);
        currentPlayer = gm.getCurrentPlayer();

        gm.readMove(input);

        assertEquals(currentPlayer.getPlayerSymbol().orElse(new Symbol()),board.get(coordinates.get(0),coordinates.get(1)));

        input = 6;
        coordinates = gm.getCurrentPlayer().getCellAddressAfterInput(input);
        currentPlayer = gm.getCurrentPlayer();

        gm.readMove(input);

        assertEquals(currentPlayer.getPlayerSymbol().orElse(new Symbol()),board.get(coordinates.get(0),coordinates.get(1)));
    }

    @Test
    public void testIfItValidatesWinAfterMove(){

        try {
            gm.readMove(1);
            gm.readMove(4);
            gm.readMove(2);
            gm.readMove(5);
            gm.readMove(3);

            fail();
        } catch (GameHasEndedException e) {
            assertNotEquals("Empty",gm.getCurrentPlayer().getPlayerSymbol().toString());
            assertEquals("Game has ended already!", e.getMessage());
        } catch (Exception e){
            fail();
        }

    }
}
