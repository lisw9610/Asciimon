package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public class Heal extends Move{

    public Heal(String moveName, Type moveType, Integer statModifier) {
        super(moveName, moveType, "health", statModifier, 0, false);
    }

}
