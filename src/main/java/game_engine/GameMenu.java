package game_engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    public boolean isQuit(int selector, int choice) {
        if (selector == 0 && choice == 2 || selector == 5) {
            return true;
        }
        return false;
    }

    public void displayGameTitle() {
        System.out.println("===========");
        System.out.println("TIC TAC TOE");
        System.out.println("===========");
    }

    public void displayOptions() {
        System.out.println(messages.get(0));
    }

    public void processGameState(){
        switch(selector){
            case GAME_START:
                displayOptions();
                break;
            case GAME_SETUP:
                displayOptions();
                break;
            case GAME_RUNNING:
                displayOptions();
                break;
            case GAME_WON:
                displayOptions();
                break;
            case GAME_DRAW:
                displayOptions();
                break;
            case GAME_QUIT:
                displayOptions();
                break;
            default:
                break;
        }
        displayOptions();
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
