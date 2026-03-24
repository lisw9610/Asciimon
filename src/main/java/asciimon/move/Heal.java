package asciimon.move;

import asciimon.type.Type;

public class Heal extends Move{

    public Heal(String moveName, Type moveType, Integer statModifier) {
        super(moveName, moveType, "health", statModifier, 0, false);
    }

}
