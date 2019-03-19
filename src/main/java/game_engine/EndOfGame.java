package game_engine;

import java.util.*;

public class EndOfGame {

    private boolean isSolved;
    private Board board;
    private GameState gameState;

    public EndOfGame(Board board) {
        this.isSolved = false;
        this.board = board;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Symbol checkLine (List<Symbol> line, Set maybeWinner){

        Symbol result = new Symbol();

        for (Symbol s : line){

            if (s.toString() == "Empty"){
                result = new Symbol();
                maybeWinner.clear();
                return result;
            } else {
                if (maybeWinner.isEmpty()){
                    result = s;
                    maybeWinner.add(s);
                }
                if (!maybeWinner.contains(s)){
                    result = new Symbol();
                    maybeWinner.clear();
                    return result;
                }
            }
        }

        maybeWinner.clear();

        return result;
    }

    public Symbol checkEnd(){

        Set maybeWinner = new HashSet();
        Symbol result;

        //win
        for (int i = 0; i < board.getSize(); i++){
            result = checkLine(board.getRow(i),maybeWinner);
            if (result.toString() != "Empty") {
                setGameState(GameState.GAME_WON);
                return result;
            }
            result = checkLine(board.getColumn(i),maybeWinner);
            if (result.toString() != "Empty") {
                setGameState(GameState.GAME_WON);
                return result;
            }
        }

        result = checkLine(board.getSlash(),maybeWinner);
        if (result.toString() != "Empty") {
            setGameState(GameState.GAME_WON);
            return result;
        }
        result = checkLine(board.getBackslash(),maybeWinner);
        if (result.toString() != "Empty") {
            setGameState(GameState.GAME_WON);
            return result;
        }

        //draw
        for (int i = 0; i < (board.getSize() * board.getSize()); i++){
            if (board.isCellEmpty(i % 3,i / 3)){
                isSolved = false;
                return result;
            } else {
                setGameState(GameState.GAME_DRAW);
                isSolved = true;
            }
        }

        return result;
    }
}
