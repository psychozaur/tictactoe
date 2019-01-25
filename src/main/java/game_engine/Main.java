package game_engine;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(3);
        int xPos = -1, yPos = -1;

        try {
            board.insert(new Cross(),xPos,yPos);
            System.out.println(board.get(xPos,yPos));
        } catch (MoveOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

    }
}
