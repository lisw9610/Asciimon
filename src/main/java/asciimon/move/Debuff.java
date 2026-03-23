package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public class Debuff extends Move{

    public Debuff(String moveName, Type moveType, String modifiedStat, Integer statModifier, Integer modifierDuration) {
        super(moveName, moveType, modifiedStat, statModifier, modifierDuration, true);
    }
}


