package game_engine;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Symbol>> state;

    public Board(int n) {
        this.state = new ArrayList<List<Symbol>>();
        for( int i = 0; i < n; i++){
            state.add(new ArrayList<Symbol>());
            for (int j = 0; j < n; j++){
                state.get(i).add(new Symbol());
            }
        }
    }

    public int getSize() {
        return state.size();
    }

    public List<Symbol> getRow(int xPos){
        return state.get(xPos);
    }

    public List<Symbol> getColumn(int yPos){
        List<Symbol> result = new ArrayList<Symbol>();

        for (int i = 0; i < state.size(); i++)
        result.add(state.get(i).get(yPos));

        return result;
    }

    public List<Symbol> getSlash(){
        List<Symbol> result = new ArrayList<Symbol>();

        result.add(state.get(2).get(0));
        result.add(state.get(1).get(1));
        result.add(state.get(0).get(2));

        return result;
    }

    public List<Symbol> getBackslash(){
        List<Symbol> result = new ArrayList<Symbol>();

        result.add(state.get(0).get(0));
        result.add(state.get(1).get(1));
        result.add(state.get(2).get(2));


        return result;
    }

    public boolean isCellEmpty(int i, int j) {
        if ("Empty" == state.get(i).get(j).toString())  return true;
            return false;
    }

    public void insert(Symbol symbol, int xPos, int yPos){

        if(xPos < 0 || xPos > state.size() || yPos < 0 || yPos > state.size()){
            throw new MoveOutOfBoundsException("Move out of bounds!");
        }

        if ("Empty" == this.get(xPos,yPos).toString()) {
            state.set(xPos, state.get(xPos)).set(yPos, symbol);
        }

    }

    public Symbol get(int xPos, int yPos){
        if(xPos < 0 || xPos > state.size() || yPos < 0 || yPos > state.size()){
            throw new GetOutOfBoundsException("Get out of bounds!");
        }

        return state.get(xPos).get(yPos);
    }
}
