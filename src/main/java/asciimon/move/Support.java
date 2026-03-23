package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public class Support extends Move{
    private String modifiedStat;
    private Integer buffAmount;

    public Support(String moveName, Type moveType, String modifiedStat, Integer buffAmount) {
        super(moveName, moveType);
        this.modifiedStat = modifiedStat;
        this.buffAmount = buffAmount;
    }

    public String getModifiedStat() {
        return this.modifiedStat;
    }

    public Integer getBuffAmount() {
        return this.buffAmount;
    }

}
