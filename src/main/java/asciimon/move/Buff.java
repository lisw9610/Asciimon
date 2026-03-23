package src.main.java.asciimon.move;

public class Buff extends Move{
    private String modifiedStat;
    private Integer buffAmount;

    public Buff(String moveName, String moveType, String modifiedStat, Integer buffAmount) {
        super(moveName, moveType);
        this.modifiedStat = modifiedStat;
        this.buffAmount = buffAmount;
    }

}
