package asciimon.card;

import java.util.Arrays;
import java.util.List;

public class CardFactory {
    public static Card createCard(String name, String cardName) {
        Integer experienceModifier;
        String asciiArt;
        List<Integer> statIncreaseOnLevelUp;
        List<Integer> baseStats;

        switch(cardName.toLowerCase()) {
            case "fire_slime":
                experienceModifier = 1;
                statIncreaseOnLevelUp = Arrays.asList(10, 1, 1, 1);
                baseStats = Arrays.asList(100, 10, 10, 10);
                asciiArt = "";
                
                return new FireSlime(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp);

            case "water_slime":
                experienceModifier = 1;
                statIncreaseOnLevelUp = Arrays.asList(10, 1, 1, 1);
                baseStats = Arrays.asList(100, 10, 10, 10);
                asciiArt = "";

                return new WaterSlime(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp);

            case "electric_slime":
                experienceModifier = 1;
                statIncreaseOnLevelUp = Arrays.asList(10, 1, 1, 1);
                baseStats = Arrays.asList(100, 10, 10, 10);
                asciiArt = "";

                return new ElectricSlime(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp);
                
            case "plant_slime":
                experienceModifier = 1;
                statIncreaseOnLevelUp = Arrays.asList(10, 1, 1, 1);
                baseStats = Arrays.asList(100, 10, 10, 10);
                asciiArt = "";

                return new PlantSlime(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp);

            default:
                return null;
        }
    }
}
