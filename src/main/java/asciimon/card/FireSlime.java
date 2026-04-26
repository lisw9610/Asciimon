package asciimon.card;

import java.util.List;

import asciimon.move.MoveFactory;
import asciimon.type.FireType;

public class FireSlime extends Card {
    
    public FireSlime(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp) {
        super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, FireType.getInstance());

        this.learnMove(MoveFactory.getMoveInstance("fireball"));
        this.learnMove(MoveFactory.getMoveInstance("overheat"));
        this.learnMove(MoveFactory.getMoveInstance("flamethrower"));
    }

}