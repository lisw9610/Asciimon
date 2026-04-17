package asciimon.card;

import java.util.List;

import asciimon.move.MoveInstances;
import asciimon.type.TypeInstances;

public class FireSlime extends Card {
    
    public FireSlime(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp) {
        super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, TypeInstances.FIRE_INSTANCE.getInstance());

        this.learnMove(MoveInstances.getMoveInstance("fireball"));
        this.learnMove(MoveInstances.getMoveInstance("overheat"));
    }

}