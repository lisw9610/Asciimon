package asciimon.card;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CardFactory {

    private static String readAsciiArt(String artFileName) {
        Path fileName = Path.of(String.format("./cardAsciiArt/%s", artFileName));

        try {
            return Files.readString(fileName, StandardCharsets.UTF_8);

        } catch (IOException e) {
            return "-----";
        }
    }

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

                asciiArt = readAsciiArt("slime.txt");
                
                return new FireSlime(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp);

            case "water_slime":
                experienceModifier = 1;
                statIncreaseOnLevelUp = Arrays.asList(10, 1, 1, 1);
                baseStats = Arrays.asList(100, 10, 10, 10);

                asciiArt = readAsciiArt("slime.txt");

                return new WaterSlime(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp);

            case "electric_slime":
                experienceModifier = 1;
                statIncreaseOnLevelUp = Arrays.asList(10, 1, 1, 1);
                baseStats = Arrays.asList(100, 10, 10, 10);

                asciiArt = readAsciiArt("slime.txt");


                return new ElectricSlime(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp);
                
            case "plant_slime":
                experienceModifier = 1;
                statIncreaseOnLevelUp = Arrays.asList(10, 1, 1, 1);
                baseStats = Arrays.asList(100, 10, 10, 10);

                asciiArt = readAsciiArt("slime.txt");

                return new PlantSlime(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp);

            default:
                return null;
        }
    }
}
