package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GameManagerTest {

    Symbol symbol;
    Symbol aiSymbol;
    Board board;
    Board board2;
    Player currentPlayer;
    List<Player> players;
    GameManager gm;
    GameManager gm2;
    GameParser parser;
    GameParser parser2;
    String name;
    List<Integer> movesToDraw;
    List<Integer> movesToWin;


    @Before
    public void setUp(){
        name = "Marcin";
        symbol = new Cross();
        aiSymbol = new Nought();
        board = new Board(3);
        parser = new GameParser(board);
        players = Arrays.asList(new HumanPlayer(board),
                            new AIPlayer(board));
        players.get(0).setPlayerSymbol(symbol);
        players.get(1).setPlayerSymbol(aiSymbol);
        gm = new GameManager(players, parser);
        board2 = new Board(3);
        parser2 = new GameParser(board2);
        gm2 = new GameManager(players, parser2);
        gm2.readMove(5);
        gm2.readMove(4);
        gm2.readMove(6);

        movesToDraw = Arrays.asList(5,1,8,2,3,7,4,6,9);
        movesToWin = Arrays.asList(5,1,8,2,3,7,4,9,6);

    }

    @Test
    public void testIfGameManagerGotInitializedWithPlayers(){

        assertSame(players,gm.getPlayers());
    }

    @Test
    public void testIfPlayersMoveIsRead(){

        gm.readMove(5);
        gm.readMove(4);
        gm.readMove(6);

        assertEquals(board.getState(),board2.getState());

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
            for (Integer move : movesToDraw){
                gm.readMove(move);
            }

            fail();
        } catch (GameHasEndedException e) {
            assertEquals("Empty",parser.checkWin().toString());
            assertEquals(e.getMessage(),"Game has ended already!");
        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void testIfMovesArentReadWhenWon(){

        try {
            for (Integer move : movesToWin){
                gm.readMove(move);
            }

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
        List<Integer> coordinates = gm.getCurrentPlayer().getCellAddressAfterInput(5, board);

        gm.readMove(input);

        assertFalse(board.isCellEmpty(coordinates.get(0),coordinates.get(1)));
    }

    @Test
    public void testIfCorrectPlayerSymbolIsPassedToBoard(){

        int input = 5;
        List<Integer> coordinates = gm.getCurrentPlayer().getCellAddressAfterInput(input, board);
        currentPlayer = gm.getCurrentPlayer();

        gm.readMove(input);

        assertEquals(currentPlayer.getPlayerSymbol().orElse(new Symbol()),board.get(coordinates.get(0),coordinates.get(1)));

        input = 6;
        coordinates = gm.getCurrentPlayer().getCellAddressAfterInput(input, board);
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
