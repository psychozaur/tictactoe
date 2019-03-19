package game_engine;

public class Symbol {

    private String mark = "empty";

    public String getMark(){
        return mark;
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
