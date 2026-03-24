package asciimon.move;

import asciimon.type.Type;

public class Attack extends Move{

    public Attack(String moveName, Type moveType, Integer statModifier) {
        super(moveName, moveType, "health", statModifier, 0, true);
    }

}
