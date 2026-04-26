package asciimon.type;

public class ElectricType extends Type {

    private static final ElectricType INSTANCE = new ElectricType();

    private ElectricType() {}

    public static ElectricType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean hasAdvantageAgainst(Type type) {
        return type instanceof WaterType;
    }

    @Override
    public boolean hasDisadvantageAgainst(Type type) {
        return type instanceof PlantType;
    }

    @Override
    public String toString() {
        return "ELECTRIC";
    }
}