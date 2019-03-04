package game_engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class GameMenu {

    private GameManager gm;
    private BoardDisplay bd;

    private Random random;

    private GameState selector;

    private List<String> messages = new ArrayList<>();

    //0
    // = setup || = quit
    private final String startMessage = "New game ('1') ******** Quit ('2')";

    //1
    //new HumanPlayer; new AIPlayer; || new HumanPlayer; new HumanPlayer
    private final String playerModeMessage = "Human vs Computer ('1') or Human vs Human ('2')?";

    //2
    //setNameOfPlayerOne(...); setNameOfPlayerTwo(...);
    private final String nameMessage = "What's your name, player?";

    //3
    //setMarkOfPlayerOne(...); setMarkOfPlayerTwo(...);
    // = running;
    private final String symbolMessage = "Choose your symbol: Cross ('1') or Nought ('2').";

    //4
    // message = player1name + " (" + mark + ") " + player2name + " (" + mark + ") ";
    // print(message)
    // = won || = draw
    private String currentPlayersMessage = "";

    //5
    // message = playername + whoWonMessage
    // print(message)
    // = quit
    private String whoWonMessage = " won. ";

    //6
    //print(message)
    // = quit
    private String wasDrawMessage = "Cat's game. ";

    //7
    //print(message)
    private final String quitMessage = "Goodbye";

    private Player playerOne;
    private Player playerTwo;
    private List<Player> players;

    private Player currentPlayer;

    private void populateMessageList(){
        messages.add(startMessage);
        messages.add(playerModeMessage);
        messages.add(nameMessage);
        messages.add(symbolMessage);
        messages.add(currentPlayersMessage);
        messages.add(whoWonMessage);
        messages.add(wasDrawMessage);
        messages.add(quitMessage);
    }

    public GameMenu(GameManager gm, BoardDisplay bd) {
        this.gm = gm;
        this.bd = bd;
        this.selector = GameState.GAME_START;
        populateMessageList();
    }

    public BoardDisplay getBd() {
        return bd;
    }

    public GameState getSelector() {
        return selector;
    }

    public String getMessage(int index) {
        return messages.get(index);
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getNameOfPlayerOne() {
        return playerOne.getName().orElse("Name not found");
    }

    public void setNameOfPlayerOne(String nameOfPlayerOne) {
        playerOne.setName(nameOfPlayerOne);
    }

    public String getNameOfPlayerTwo() {
        return playerTwo.getName().orElse("Name not found");
    }

    public void setNameOfPlayerTwo(String nameOfPlayerTwo) {
        playerTwo.setName(nameOfPlayerTwo);
    }

    public boolean isQuit() {
        if (selector == (GameState.GAME_QUIT)) {
            return true;
        }
        return false;
    }

    public void displayGameTitle() {
        System.out.println("===========");
        System.out.println("TIC TAC TOE");
        System.out.println("===========");
    }

    // always 1
    public int readIntChoice (){
//        Scanner reader = new Scanner(System.in);
//        return reader.nextInt();
        return 1;
    }

    public String readStringChoice (){
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }

    public <R> void processChoice (Function<Player,R> case1, Function<Player,R> case2){
        R intermediary;
        switch(readIntChoice()){
            case 1:
                intermediary = case1.apply(currentPlayer);
                selector = (GameState) intermediary;
                break;
            case 2:
                intermediary = case2.apply(currentPlayer);
                selector = (GameState) intermediary;
                break;
            default:
                break;
        }
    }

    public <R> void processChoice2 (Function<Player,R> case1, Function<Player,R> case2){
        R intermediary;
        switch(readIntChoice()){
            case 1:
                intermediary = case1.apply(currentPlayer);
                players = (List<Player>) intermediary;
                setPlayerOne(players.get(0));
                setPlayerTwo(players.get(1));
                break;
            case 2:
                intermediary = case2.apply(currentPlayer);
                players = (List<Player>) intermediary;
                setPlayerOne(players.get(0));
                setPlayerTwo(players.get(1));
                break;
            default:
                break;
        }
    }

//readStringChoice nie wystarczy?
    public void processChoice (String stringInput){

    }

    public <R> void evaluateGameProgress (int messageIndex, Function<Player,R> case1, Function<Player,R> case2){
//        int result = this.readIntChoice();

        switch(selector){
            case GAME_START:
                processChoice(case1,case2);
                break;
            case GAME_SETUP:
                processChoice2(case1,case2);
                break;
            case GAME_RUNNING:
                displayOptions(4);
                break;
            case GAME_WON:
                displayOptions(5);
                break;
            case GAME_DRAW:
                displayOptions(6);
                break;
            case GAME_QUIT:
                displayOptions(7);
                break;
            default:
                break;
        }

    }

    public void displayOptions(int index) {
        System.out.println(messages.get(index));
    }

    public <R> void processGameState(Function<Player,R> case1, Function<Player,R> case2){
        switch(selector){
            case GAME_START:
                displayOptions(0);
                evaluateGameProgress(0, case1, case2);
                break;
            case GAME_SETUP:
                for (int i = 1; i <= 3; i++){
                    displayOptions(i);
                    evaluateGameProgress(i, case1, case2);
                }
                break;
            case GAME_RUNNING:
                displayOptions(4);
                evaluateGameProgress(4, case1, case2);
                break;
            case GAME_WON:
                displayOptions(5);
                evaluateGameProgress(5, case1, case2);
                break;
            case GAME_DRAW:
                displayOptions(6);
                evaluateGameProgress(6, case1, case2);
                break;
            case GAME_QUIT:
                displayOptions(7);
                evaluateGameProgress(7, case1, case2);
                break;
            default:
                break;
        }
    }

    public void flush(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void display(){

        random = new Random();
        double result = random.nextDouble();
        System.out.println(result);

//        Scanner reader = new Scanner(System.in);
//        int playerCount = reader.nextInt();
//        switch(playerCount){
//            case 1:
//                System.out.println("1P");
//                break;
//            case 2:
//                System.out.println("2P");
//                break;
//                default:
//                    System.out.println("Wrong player mode. Type 1 or 2");
//                    break;
//        }
    }
}
