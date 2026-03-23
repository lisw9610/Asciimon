package src.main.java.asciimon.card;

import java.util.Arrays;
import java.util.List;

import src.main.java.asciimon.move.MoveInstances;
import src.main.java.asciimon.type.TypeInstances;

public class WaterSlime extends Card {
    
    public WaterSlime(String name) {
        List<Integer> nextLevelExperienceCounts = Arrays.asList(100, 200, 300, 400, 500);
        List<Integer> statIncreaseOnLevelUp = Arrays.asList(1, 1, 1, 1);

        super(name, 10, 10, 10, 10, nextLevelExperienceCounts, statIncreaseOnLevelUp, TypeInstances.WATER_INSTANCE.getInstance());
    
        this.learnMove(MoveInstances.getMoveInstance("watergun"));
    }

}
