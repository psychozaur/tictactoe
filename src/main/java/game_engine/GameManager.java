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


    public void readMove(int input){

        List<Integer> coordinates = currentPlayer.getCellAddressAfterInput(input);
        currentPlayer.getGameBoard().insert(currentPlayer.getPlayerSymbol(),
                                            coordinates.get(0),
                                            coordinates.get(1));

        if (eog.checkEnd().toString() != "Empty" || eog.isSolved()){
            throw new GameHasEndedException("Game has ended already!");
        }

        currentPlayer = currentPlayer == players.get(0) ? players.get(1) : players.get(0);
    }
}
