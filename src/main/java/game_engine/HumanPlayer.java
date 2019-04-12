package game_engine;

import java.util.*;

public class HumanPlayer implements Player {

    private Symbol playerSymbol;
    private Board gameBoard;
    private String name;

    public HumanPlayer(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Optional<Symbol> getPlayerSymbol() {
        return Optional.ofNullable(playerSymbol);
    }

    public void setPlayerSymbol(Symbol playerSymbol) {
        if (null == this.playerSymbol)
            this.playerSymbol = playerSymbol;
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

    public void setName(String name){
        this.name = name;
    }

    public List<Integer> getCellAddressAfterInput (int search, Board board){
        List<Integer> coordinates = new ArrayList<Integer>();
        setGameBoard(board);

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
