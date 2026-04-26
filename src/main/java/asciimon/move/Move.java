package asciimon.move;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.type.Type;

public abstract class Move {
    private String moveName;
    private Type moveType;
    private StatType impactedStat;
    private Integer statImpact;
    private Integer impactDuration;
    private boolean targetsEnemy;

    public Move(String moveName, Type moveType, StatType impactedStat, Integer statImpact, Integer impactDuration, boolean targetsEnemy) {
        this.moveName = moveName;
        this.moveType = moveType;
        this.impactedStat = impactedStat;
        this.statImpact = statImpact;
        this.impactDuration = impactDuration;
        this.targetsEnemy = targetsEnemy;
    }

    public String getMoveName() {
        return this.moveName;
    }

    public Type getType() {
        return this.moveType;
    }

    public String getImpactedStat() {
        return this.impactedStat.name().toLowerCase();
    }

    public StatType getImpactedStatEnum() {
        return this.impactedStat;
    }

    public Integer getStatImpact() {
        return this.statImpact;
    }

    public Integer getImpactDuration() {
        return this.impactDuration;
    }

    public boolean targetsEnemy() {
        return this.targetsEnemy;
    }

    public void applyEffect(Card target) {}

    public void onExpire(Card target) {}

    public abstract void executeMove(Card user, Card target);
}