package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public abstract class Move {
    private String moveName;
    private Type moveType;
    private String impactedStat;
    private Integer statImpact;
    private Integer impactDuration;
    private boolean targetsEnemy; //true if it targets an enemy(attack and debuff) and false if it targets self(support)


    public Move(String moveName, Type moveType, String impactedStat, Integer statImpact, Integer impactDuration, boolean targetsEnemy) {
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

}
