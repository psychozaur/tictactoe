package game_engine;

import java.util.List;

public class GameManager {

    private Player currentPlayer;
    private List<Player> players;
    private EndOfGame eog;

    public GameManager(List<Player> players, EndOfGame eog) {
        this.players = players;
        this.eog = eog;
        this.currentPlayer = players.get(0);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

        public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public EndOfGame getEog() {
        return eog;
    }

    public boolean checkEnd(){
        if((eog.getGameState().equals(GameState.GAME_WON)
                || eog.getGameState().equals(GameState.GAME_DRAW))){
            return true;
        }
        return false;
    }

    public void readMove(int input){

        List<Integer> coordinates = currentPlayer.getCellAddressAfterInput(input);
        currentPlayer.getGameBoard().insert(currentPlayer.getPlayerSymbol().orElse(new Symbol()),
                                            coordinates.get(0),
                                            coordinates.get(1));

        eog.checkWin();
        eog.checkDraw();
//        if (eog.checkWin().toString() != "Empty" || checkEnd()){
        if (checkEnd()){
            throw new GameHasEndedException("Game has ended already!");
        }

        currentPlayer = currentPlayer == players.get(0) ? players.get(1) : players.get(0);
    }
}
