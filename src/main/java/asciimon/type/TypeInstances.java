package asciimon.type;

public enum TypeInstances {
    ELECTRIC_INSTANCE(new ElectricType()),
    FIRE_INSTANCE(new FireType()),
    PLANT_INSTANCE(new PlantType()),
    WATER_INSTANCE(new WaterType());


    private Type typeInstance;

    private TypeInstances(Type typeInstance) {
        this.typeInstance = typeInstance;
    }

    public Type getInstance() {
        return this.typeInstance;
    }
}
