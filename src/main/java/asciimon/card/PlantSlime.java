package src.main.java.asciimon.card;

import java.util.Arrays;
import java.util.List;

import src.main.java.asciimon.move.MoveInstances;
import src.main.java.asciimon.type.TypeInstances;

public class PlantSlime extends Card {
    
    public PlantSlime(String name) {
        List<Integer> nextLevelExperienceCounts = Arrays.asList(100, 200, 300, 400, 500);

        super(name, 10, 10, 10, 10, nextLevelExperienceCounts, TypeInstances.PLANT_INSTANCE.getInstance());

        this.learnMove(MoveInstances.getMoveInstance("razorleaf"));
        this.learnMove(MoveInstances.getMoveInstance("restoration"));
    }

}
