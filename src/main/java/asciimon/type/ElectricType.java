package asciimon.type;

import java.util.Arrays;
import java.util.List;

public class ElectricType extends Type{

    public ElectricType() {
        List<String> advantageAgainst = Arrays.asList("WATER");
        List<String> disadvantageAgainst = Arrays.asList("PLANT");

        super(advantageAgainst, disadvantageAgainst);
    }

    public String toString() {
        return "ELECTRIC";
    }

}
