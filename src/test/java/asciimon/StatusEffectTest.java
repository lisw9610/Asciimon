package asciimon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import asciimon.card.Card;
import asciimon.card.CardFactory;
import asciimon.card.StatType;
import asciimon.status_effects.BuffEffect;
import asciimon.status_effects.DamageOverTimeEffect;
import asciimon.status_effects.DebuffEffect;
import asciimon.status_effects.StatusEffectTracker;

public class StatusEffectTest {
    private Card testCard;

    @BeforeEach
    void setup() {
        testCard = CardFactory.createCard("slimey", "plant_slime");
        assertNotNull(testCard, "CardFactory returned null");
    }


    @Test
    void testBuffEffectAppliesAndExpires() {
        int initialAttack = testCard.getModifiedStat(StatType.ATTACK);

        BuffEffect buff = new BuffEffect(StatType.ATTACK, 5);
        StatusEffectTracker tracker = new StatusEffectTracker(2, buff, testCard);
        testCard.addStatusEffect(tracker);

        assertEquals(initialAttack + 5, testCard.getModifiedStat(StatType.ATTACK));

        testCard.doTurn(0, testCard); // dummy turn to trigger effect processing
        assertEquals(initialAttack + 5, testCard.getModifiedStat(StatType.ATTACK));

        // simulate turn 2 (effect expires after this)
        testCard.doTurn(0, testCard);
        assertEquals(initialAttack, testCard.getModifiedStat(StatType.ATTACK));
    }

    @Test
    void testDebuffEffectAppliesAndExpires() {
        int initialDefense = testCard.getModifiedStat(StatType.DEFENSE);

        DebuffEffect debuff = new DebuffEffect(StatType.DEFENSE, 3);
        StatusEffectTracker tracker = new StatusEffectTracker(1, debuff, testCard);
        testCard.addStatusEffect(tracker);

        // onApply should reduce stat
        assertEquals(initialDefense - 3, testCard.getModifiedStat(StatType.DEFENSE));

        // expire should restore stat
        tracker.expire(testCard);
        assertEquals(initialDefense, testCard.getModifiedStat(StatType.DEFENSE));
    }

    @Test
    void testDamageOverTimeReducesHP() {
        int initialHp = testCard.getHealthPoints();

        DamageOverTimeEffect dot = new DamageOverTimeEffect(7);
        StatusEffectTracker tracker = new StatusEffectTracker(3, dot, testCard);
        testCard.addStatusEffect(tracker);

        // simulate 3 turns
        for (int i = 0; i < 3; i++) {
            testCard.turnPasses();

            System.out.println("After turn " + (i + 1) + ": HP = " + testCard.getHealthPoints());
            
        }

        int expectedHp = initialHp - (3 * 7);
        assertEquals(expectedHp, testCard.getHealthPoints());
    }

}
