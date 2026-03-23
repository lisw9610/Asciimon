package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public class Buff extends Move{
    public Buff(String moveName, Type moveType, String modifiedStat, Integer statModifier, Integer modifierDuration) {
        super(moveName, moveType, modifiedStat, statModifier, modifierDuration, false);
    }

}
