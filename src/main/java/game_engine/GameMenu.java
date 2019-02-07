package game_engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

public class GameMenu {

    private GameManager gm;

    private Random random;

    private GameState selector;

    private List<String> messages = new ArrayList<>();

    private final String startMessage = "New game ('1') ******** Quit ('2')";
    //new Player, new Computer
    private final String playerModeMessage = "Human vs Computer ('1') or Human vs Human ('2')?";

    private final String nameMessage = "What's your name, player?";
    private final String symbolMessage = "Choose your symbol: Cross ('1') or Nought ('2').";
    private String currentPlayersMessage = "";
    private String whoWonMessage = " won. ";
    private String wasDrawMessage = "Cat's game. ";
    private final String quitMessage = "Goodbye";

    private String nameOfPlayerOne;
    private String nameOfPlayerTwo;

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

    public String getNameOfPlayerOne() {
        return nameOfPlayerOne;
    }

    public void setNameOfPlayerOne(String nameOfPlayerOne) {
        this.nameOfPlayerOne = nameOfPlayerOne;
    }

    public String getNameOfPlayerTwo() {
        return nameOfPlayerTwo;
    }

    public void setNameOfPlayerTwo(String nameOfPlayerTwo) {
        this.nameOfPlayerTwo = nameOfPlayerTwo;
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

    public void processChoice (int intInput, Consumer<GameState> case1, Consumer<GameState> case2){
        switch(intInput){
            case 1:
                selector = GameState.GAME_SETUP;
                break;
            case 2:
                selector = GameState.GAME_QUIT;
                break;
            default:
                break;
        }
    }

    public void processChoice (String stringInput){

    }

    public void evaluateGameProgress (int messageIndex){
        int result = this.readIntChoice();

        switch(selector){
            case GAME_START:
                switch(result){
                    case 1:
                        selector = GameState.GAME_SETUP;
                        break;
                    case 2:
                        selector = GameState.GAME_QUIT;
                        break;
                    default:
                        break;
                }
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

    public void processGameState(){
        switch(selector){
            case GAME_START:
                displayOptions(0);
                evaluateGameProgress(0);
                break;
            case GAME_SETUP:
                for (int i = 1; i <= 3; i++){
                    displayOptions(i);
                    evaluateGameProgress(i);
                }
                break;
            case GAME_RUNNING:
                displayOptions(4);
                evaluateGameProgress(4);
                break;
            case GAME_WON:
                displayOptions(5);
                evaluateGameProgress(5);
                break;
            case GAME_DRAW:
                displayOptions(6);
                evaluateGameProgress(6);
                break;
            case GAME_QUIT:
                displayOptions(7);
                evaluateGameProgress(7);
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
