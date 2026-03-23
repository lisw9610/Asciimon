package src.main.java.asciimon.card;

import java.util.Arrays;
import java.util.List;

import src.main.java.asciimon.move.SingletonMoves;
import src.main.java.asciimon.type.TypeInstances;

public class WaterSlime extends Card {
    
    public WaterSlime(String name) {
        List<Integer> nextLevelExperienceCounts = Arrays.asList(100, 200, 300, 400, 500);

        super(name, 10, 10, 10, 10, nextLevelExperienceCounts, TypeInstances.WATER_INSTANCE.getInstance());
    
        this.learnMove(SingletonMoves.getMoveInstance("watergun"));
    }

}
