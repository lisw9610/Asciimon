package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public abstract class Move {
    private String moveName;
    private Type moveType;
    private String modifiedStat;
    private Integer statModifier;
    private Integer modifierDuration;
    private boolean targetsEnemy; //true if it targets an enemy(attack and debuff) and false if it targets self(support)


    public Move(String moveName, Type moveType, String modifiedStat, Integer statModifier, Integer modifierDuration, boolean targetsEnemy) {
        this.moveName = moveName;
        this.moveType = moveType;
        this.modifiedStat = modifiedStat;
        this.statModifier = statModifier;
        this.modifierDuration = modifierDuration;
        this.targetsEnemy = targetsEnemy;
    }

    public String getMoveName() {
        return this.moveName;
    }

    public Type getType() {
        return this.moveType;
    }

    public String getMofidiedStat() {
        return this.modifiedStat;
    }

    public Integer getStatModifier() {
        return this.statModifier;
    }

    public Integer getModifierDuration() {
        return this.modifierDuration;
    }

    public boolean targetsEnemy() {
        return this.targetsEnemy;
    }

}
