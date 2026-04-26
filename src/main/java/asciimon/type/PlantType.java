package asciimon.type;

import java.util.List;

public class PlantType extends Type {

    private static final PlantType INSTANCE = new PlantType();

    private PlantType() {
        super(List.of("ELECTRIC"), List.of("FIRE"));
    }

    public static PlantType getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "PLANT";
    }
}
