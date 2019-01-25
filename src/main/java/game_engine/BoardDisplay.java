package game_engine;

public class BoardDisplay {

    private Board board;

    public BoardDisplay(Board board) {
        this.board = board;
    }

    public void display(){

        String symbolToDisplay;

        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                if(board.get(i,j).toString() == "Cross"){
                    symbolToDisplay = board.get(i,j).getMark() + "  ";
                } else if(board.get(i,j).toString() == "Nought"){
                    symbolToDisplay = "O  ";
                } else {
                    symbolToDisplay = ((3 * i) + (j + 1)) + "  ";
                }
                System.out.print(symbolToDisplay);
            }
            System.out.println();
            System.out.println();
        }

    }
}
