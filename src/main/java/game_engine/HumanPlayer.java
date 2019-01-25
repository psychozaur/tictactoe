package game_engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player {

    private Symbol playerSymbol;
    private Board gameBoard;
    private String name;

    public HumanPlayer(Symbol playerSymbol, Board gameBoard, String name) {
        this.playerSymbol = playerSymbol;
        this.gameBoard = gameBoard;
        this.name = name;
    }

    public Symbol getPlayerSymbol() {
        return playerSymbol;
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
