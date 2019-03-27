package game_engine;


import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class MinimalGameMenu {

    private GameManager gm;
    private BoardDisplay bd;

    public MinimalGameMenu(GameManager gm, BoardDisplay bd) {
        this.gm = gm;
        this.bd = bd;
    }

    private void assignSymbolsToPlayers(String input){
        switch(input){
            case "x":
            case "X":
                gm.getPlayers().get(0).setPlayerSymbol(new Cross());
                gm.getPlayers().get(1).setPlayerSymbol(new Nought());
                break;
            case "o":
            case "O":
                gm.getPlayers().get(0).setPlayerSymbol(new Nought());
                gm.getPlayers().get(1).setPlayerSymbol(new Cross());
                break;
            default:
                System.out.println("Wrong input. Setup is going to restart");
                break;
        }
    }

    public void setUpNamesAndSymbols() {
        gm.getEog().setGameState(GameState.GAME_SETUP);
        while (gm.getPlayers()
                .stream()
                .filter(player -> player.getName().equals(Optional.empty())
                        || player.getPlayerSymbol().equals(Optional.empty()))
                .count() > 0){
            Scanner reader = new Scanner(System.in);
            System.out.println("What's your name, Player 1?");
            gm.getPlayers().get(0).setName(reader.next());
            System.out.println(gm.getPlayers().get(0).getName().orElse("") + ", choose your weapon: type 'X' for cross, 'O' for nought.");
            assignSymbolsToPlayers(reader.next());
            System.out.println("What's your name, Player 2?");
            gm.getPlayers().get(1).setName(reader.next());
        }


    }

    public void displayPlayerNamesAndSymbols() {
        System.out.println( gm.getPlayers().get(0).getName().orElse("Unnamed One") +
                " (" + gm.getPlayers().get(0).getPlayerSymbol().orElse(new Symbol()).getMark() + ") vs " +
                gm.getPlayers().get(1).getName().orElse("Unnamed One") +
                " (" + gm.getPlayers().get(1).getPlayerSymbol().orElse(new Symbol()).getMark() + ") ::::: Who moved last: " +
                gm.getCurrentPlayer().getName().orElse("Unnamed One") +
                " (" + gm.getCurrentPlayer().getPlayerSymbol().orElse(new Symbol()).getMark() + ")");
    }

    private void displayBoard() {
        this.bd.display();
    }

    public void readMoveAndDisplayAll(int input) {
        this.displayPlayerNamesAndSymbols();

        try {
            gm.readMove(input);
        } catch (GameHasEndedException e) {
            if (gm.getEog().getGameState().equals(GameState.GAME_WON)) System.out.println(gm.getCurrentPlayer().getName().orElse("") + " won.");
            else System.out.println("Cat's game");
        } finally {
            this.displayBoard();
        }
    }
}
