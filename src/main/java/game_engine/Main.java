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

        GameManager gm = new GameManager(players, eog);
        BoardDisplay bd = new BoardDisplay(board);
        MinimalGameMenu menu = new MinimalGameMenu(gm,bd);

        menu.setUpNamesAndSymbols();

        gm.getEog().setGameState(GameState.GAME_RUNNING);

        while (!gm.checkEnd()){
            Scanner inputReader = new Scanner(System.in);
            System.out.println("Enter a number between 1 and 9, " + gm.getCurrentPlayer().getName().orElse("Unnamed One"));
            int nextMove = inputReader.nextInt();
            menu.readMoveAndDisplayAll(nextMove);
        }


    }
}
