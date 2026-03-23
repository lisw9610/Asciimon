package src.main.java.asciimon.move;

import java.util.Arrays;
import java.util.List;

import src.main.java.asciimon.type.TypeInstances;

public class MoveInstances {
    private static List<Move> possibleMoves = Arrays.asList(
        new Attack("fireball", TypeInstances.FIRE_INSTANCE.getInstance(), 10),
        new Attack("watergun", TypeInstances.WATER_INSTANCE.getInstance(), 10),
        new Attack("discharge", TypeInstances.ELECTRIC_INSTANCE.getInstance(), 10),
        new Attack("razorleaf", TypeInstances.PLANT_INSTANCE.getInstance(), 10),
        new Support("restoration", TypeInstances.PLANT_INSTANCE.getInstance(), "health", 10),
        new Debuff("paralyze", TypeInstances.ELECTRIC_INSTANCE.getInstance(), "speed", 10),
        new Support("overheat", TypeInstances.FIRE_INSTANCE.getInstance(), "attack", 10)
    );
    
    public static Move getMoveInstance(String moveName) {
        switch(moveName.toLowerCase()) {
            case "fireball":
                return possibleMoves.get(0);

            case "watergun":
                return possibleMoves.get(1);

            case "discharge":
                return possibleMoves.get(2);

            case "razorleaf":
                return possibleMoves.get(3);
            
            case "restoration":
                return possibleMoves.get(4);

            case "paralyze":
                return possibleMoves.get(5);

            case "overheat":
                return possibleMoves.get(6);


            default:
                return null;
        }
    }
}
