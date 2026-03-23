package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public class Debuff extends Move{
    private String modifiedStat;
    private Integer debuffAmount;

    public Debuff(String moveName, Type moveType, String modifiedStat, Integer debuffAmount) {
        super(moveName, moveType);
        this.modifiedStat = modifiedStat;
        this.debuffAmount = debuffAmount;
    }

    public String getModifiedStat() {
        return this.modifiedStat;
    }

    public Integer getDebuffAmount() {
        return this.debuffAmount;
    }
}


