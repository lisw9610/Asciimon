package asciimon.type;

import java.util.List;

public class FireType extends Type {

    private static final FireType INSTANCE = new FireType();

    private FireType() {
        super(List.of("PLANT"), List.of("WATER"));
    }

    public static FireType getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "FIRE";
    }
}
