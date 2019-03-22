package game_engine;


public class MinimalGameMenu {

    private GameManager gm;
    private BoardDisplay bd;

    public MinimalGameMenu(GameManager gm, BoardDisplay bd) {
        this.gm = gm;
        this.bd = bd;
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
