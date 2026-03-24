package asciimon.type;

import java.util.List;

public abstract class Type { 
    protected List<String> advantageAgainst;
    protected List<String> disadvantageAgainst;
    private Double advantageModifier;
    private Double disadvantageModifier;

    protected static Type INSTANCE = null;

    protected Type(List<String> advantageAgainst, List<String> disadvantageAgainst) {
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
        for(String advantageType : advantageAgainst) {
            if(advantageType.equals(type.toString())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDisadvantageAgainst(Type type) {
        for(String disadvantageAgainst : disadvantageAgainst) {
            if(disadvantageAgainst.equals(type.toString())) {
                return true;
            }
        }
        return false;
    }

    public abstract String toString();

}
