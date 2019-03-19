package game_engine;


public class MinimalGameMenu {

    private GameManager gm;

    public MinimalGameMenu(GameManager gm) {
        this.gm = gm;
    }

    public void displayNamesAndSymbols() {
        System.out.println( gm.getPlayers().get(0).getName().orElse("Unnamed") +
                " (" + gm.getPlayers().get(0).getPlayerSymbol().orElse(new Symbol()).getMark() + ") vs " +
                gm.getPlayers().get(1).getName().orElse("Unnamed") +
                " (" + gm.getPlayers().get(1).getPlayerSymbol().orElse(new Symbol()).getMark() + ")");
    }
}
