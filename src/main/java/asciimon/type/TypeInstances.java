package src.main.java.asciimon.type;

public enum TypeInstances {
    ELECTRIC_INSTANCE(new ElectricType()),
    FIRE_INSTANCE(new FireType()),
    PLANT_INSTANCE(new PlantType()),
    WATER_INSTANCE(new WaterType());


    private Type typeInstance;

    // Constructor (runs once for each constant above)
    private TypeInstances(Type typeInstance) {
        this.typeInstance = typeInstance;
    }

    public Type getInstance() {
        return this.typeInstance;
    }
}
