package asciimon.type;

public class PlantType extends Type {

    private static final PlantType INSTANCE = new PlantType();

    private PlantType() {}

    public static PlantType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean hasAdvantageAgainst(Type type) {
        return type instanceof ElectricType;
    }

    @Override
    public boolean hasDisadvantageAgainst(Type type) {
        return type instanceof FireType;
    }

    @Override
    public String toString() {
        return "PLANT";
    }
}