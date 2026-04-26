package asciimon.type;

import java.util.List;

public class WaterType extends Type {

    private static final WaterType INSTANCE = new WaterType();

    private WaterType() {
        super(List.of("FIRE"), List.of("ELECTRIC"));
    }

    public static WaterType getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "WATER";
    }
}
