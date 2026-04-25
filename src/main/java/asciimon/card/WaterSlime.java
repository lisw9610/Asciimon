package asciimon.card;

import java.util.List;

import asciimon.move.MoveFactory;
import asciimon.type.WaterType;

public class WaterSlime extends Card {
    
    public WaterSlime(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp) {
        super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, WaterType.getInstance());
    
        this.learnMove(MoveFactory.getMoveInstance("watergun"));
    }

}
