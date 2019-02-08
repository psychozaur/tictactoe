package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AIPlayerTest {

    Board board;
    Symbol symbol;

    @Before
    public void setUp(){
        board = new Board(3);
        symbol = new Cross();
    }

    @Test
    public void testIfAIPlayerCreationIsPossible(){

        Player player = new AIPlayer(board);

        assertEquals(symbol,((AIPlayer) player).getPlayerSymbol());

    }

    @Test
    public void testIfReturnedAddressIsFreeToSelect(){

        board.insert(symbol,1,1);
        Player player = new AIPlayer(board);
        int search = 5;

        try {
            ((AIPlayer) player).getCellAddressAfterInput(search);
            fail();
        } catch (IllegalInputException e) {
            assertEquals(e.getMessage(),"This field is already occupied!");
        } catch (Exception e){
            fail();
        }

    }
}
