package src.main.java.asciimon.card;

import java.util.Arrays;
import java.util.List;

import src.main.java.asciimon.move.SingletonMoves;
import src.main.java.asciimon.type.TypeInstances;

public class FireSlime extends Card {
    
    public FireSlime(String name) {
        List<Integer> nextLevelExperienceCounts = Arrays.asList(100, 200, 300, 400, 500);

        super(name, 10, 10, 10, 10, nextLevelExperienceCounts, TypeInstances.FIRE_INSTANCE.getInstance());

        this.learnMove(SingletonMoves.getMoveInstance("fireball"));
        this.learnMove(SingletonMoves.getMoveInstance("overheat"));
    }

}