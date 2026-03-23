package src.main.java.asciimon.move;

import src.main.java.asciimon.type.TypeInstances;

public class MoveFactory {
    public static Move createMove(String moveName) {
        switch(moveName.toLowerCase()) {
            case "fireball":
                return new Attack("fireball", TypeInstances.FIRE_INSTANCE.getInstance(), 10);

            case "watergun":
                return new Attack("watergun", TypeInstances.WATER_INSTANCE.getInstance(), 10);

            case "discharge":
                return new Attack("discharge", TypeInstances.ELECTRIC_INSTANCE.getInstance(), 10);

            case "razorleaf":
                return new Attack("razorleaf", TypeInstances.PLANT_INSTANCE.getInstance(), 10);
            
            case "restoration":
                return new Heal("restoration", TypeInstances.PLANT_INSTANCE.getInstance(), 10);

            case "paralyze":
                return new Debuff("paralyze", TypeInstances.ELECTRIC_INSTANCE.getInstance(), "speed", 10);

            case "overheat":
                return new Buff("overheat", TypeInstances.FIRE_INSTANCE.getInstance(), "attack", 10);


            default:
                return null;
        }
    }
}
