package asciimon.card;

public class CardFactory {
    public static Card createCard(String name, String cardName) {
        switch(cardName.toLowerCase()) {
            case "fire slime":
                return new FireSlime(name);

            case "water slime":
                return new WaterSlime(name);

            case "electric slime":
                return new ElectricSlime(name);
                
            case "plant slime":
                return new PlantSlime(name);

            default:
                return null;
        }
    }
}
