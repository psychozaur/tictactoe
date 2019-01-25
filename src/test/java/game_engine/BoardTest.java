package game_engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testIfBoardIsCreatedSuccessfully(){
        //GIVEN
        int n = 3;

        //WHEN
        Board board = new Board(n);

        //THEN
        assertEquals(3,board.getSize());
    }


    @Test
    public void testIfExtendedBoardIsCreatedSuccessfully(){
        //GIVEN
        int n = 4;

        //WHEN
        Board board = new Board(n);

        //THEN
        assertEquals(4,board.getSize());
    }

    @Test
    public void testIfInitialBoardIsEmpty(){

        Board board = new Board(3);

        for (int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){
                assertTrue(board.isCellEmpty(i,j));
            }
    }

    @Test
    public void testInsertSymbolsToTheBoard(){
        Board board = new Board(3);
        Symbol symbol = new Cross();

        board.insert(symbol, 1, 1);
//        symbol = board.get(1,1);



        assertSame(symbol, board.get(1,1));
//        assertTrue(board.get(1,1).isTic());
//        assertTrue(board.containsTic(1,1));
    }

    @Test
    public void testIfSameMoveTwiceIsPossible(){

        //GIVEN
        Board board = new Board(3);
        Symbol symbol = new Cross();
        board.insert(symbol, 1, 1);

        //WHEN
        board.insert(new Nought(), 1, 1);

        //THEN
        assertSame(symbol,board.get(1,1));

    }

    @Test
    public void testOutOfBoundCoordinates(){

        Board board = new Board(3);

        try {
            board.insert(new Nought(),-1,-1);
            fail();
        } catch (MoveOutOfBoundsException e) {
            assertEquals(e.getMessage(),"Move out of bounds!");
        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void testIfGettingCorrectAddressIsPossible(){

        Board board = new Board(3);

        assertEquals("Empty",board.get(1,1).toString());
    }

    @Test
    public void testIfGettingIncorrectAddressIsPossible(){

        Board board = new Board(3);

        try {
            board.get(-1,-1);
            fail();
        } catch (GetOutOfBoundsException e) {
            assertEquals(e.getMessage(),"Get out of bounds!");
        } catch (Exception e){
            fail();
        }

    }

}