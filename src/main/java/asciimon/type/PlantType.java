package src.main.java.asciimon.type;

import java.util.ArrayList;
import java.util.List;

public class PlantType extends Type{

    public PlantType() {
        List<Type> advantageAgainst = new ArrayList<>();
        List<Type> disadvantageAgainst = new ArrayList<>();

        super(advantageAgainst, disadvantageAgainst);
    }

    public String toString() {
        return "PLANT";
    }

}
