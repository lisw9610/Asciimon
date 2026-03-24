package asciimon.type;

import java.util.Arrays;
import java.util.List;

public class WaterType extends Type{


    public WaterType() {
        List<String> advantageAgainst = Arrays.asList("FIRE");
        List<String> disadvantageAgainst = Arrays.asList("ELECTRIC");

        super(advantageAgainst, disadvantageAgainst);
    }

    public String toString() {
        return "WATER";
    }

}
