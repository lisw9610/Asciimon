package asciimon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import asciimon.move.Move;
import asciimon.move.MoveInstances;

public class MoveTest {
    @Test
    void getMoveInstance_knownNames_returnExpectedProperties() {
        Move fire = MoveInstances.getMoveInstance("fireball");
        assertNotNull(fire);
        assertEquals("fireball", fire.getMoveName());
        assertEquals("health", fire.getImpactedStat());
        assertEquals(Integer.valueOf(10), fire.getStatImpact());
        assertTrue(fire.targetsEnemy()); // attack should target enemy

        Move rest = MoveInstances.getMoveInstance("restoration");
        assertNotNull(rest);
        assertEquals("restoration", rest.getMoveName());
        assertEquals("health", rest.getImpactedStat());
        assertEquals(Integer.valueOf(10), rest.getStatImpact());
        assertFalse(rest.targetsEnemy()); // heal should target self

        Move par = MoveInstances.getMoveInstance("paralyze");
        assertNotNull(par);
        assertEquals("paralyze", par.getMoveName());
        assertEquals("speed", par.getImpactedStat());
        assertEquals(Integer.valueOf(10), par.getStatImpact());
        assertEquals(Integer.valueOf(2), par.getImpactDuration());
        assertTrue(par.targetsEnemy()); // debuff targets enemy

        Move over = MoveInstances.getMoveInstance("overheat");
        assertNotNull(over);
        assertEquals("overheat", over.getMoveName());
        assertEquals("attack", over.getImpactedStat());
        assertEquals(Integer.valueOf(10), over.getStatImpact());
        assertEquals(Integer.valueOf(2), over.getImpactDuration());
        assertFalse(over.targetsEnemy() == false ? false : over.targetsEnemy()); // buff targets self
    }
}
