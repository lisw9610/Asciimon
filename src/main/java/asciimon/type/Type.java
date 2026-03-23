package src.main.java.asciimon.type;

import java.util.List;

public abstract class Type { 
    protected List<Type> advantageAgainst;
    protected List<Type> disadvantageAgainst;
    private Double advantageModifier;
    private Double disadvantageModifier;

    protected static Type INSTANCE = null;

    protected Type(List<Type> advantageAgainst, List<Type> disadvantageAgainst) {
        this.advantageAgainst = advantageAgainst;
        this.disadvantageAgainst = disadvantageAgainst;
        this.advantageModifier = 2.0;
        this.disadvantageModifier = 0.5;
    }

    public static Type getInstance() {
        return INSTANCE;
    }

    public Double getAdvantageModifier() {
        return this.advantageModifier;
    }

    public Double getDisadvantageModifier() {
        return this.disadvantageModifier;
    }

    public boolean hasAdvantageAgainst(Type type) {
        for(Type advantageType : advantageAgainst) {
            if(advantageType.toString().equals(type.toString())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDisadvantageAgainst(Type type) {
        for(Type disadvantageAgainst : disadvantageAgainst) {
            if(disadvantageAgainst.toString().equals(type.toString())) {
                return true;
            }
        }
        return false;
    }

    public abstract String toString();

}
