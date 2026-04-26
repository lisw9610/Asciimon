package asciimon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import asciimon.card.StatType;
import asciimon.move.Move;
import asciimon.move.MoveFactory;

public class MoveTest {
    @Test
    void getMoveInstance() {
        Move fire = MoveFactory.getMoveInstance("fireball");
        assertNotNull(fire);
        assertEquals("fireball", fire.getMoveName());
        assertNull(fire.getImpactedStat());
        assertEquals(Integer.valueOf(10), fire.getStatImpact());
        assertTrue(fire.targetsEnemy()); // attack should target enemy

        Move rest = MoveFactory.getMoveInstance("restoration");
        assertNotNull(rest);
        assertEquals("restoration", rest.getMoveName());
        assertNull(rest.getImpactedStat());
        assertEquals(Integer.valueOf(10), rest.getStatImpact());
        assertFalse(rest.targetsEnemy()); // heal should target self

        Move par = MoveFactory.getMoveInstance("paralyze");
        assertNotNull(par);
        assertEquals("paralyze", par.getMoveName());
        assertEquals(StatType.SPEED, par.getImpactedStat());
        assertEquals(Integer.valueOf(2), par.getStatImpact());
        assertEquals(Integer.valueOf(2), par.getImpactDuration());
        assertTrue(par.targetsEnemy()); // debuff targets enemy

        Move over = MoveFactory.getMoveInstance("overheat");
        assertNotNull(over);
        assertEquals("overheat", over.getMoveName());
        assertEquals(StatType.ATTACK, over.getImpactedStat());
        assertEquals(Integer.valueOf(2), over.getStatImpact());
        assertEquals(Integer.valueOf(2), over.getImpactDuration());
        assertFalse(over.targetsEnemy() == false ? false : over.targetsEnemy()); // buff targets self
    }
}
