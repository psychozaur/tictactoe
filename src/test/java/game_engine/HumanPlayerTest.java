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

    @Before
    public void setUp(){
        board = new Board(3);
        symbol = new Cross();
    }

    @Test
    public void testIfHumanPlayerCreationIsPossible(){

        Player player = new HumanPlayer(symbol,board);

        assertEquals(symbol,((HumanPlayer) player).getPlayerSymbol());

    }

    @Test
    public void testIsCorrectAddressReturnedAfterEnteringInput(){

        Player player = new HumanPlayer(symbol,board);
//        Scanner scanner = new Scanner(System.in);

//        int search = scanner.nextInt();
        int search = 5;

        List<Integer> coordinates = ((HumanPlayer) player).getCellAddressAfterInput(search);

        assertEquals(Arrays.asList(Integer.valueOf(1), Integer.valueOf(1)), coordinates);
    }

    @Test
    public void testIfIllegalInputThrowsException(){

        Player player = new HumanPlayer(symbol,board);
        int search = -1;

        try {
            ((HumanPlayer) player).getCellAddressAfterInput(search);
            fail();
        } catch (IllegalInputException e) {
            assertEquals(e.getMessage(),"Input must be between 1 and 9");
        } catch (Exception e){
            fail();
        }

    }
}
