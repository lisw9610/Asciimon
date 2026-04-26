package asciimon;

import asciimon.card.Card;
import asciimon.card.CardFactory;
import asciimon.card.StatType;
import asciimon.move.Buff;
import asciimon.move.DamageOverTime;
import asciimon.move.Debuff;
import asciimon.move.Heal;
import asciimon.move.Move;
import asciimon.move.MoveFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveEffectTest {

    private Card testCard;
    private Card enemyCard;

    @BeforeEach
    void setup() {
        testCard = CardFactory.createCard("slimey", "plant_slime");
        enemyCard = CardFactory.createCard("enemyslime", "fire_slime");
        assertNotNull(testCard);
        assertNotNull(enemyCard);
    }

    @Test
    void testBuffMoveIncreasesStat() {
        int initialAttack = testCard.getModifiedStat(StatType.ATTACK);

        Move buffMove = MoveFactory.getMoveInstance("overheat");
        buffMove.executeMove(testCard, testCard);

        // Stat should be increased immediately
        assertEquals(initialAttack + 2, testCard.getModifiedStat(StatType.ATTACK));

        // simulate turn pass
        testCard.doTurn(0, testCard);

        // Should still be increased before expiration
        assertEquals(initialAttack + 2, testCard.getModifiedStat(StatType.ATTACK));
    }

    // -------------------------
    // Debuff move tests
    // -------------------------
    @Test
    void testDebuffMoveDecreasesEnemyStat() {
        int initialDefense = enemyCard.getModifiedStat(StatType.DEFENSE);

        Move debuffMove = MoveFactory.getMoveInstance("paralyze");
        debuffMove.executeMove(testCard, enemyCard);

        assertEquals(initialDefense - 2, enemyCard.getModifiedStat(StatType.SPEED));

        enemyCard.doTurn(0, enemyCard);

        assertEquals(initialDefense - 2, enemyCard.getModifiedStat(StatType.SPEED));
    }

    @Test
    void testDamageOverTimeReducesHP() {
        int initialHp = enemyCard.getHealthPoints();

        Move dotMove = MoveFactory.getMoveInstance("poison needle");
        dotMove.executeMove(testCard, enemyCard);

        // simulate 3 turns
        for (int i = 0; i < 3; i++) {
            enemyCard.doTurn(0, enemyCard);
            System.out.println("After turn " + (i + 1) + ": HP = " + enemyCard.getHealthPoints());
        }

        int expectedHp = initialHp - (3 * 10);
        assertEquals(expectedHp, enemyCard.getHealthPoints());
    }


    @Test
    void testHealMoveRestoresHP() {
        enemyCard.takeDamage(20); // reduce HP first
        int hpAfterDamage = enemyCard.getHealthPoints();

        Move healMove = MoveFactory.getMoveInstance("restoration");
        healMove.executeMove(enemyCard, enemyCard);

        int expectedHp = hpAfterDamage + 10;
        assertEquals(expectedHp, enemyCard.getHealthPoints());
    }
}