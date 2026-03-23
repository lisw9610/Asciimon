package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public class Attack extends Move{

    public Attack(String moveName, Type moveType, Integer statModifier) {
        super(moveName, moveType, "health", statModifier, 0, true);
    }

}
