package asciimon.card;

import java.util.List;

import asciimon.move.MoveInstances;
import asciimon.type.TypeInstances;

public class ElectricSlime extends Card {
    
    public ElectricSlime(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp) {
        super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, TypeInstances.ELECTRIC_INSTANCE.getInstance());

        this.learnMove(MoveInstances.getMoveInstance("discharge"));
        this.learnMove(MoveInstances.getMoveInstance("paralyza"));
    }

}
