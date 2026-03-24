package asciimon.type;

import java.util.Arrays;
import java.util.List;

public class PlantType extends Type{


    public PlantType() {
        List<String> advantageAgainst = Arrays.asList("ELECTRIC");
        List<String> disadvantageAgainst = Arrays.asList("FIRE");

        super(advantageAgainst, disadvantageAgainst);
    }

    public String toString() {
        return "PLANT";
    }



}
