package game_engine;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board = new Board(3);
        EndOfGame eog = new EndOfGame(board);
        List<Player> players = Arrays.asList(new HumanPlayer(board),
                new HumanPlayer(board));
        players.get(0).setName("Krzychu");
        players.get(1).setName("Zbychu");
        players.get(0).setPlayerSymbol(new Cross());
        players.get(1).setPlayerSymbol(new Nought());
        GameManager gm = new GameManager(players, eog);
        BoardDisplay bd = new BoardDisplay(board);
        MinimalGameMenu menu = new MinimalGameMenu(gm,bd);
//        List<Integer> moves = Arrays.asList(5,4,1,9,2,3,8,6,7);

//        while (!(gm.getEog().getGameState().equals(GameState.GAME_WON) ||
//                gm.getEog().getGameState().equals(GameState.GAME_DRAW))){
        while (true){
            Scanner inputReader = new Scanner(System.in);
            int nextMove = inputReader.nextInt();
            menu.readMoveAndDisplayAll(nextMove);
        }

//        for (Integer move : moves){
//            menu.readMoveAndDisplayAll(move);
//        }

    }
}
