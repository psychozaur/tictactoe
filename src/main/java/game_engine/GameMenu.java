package game_engine;

import java.util.Scanner;

public class GameMenu {

    private GameManager gm;

    public GameMenu(GameManager gm) {
        this.gm = gm;
    }

    public void displayGameTitle() {
        System.out.println("===========");
        System.out.println("TIC TAC TOE");
        System.out.println("===========");
    }

    public void display(){
//        gm.getPlayers().forEach(x -> System.out.println(x.getName() + " - " + x.getPlayerSymbol().toString()));

        System.out.print("New game ('n') ******** Quit ('q')");

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
