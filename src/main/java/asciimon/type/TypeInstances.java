package asciimon.type;

public enum TypeInstances {
    ELECTRIC_INSTANCE(ElectricType.getInstance()),
    FIRE_INSTANCE(FireType.getInstance()),
    PLANT_INSTANCE(PlantType.getInstance()),
    WATER_INSTANCE(WaterType.getInstance());

    private final Type typeInstance;

    TypeInstances(Type typeInstance) {
        this.typeInstance = typeInstance;
    }

    public Type getInstance() {
        return this.typeInstance;
    }
}