package asciimon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import asciimon.type.Type;
import asciimon.type.TypeInstances;
import asciimon.card.Card;
import asciimon.card.CardFactory;

class CardTest {

    private Card testCard;

    static class DummyCard extends Card {
        DummyCard(String name, Integer health, Integer attack, Integer defense, Integer speed, List<Integer> nextLevelExp, List<Integer> statIncrease, Type type) {
            super(name, health, attack, defense, speed, nextLevelExp, statIncrease, type);
        }
    }

    @BeforeEach
    void setup() {
        List<Integer> nextLevelExp = Arrays.asList(10, 20, 30);
        List<Integer> statIncrease = Arrays.asList(5, 1, 2, 3); // health, atk, def, spd
        testCard = new DummyCard("Tester", 50, 10, 8, 7, nextLevelExp, statIncrease, TypeInstances.ELECTRIC_INSTANCE.getInstance());
    }

    @Test
    void testGetters() {
        assertEquals("Tester", testCard.getName());
        assertEquals(1, testCard.getLevel());
        assertEquals(0, testCard.getCurrentExp());
        assertEquals(10, testCard.getExperienceForNextLevel());
        assertEquals(50, testCard.getMaxHealth());
        assertEquals(50, testCard.getHealth());
        assertEquals(10, testCard.getBaseAttack());
        assertEquals(8, testCard.getBaseDefense());
        assertEquals(7, testCard.getBaseSpeed());
        assertEquals(TypeInstances.ELECTRIC_INSTANCE.getInstance(), testCard.getType());
        assertEquals(4, testCard.getMaxMoveCount());
    }

    @Test
    void testGainExperienceAndCanLevelUp() {
        testCard.gainExperience(5);
        assertEquals(5, testCard.getCurrentExp());
        assertFalse(testCard.canLevelUp());

        testCard.gainExperience(5);
        assertEquals(10, testCard.getCurrentExp());
        assertTrue(testCard.canLevelUp());
    }

    @Test
    void testDoLevelUp() {
        testCard.gainExperience(12);
        assertTrue(testCard.canLevelUp());
        testCard.doLevelUp();

        assertEquals(2, testCard.getLevel());
        assertEquals(2, testCard.getCurrentExp());
 
        assertEquals(55, testCard.getMaxHealth());
        assertEquals(11, testCard.getBaseAttack());
        assertEquals(10, testCard.getBaseDefense()); 
        assertEquals(10, testCard.getBaseSpeed());
    }

    @Test
    void testTakeDamageAndIsDead() {
        testCard.takeDamage(20);
        assertEquals(30, testCard.getHealth());
        assertFalse(testCard.isDead());

        testCard.takeDamage(1000);
        assertEquals(0, testCard.getHealth());
        assertTrue(testCard.isDead());
    }

    @Test
    void testHealDamage() {
        testCard.takeDamage(30);
        assertEquals(20, testCard.getHealth());
        testCard.healDamage(5);
        assertEquals(25, testCard.getHealth());
        testCard.healDamage(100);
        assertEquals(testCard.getMaxHealth(), testCard.getHealth());
    }

    @Test
    void testDoTurnAttackAndHeal() {
        Card c1 = CardFactory.createCard("A", "fire slime");
        Card c2 = CardFactory.createCard("B", "plant slime");

        c1.doTurn(0, c2);
        assertEquals(90, c2.getHealth());

        c2.doTurn(1, c1);
        assertEquals(100, c2.getHealth());
    }

    @Test
    void testToString_containsLevelAndExpAndName() {
        String s = testCard.toString();
        assertTrue(s.contains("Tester"));
        assertTrue(s.contains("lv.1"));
        assertTrue(s.contains("0/10"));
    }

    @Test
    void testCardFactory_createsKnownTypes() {
        Card c1 = CardFactory.createCard("A", "fire slime");
        Card c2 = CardFactory.createCard("B", "water slime");
        Card c3 = CardFactory.createCard("C", "electric slime");
        Card c4 = CardFactory.createCard("D", "plant slime");

        assertNotNull(c1);
        assertNotNull(c2);
        assertNotNull(c3);
        assertNotNull(c4);

        assertNull(CardFactory.createCard("X", "unknown"));
    }

}
