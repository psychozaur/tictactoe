package game_engine;

import java.util.List;
import java.util.Optional;

public interface Player {

    public Optional<Symbol> getPlayerSymbol();
    public void setPlayerSymbol(Symbol symbol);
    public Board getGameBoard();
    public Optional<String> getName();
    public void setName(String name);
    public List<Integer> getCellAddressAfterInput(int search, Board board);

}
