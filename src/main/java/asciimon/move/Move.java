package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public abstract class Move {
    protected String moveName;
    protected Type moveType;

    public Move(String moveName, Type moveType) {
        this.moveName = moveName;
        this.moveType = moveType;
    }

    public String getMoveName() {
        return this.moveName;
    }

    public Type getType() {
        return this.moveType;
    }

}
