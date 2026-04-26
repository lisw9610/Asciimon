package asciimon.type;

import java.util.List;

public class ElectricType extends Type {

    private static final ElectricType INSTANCE = new ElectricType();

    private ElectricType() {
        super(List.of("WATER"), List.of("PLANT"));
    }

    public static ElectricType getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "ELECTRIC";
    }
}
