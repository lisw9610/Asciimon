package src.main.java.asciimon.move;

public class Debuff extends Move{
    private String modifiedStat;
    private Integer debuffAmount;

    public Debuff(String moveName, String moveType, String modifiedStat, Integer debuffAmount) {
        super(moveName, moveType);
        this.modifiedStat = modifiedStat;
        this.debuffAmount = debuffAmount;
    }
}
