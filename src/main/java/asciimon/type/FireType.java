package asciimon.type;

public class FireType extends Type {

    private static final FireType INSTANCE = new FireType();

    private FireType() {}

    public static FireType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean hasAdvantageAgainst(Type type) {
        return type instanceof PlantType;
    }

    @Override
    public boolean hasDisadvantageAgainst(Type type) {
        return type instanceof WaterType;
    }

    @Override
    public String toString() {
        return "FIRE";
    }
}