package game_engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIPlayer implements Player {

    private Symbol aiSymbol;
    private Board gameBoard;
    private String name;

    public AIPlayer(Symbol aiSymbol, Board gameBoard) {
        this.aiSymbol = aiSymbol;
        this.gameBoard = gameBoard;
        this.name = "Computer";
    }

    public Symbol getPlayerSymbol() {
        return aiSymbol;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getCellAddressAfterInput (int search){
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
