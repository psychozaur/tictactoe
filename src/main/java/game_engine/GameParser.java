package game_engine;

import java.util.*;
import java.util.logging.Logger;

public class GameParser {

    private static Logger logger = Logger.getLogger(GameParser.class.getName());

    private Board board;
    private GameState gameState;

    public GameParser(Board board) {
        this.board = board;
        this.gameState = GameState.GAME_START;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private Symbol checkLine (List<Symbol> line, Set maybeWinner){

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

    public Symbol checkWin(){

        Set maybeWinner = new HashSet();
        Symbol result;

        //win
        for (int i = 0; i < board.getSize(); i++){
            result = checkLine(board.getRow(i),maybeWinner);
            if (result.toString() != "Empty") {
//                logger.info("Game won");
                setGameState(GameState.GAME_WON);
                return result;
            }
            result = checkLine(board.getColumn(i),maybeWinner);
            if (result.toString() != "Empty") {
//                logger.info("Game won");
                setGameState(GameState.GAME_WON);
                return result;
            }
        }

        result = checkLine(board.getSlash(),maybeWinner);
        if (result.toString() != "Empty") {
//            logger.info("Game won");
            setGameState(GameState.GAME_WON);
            return result;
        }
        result = checkLine(board.getBackslash(),maybeWinner);
        if (result.toString() != "Empty") {
//            logger.info("Game won");
            setGameState(GameState.GAME_WON);
            return result;
        }

        return result;
    }

    public void checkDraw(){
        //draw

        if(board.isBoardFull() && checkWin().toString() == "Empty"){
//            logger.info("Game draw");
            setGameState(GameState.GAME_DRAW);
        }
    }




}
