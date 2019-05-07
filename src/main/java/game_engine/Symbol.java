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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj || this.getClass() != obj.getClass()){
            return false;
        }
        return true;
    }
}
