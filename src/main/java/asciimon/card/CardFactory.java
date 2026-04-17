package asciimon.card;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class CardFactory {

    private static String readAsciiArt(String artFileName) {
        if (artFileName == null || artFileName.contains("..") || artFileName.startsWith("/")) {
            throw new IllegalArgumentException("Invalid art file name");
        }
        InputStream in = CardFactory.class.getResourceAsStream("/cardAsciiArt/" + artFileName);
        if (in == null) {
            // diagnostic: list resource root (only for debugging, remove in production)
            System.err.println("Resource not found: cardAsciiArt/" + artFileName);
            return "-----";
        }
        try (in) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
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
