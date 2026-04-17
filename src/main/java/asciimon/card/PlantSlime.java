package asciimon.card;

import java.util.List;

import asciimon.move.MoveInstances;
import asciimon.type.TypeInstances;

public class PlantSlime extends Card {
    
    public PlantSlime(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp) {
    
        super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, TypeInstances.PLANT_INSTANCE.getInstance());

        this.learnMove(MoveInstances.getMoveInstance("razorleaf"));
        this.learnMove(MoveInstances.getMoveInstance("restoration"));
    }

}
