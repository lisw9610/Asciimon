package asciimon.move;

import asciimon.type.Type;

public class Buff extends Move{
    public Buff(String moveName, Type moveType, String modifiedStat, Integer statModifier, Integer modifierDuration) {
        super(moveName, moveType, modifiedStat, statModifier, modifierDuration, false);
    }

}
