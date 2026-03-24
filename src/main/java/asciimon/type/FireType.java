package asciimon.type;

import java.util.Arrays;
import java.util.List;

public class FireType extends Type{

    public FireType() {
        List<String> advantageAgainst = Arrays.asList("PLANT");
        List<String> disadvantageAgainst = Arrays.asList("WATER");

        super(advantageAgainst, disadvantageAgainst);
    }

    public String toString() {
        return "FIRE";
    }

}
