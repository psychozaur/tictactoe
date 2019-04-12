package game_engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AIPlayer implements Player {

    private Symbol aiSymbol;
    private Board gameBoard;
    private String name;

    public AIPlayer(Board gameBoard) {
        this.gameBoard = gameBoard;
        this.name = "Computer";
    }

    public Optional<Symbol> getPlayerSymbol() {
        return Optional.ofNullable(aiSymbol);
    }

    public void setPlayerSymbol(Symbol aiSymbol) {
        if (null == this.aiSymbol)
            this.aiSymbol = aiSymbol;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    private void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name){}


    public List<Integer> getCellAddressAfterInput (int search, Board board){
        List<Integer> coordinates = new ArrayList<Integer>();

        if (search <= 0 || search > (gameBoard.getSize() * gameBoard.getSize())){
            throw new IllegalInputException("Input must be between 1 and 9");
        }

        for (int x = 0; x < gameBoard.getSize(); x++){
            for (int y = 0; y < gameBoard.getSize(); y++){
                if (search == (gameBoard.getSize() * x) + y + 1){
                    if (gameBoard.get(x,y).toString() != "Empty"){
                        throw new IllegalInputException("This field is already occupied!");
                    }
                    coordinates.add(x);
                    coordinates.add(y);
                    return coordinates;
                }
            }
        }

        return Collections.emptyList();
    }
}
