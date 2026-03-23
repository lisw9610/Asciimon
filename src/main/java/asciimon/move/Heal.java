package src.main.java.asciimon.move;

public class Heal extends Move{
    private Integer healAmount;

    public Heal(String moveName, String moveType, Integer healAmount) {
        super(moveName, moveType);
        this.healAmount = healAmount;
    }

}
