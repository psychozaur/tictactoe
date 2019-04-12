package game_engine;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AIPlayerTest {

    Board board;
    Symbol symbol;
    Player player;

    @Before
    public void setUp(){
        board = new Board(3);
        symbol = new Cross();
        player = new AIPlayer(board);
        player.setPlayerSymbol(symbol);
    }

    @Test
    public void testIfAIPlayerCreationIsPossible(){

        assertEquals(symbol,((AIPlayer) player).getPlayerSymbol().orElse(new Symbol()));

    }

    @Test
    public void testIfReturnedAddressIsFreeToSelect(){

        board.insert(symbol,1,1);
        int search = 5;

        try {
            ((AIPlayer) player).getCellAddressAfterInput(search, board);
            fail();
        } catch (IllegalInputException e) {
            assertEquals(e.getMessage(),"This field is already occupied!");
        } catch (Exception e){
            fail();
        }

    }
}
