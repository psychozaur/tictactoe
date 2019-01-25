package game_engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class EndOfGameTest {

    @Test
    public void testIsEmptyBoardNotWon()
    {
        EndOfGame eog = new EndOfGame(new Board(3));
        boolean result = true;

        if ("Empty" == eog.checkEnd().toString()) result = false;

        assertFalse(result);
    }

    @Test
    public void testIsRowsGivingSymbols(){

        Board board = new Board(3);
        Symbol symbol = new Cross();
        board.insert(symbol,1,0);
        board.insert(symbol,1,1);
        board.insert(symbol,1,2);

        EndOfGame eog = new EndOfGame(board);

        assertSame(symbol,eog.checkEnd());

    }

    @Test
    public void testIsColumnsGivingSymbols(){

        Board board = new Board(3);
        Symbol symbol = new Cross();
        board.insert(symbol,0,2);
        board.insert(symbol,1,2);
        board.insert(symbol,2,2);

        EndOfGame eog = new EndOfGame(board);

        assertSame(symbol,eog.checkEnd());

    }

    @Test
    public void testIsSlashGivingSymbols(){

        Board board = new Board(3);
        Symbol symbol = new Cross();
        board.insert(symbol,2,0);
        board.insert(symbol,1,1);
        board.insert(symbol,0,2);

        EndOfGame eog = new EndOfGame(board);

        assertSame(symbol,eog.checkEnd());

    }

    @Test
    public void testIsBackslashGivingSymbols(){

        Board board = new Board(3);
        Symbol symbol = new Cross();
        board.insert(symbol,0,0);
        board.insert(symbol,1,1);
        board.insert(symbol,2,2);

        EndOfGame eog = new EndOfGame(board);

        assertSame(symbol,eog.checkEnd());

    }
}
