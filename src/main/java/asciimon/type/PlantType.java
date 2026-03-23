package src.main.java.asciimon.type;

import java.util.Arrays;
import java.util.List;

public class PlantType extends Type{


    public PlantType() {
        List<Type> advantageAgainst = Arrays.asList(TypeInstances.ELECTRIC_INSTANCE.getInstance());
        List<Type> disadvantageAgainst = Arrays.asList(TypeInstances.FIRE_INSTANCE.getInstance());

        super(advantageAgainst, disadvantageAgainst);
    }

    public String toString() {
        return "PLANT";
    }



}
