package asciimon.card;

import java.util.List;

import asciimon.move.MoveFactory;
import asciimon.type.PlantType;

public class PlantSlime extends Card {
    
    public PlantSlime(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp) {
    
        super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, PlantType.getInstance());

        this.learnMove(MoveFactory.getMoveInstance("razorleaf"));
        this.learnMove(MoveFactory.getMoveInstance("restoration"));
    }

}
