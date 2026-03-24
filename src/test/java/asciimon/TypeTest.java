package asciimon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import asciimon.type.Type;
import asciimon.type.TypeInstances;

public class TypeTest {
    private Type electric() { return TypeInstances.ELECTRIC_INSTANCE.getInstance(); }
    private Type fire()     { return TypeInstances.FIRE_INSTANCE.getInstance(); }
    private Type plant()    { return TypeInstances.PLANT_INSTANCE.getInstance(); }
    private Type water()    { return TypeInstances.WATER_INSTANCE.getInstance(); }

    @Test
    void returnsSingletonFromTypeInstances() {
        assertNull(Type.getInstance(), "Type.INSTANCE should be null by default");
        assertNotNull(electric());
        assertNotNull(fire());
        assertNotNull(plant());
        assertNotNull(water());
    }

    @Test
    void toStringReturnsTypeName() {
        assertEquals("ELECTRIC", electric().toString());
        assertEquals("FIRE", fire().toString());
        assertEquals("PLANT", plant().toString());
        assertEquals("WATER", water().toString());
    }

    @Test
    void advantageAndDisadvantageModifiersAreCorrectValues() {
        Type t = electric();
        assertEquals(2.0, t.getAdvantageModifier());
        assertEquals(0.5, t.getDisadvantageModifier());
    }

    @Test
    void testAdvantageAndDisadvantage() {
        // Based on typical Pokemon-like relationships:
        // Electric is strong against Water
        Type electric = electric();
        Type water  = water();
        Type plant = plant();
        Type fire = fire();



        assertTrue(electric.hasAdvantageAgainst(water), "Electric should have advantage vs Water");
        assertTrue(electric.hasDisadvantageAgainst(plant), "Electric should have disadvantage vs Plant");

        assertTrue(water.hasAdvantageAgainst(fire), "Water should have advantage vs Fire");
        assertTrue(water.hasDisadvantageAgainst(electric), "Water should have disadvantage vs Plant");

        assertTrue(plant.hasAdvantageAgainst(electric), "Plant should have advantage vs Electric");
        assertTrue(plant.hasDisadvantageAgainst(fire), "Plant should have disadvantage vs Fire");

        assertTrue(fire.hasAdvantageAgainst(plant), "Fire should have advantage vs Plant");
        assertTrue(fire.hasDisadvantageAgainst(water), "Fire should have disadvantage vs Water");
    }

}
