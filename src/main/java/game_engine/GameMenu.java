package game_engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameMenu {

    private GameManager gm;

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

    public GameMenu(GameManager gm) {
        this.gm = gm;
        this.selector = GameState.GAME_START;
        populateMessageList();
    }

    public String getMessage(int index) {
        return messages.get(index);
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

    public int readIntChoice (){
        Scanner reader = new Scanner(System.in);
        return reader.nextInt();
    }

    public String readStringChoice (){
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }

    public void processChoice (Supplier<GameState> case1, Supplier<GameState> case2){
        switch(readIntChoice()){
            case 1:
                selector = case1.get();
                break;
            case 2:
                selector = case2.get();
                break;
            default:
                break;
        }
    }

    public void processChoice (String stringInput){

    }

    public void evaluateGameProgress (int messageIndex, Supplier<GameState> case1, Supplier<GameState> case2){
//        int result = this.readIntChoice();

        switch(selector){
            case GAME_START:
                processChoice(case1,case2);
                break;
            case GAME_SETUP:
                for (int i = 1; i <= 3; i++){
                    displayOptions(i);

                }
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

    public void processGameState(Supplier<GameState> case1, Supplier<GameState> case2){
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
