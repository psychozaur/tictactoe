package game_engine;

public class Cross extends Symbol {

    private String mark;

    public Cross() {
        this.mark = "X";
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "Cross";
    }
}
