package asciimon.card;

import java.util.Arrays;
import java.util.List;

import asciimon.move.MoveInstances;
import asciimon.type.TypeInstances;

public class WaterSlime extends Card {
    
    public WaterSlime(String name) {
        List<Integer> nextLevelExperienceCounts = Arrays.asList(100, 200, 300, 400, 500);
        List<Integer> statIncreaseOnLevelUp = Arrays.asList(1, 1, 1, 1);

        super(name, 100, 10, 10, 10, nextLevelExperienceCounts, statIncreaseOnLevelUp, TypeInstances.WATER_INSTANCE.getInstance());
    
        this.learnMove(MoveInstances.getMoveInstance("watergun"));
    }

}
