package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    Board board;
    Symbol symbol;
    String name;
    Player player;

    @Before
    public void setUp(){
        board = new Board(3);
        symbol = new Cross();
        player = new HumanPlayer(board);
        name = "Marcin";
        player.setName(name);
        player.setPlayerSymbol(symbol);
    }

    @Test
    public void testIfHumanPlayerCreationIsPossible(){

        assertEquals(symbol,((HumanPlayer) player).getPlayerSymbol().orElse(new Symbol()));

    }

    @Test
    public void testIsCorrectAddressReturnedAfterEnteringInput(){

        int search = 5;

        List<Integer> coordinates = ((HumanPlayer) player).getCellAddressAfterInput(search, board);

        assertEquals(Arrays.asList(Integer.valueOf(1), Integer.valueOf(1)), coordinates);
    }

    @Test
    public void testIfIllegalInputThrowsException(){

        int search = -1;

        try {
            player.getCellAddressAfterInput(search, board);
            fail();
        } catch (IllegalInputException e) {
            assertEquals(e.getMessage(),"Input must be between 1 and 9");
        } catch (Exception e){
            fail();
        }

    }
}
