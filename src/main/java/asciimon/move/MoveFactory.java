package asciimon.move;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import asciimon.card.StatType;
import asciimon.type.ElectricType;
import asciimon.type.FireType;
import asciimon.type.PlantType;
import asciimon.type.WaterType;

public class MoveFactory {
    private static final Map<String, Move> moveFactory = new HashMap<>();

    static {
        moveFactory.put("fireball", new Attack("fireball", FireType.getInstance(), 10));
        moveFactory.put("watergun", new Attack("watergun", WaterType.getInstance(), 10));
        moveFactory.put("discharge", new Attack("discharge", ElectricType.getInstance(), 10));
        moveFactory.put("razorleaf", new Attack("razorleaf", PlantType.getInstance(), 10));
        moveFactory.put("restoration", new Heal("restoration", PlantType.getInstance(), 10));
        moveFactory.put("paralyze", new Debuff("paralyze", ElectricType.getInstance(), StatType.SPEED, 2, 2));
        moveFactory.put("overheat", new Buff("overheat", FireType.getInstance(), StatType.ATTACK, 2, 2));
        moveFactory.put("poison needle", new DamageOverTime("poison needle", PlantType.getInstance(), 10, 2));
        moveFactory.put("flamethrower", new DamageOverTime("flamethrower", FireType.getInstance(), 10, 2));
    }

    public static Move getMoveInstance(String moveName) {
        return moveFactory.get(moveName.toLowerCase());
    }
}
