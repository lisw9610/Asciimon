package src.main.java.asciimon.type;

import java.util.Arrays;
import java.util.List;

public class FireType extends Type{

    public FireType() {
        List<Type> advantageAgainst = Arrays.asList(TypeInstances.PLANT_INSTANCE.getInstance());
        List<Type> disadvantageAgainst = Arrays.asList(TypeInstances.FIRE_INSTANCE.getInstance());

        super(advantageAgainst, disadvantageAgainst);
    }

    public String toString() {
        return "FIRE";
    }

}
