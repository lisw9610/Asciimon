package asciimon.type;

import java.util.List;

public abstract class Type { 
    protected List<String> advantageAgainst;
    protected List<String> disadvantageAgainst;
    private Double advantageModifier;
    private Double disadvantageModifier;


    protected Type(List<String> advantageAgainst, List<String> disadvantageAgainst) {
        this.advantageAgainst = advantageAgainst;
        this.disadvantageAgainst = disadvantageAgainst;
        this.advantageModifier = 2.0;
        this.disadvantageModifier = 0.5;
    }

    public Double getAdvantageModifier() {
        return this.advantageModifier;
    }

    public Double getDisadvantageModifier() {
        return this.disadvantageModifier;
    }

    public boolean hasAdvantageAgainst(Type type) {
        return advantageAgainst.contains(type.toString());
    }

    public boolean hasDisadvantageAgainst(Type type) {
        return disadvantageAgainst.contains(type.toString());
    }

    public double getEffectiveness(Type enemyType) {
        if (hasAdvantageAgainst(enemyType)) {
            return advantageModifier;
        }
        if (hasDisadvantageAgainst(enemyType)) {
            return disadvantageModifier;
        }
        return 1.0;
    }

    public abstract String toString();

}
