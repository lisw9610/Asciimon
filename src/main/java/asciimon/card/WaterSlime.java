package asciimon.card;

import java.util.List;

import asciimon.move.MoveInstances;
import asciimon.type.TypeInstances;

public class WaterSlime extends Card {
    
    public WaterSlime(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp) {
        super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, TypeInstances.WATER_INSTANCE.getInstance());
    
        this.learnMove(MoveInstances.getMoveInstance("watergun"));
    }

}
