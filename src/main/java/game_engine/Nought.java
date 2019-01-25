package game_engine;

public class Nought extends Symbol {

    private String mark;

    public Nought() {
        this.mark = "O";
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "Nought";
    }
}
