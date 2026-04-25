package asciimon.type;

import java.util.List;

public class WaterType extends Type {

    private static final WaterType INSTANCE = new WaterType();

    private WaterType() {
        super(List.of(FireType.getInstance()), List.of(ElectricType.getInstance()));
    }

    public static WaterType getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Fire";
    }
}
