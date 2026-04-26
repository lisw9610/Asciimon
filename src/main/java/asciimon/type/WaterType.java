package asciimon.type;

public class WaterType extends Type {

    private static final WaterType INSTANCE = new WaterType();

    private WaterType() {}

    public static WaterType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean hasAdvantageAgainst(Type type) {
        return type instanceof FireType;
    }

    @Override
    public boolean hasDisadvantageAgainst(Type type) {
        return type instanceof ElectricType;
    }

    @Override
    public String toString() {
        return "WATER";
    }
}