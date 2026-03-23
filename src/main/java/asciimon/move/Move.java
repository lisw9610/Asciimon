package src.main.java.asciimon.move;

public abstract class Move {
    protected String moveName;
    protected String moveType;

    public Move(String moveName, String moveType) {
        this.moveName = moveName;
        this.moveType = moveType;
    }

    public String getMoveName() {
        return this.moveName;
    }

    public String getType() {
        return this.moveType;
    }

}
