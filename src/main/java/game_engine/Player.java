package game_engine;

import java.util.List;

public interface Player {

    public Symbol getPlayerSymbol();
    public Board getGameBoard();
    public String getName();
    public List<Integer> getCellAddressAfterInput(int search);

}
