package asciimon.card;

import java.util.List;

import asciimon.move.MoveFactory;
import asciimon.type.ElectricType;

public class ElectricSlime extends Card {
    
    public ElectricSlime(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp) {
        super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, ElectricType.getInstance());

        this.learnMove(MoveFactory.getMoveInstance("discharge"));
        this.learnMove(MoveFactory.getMoveInstance("paralyza"));
    }

}
