package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameMenu {

    private GameManager gm;

    private Random random;

    private int selector;

    private List<String> messages = new ArrayList<>();

    private final String startMessage = "New game ('1') ******** Quit ('2')";
    //new Player, new Computer
    private final String playerModeMessage = "Human vs Computer ('1') or Human vs Human ('2')?";

    private final String nameMessage = "What's your name, player?";
    private final String symbolMessage = "Choose your symbol: Cross ('1') or Nought ('2').";
    private final String quitMessage = "Goodbye";

    private String nameOfPlayerOne;
    private String nameOfPlayerTwo;

    private void populateMessageList(){
        messages.add(startMessage);
        messages.add(playerModeMessage);
        messages.add(nameMessage);
        messages.add(symbolMessage);
        messages.add("");
        messages.add(quitMessage);
    }

    public GameMenu(GameManager gm) {
        this.gm = gm;
        this.selector = 0;
        populateMessageList();
    }

    public String getMessage(int selector) {
        return messages.get(selector);
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

    public void displayOptions(int choice) {
        if (isQuit(selector,choice)){
            System.out.println(getMessage(5));
            System.out.println();
        } else {
            System.out.println(getMessage(selector));
            System.out.println();
//            selector++;
        }
    }

    public void processNumberInput(int choice){
        switch(selector){
            case 0:
                displayOptions(choice);
                break;
            case 1:
                displayOptions(choice);
                break;
            case 2:
                displayOptions(choice);
                break;
            case 3:
                displayOptions(choice);
                break;
            case 4:
                displayOptions(choice);
                break;
            case 5:
                displayOptions(choice);
                break;
            default:
                break;
        }
        displayOptions(choice);
    }

    public void processNameInput(String name){

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
