package src.main.java.asciimon.type;

import java.util.Arrays;
import java.util.List;

public class ElectricType extends Type{

    public ElectricType() {
        List<Type> advantageAgainst = Arrays.asList(TypeInstances.WATER_INSTANCE.getInstance());
        List<Type> disadvantageAgainst = Arrays.asList(TypeInstances.PLANT_INSTANCE.getInstance());

        super(advantageAgainst, disadvantageAgainst);
    }

    public String toString() {
        return "ELECTRIC";
    }

}
