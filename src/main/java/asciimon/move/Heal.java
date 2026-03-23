package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public class Heal extends Move{
    private Integer healAmount;

    public Heal(String moveName, Type moveType, Integer healAmount) {
        super(moveName, moveType);
        this.healAmount = healAmount;
    }

}
