package src.main.java.asciimon.move;

import src.main.java.asciimon.type.TypeInstances;

public class MoveFactory {
    public static Move createMove(String moveName) {
        switch(moveName.toLowerCase()) {
            case "fireball":
                return new Attack("fireball", TypeInstances.FIRE_INSTANCE.getInstance(), 10);
            


            default:
                return null;
        }
    }
}
