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
import asciimon.move.MoveInstances;

class CardTest {

    private Card testCard;

    private class DummyCard extends Card {
        DummyCard(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp, Type type) {
            super(name, asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, type);
        }
    }

    @BeforeEach
    void setup() {
        String asciiArt = "---";
        List<Integer> baseStats = Arrays.asList(5, 1, 2, 3);
        Integer experienceModifier = 1;
        List<Integer> statIncreaseOnLevelUp = Arrays.asList(5, 1, 2, 3); // health, atk, def, spd
        testCard = new DummyCard("Tester", asciiArt, baseStats, experienceModifier, statIncreaseOnLevelUp, TypeInstances.ELECTRIC_INSTANCE.getInstance());
    }

    @Test
    void testGetters() {
        assertEquals("Tester", testCard.getName());
        assertEquals(1, testCard.getLevel());
        assertEquals(0, testCard.getCurrentExp());
        assertEquals(100, testCard.getExperienceForNextLevel());
        assertEquals(50, testCard.getMaxHealthPoints());
        assertEquals(50, testCard.getHealthPoints());
        assertEquals(5, testCard.getHealth());
        assertEquals(1, testCard.getBaseAttack());
        assertEquals(2, testCard.getBaseDefense());
        assertEquals(3, testCard.getBaseSpeed());
        assertEquals(TypeInstances.ELECTRIC_INSTANCE.getInstance(), testCard.getType());
        assertEquals(4, testCard.getMaxMoveCount());
    }

    @Test
    void testGainExperienceAndCanLevelUp() {
        assertEquals(1, testCard.getLevel());
        testCard.gainExperience(50);
        assertEquals(50, testCard.getCurrentExp());
        assertEquals(1, testCard.getLevel());

        testCard.gainExperience(50);
        assertEquals(0, testCard.getCurrentExp());
        assertEquals(2, testCard.getLevel());
    }

    @Test
    void testDoLevelUp() {
        assertEquals(1, testCard.getLevel());

        testCard.gainExperience(102);

        assertEquals(2, testCard.getLevel());
        assertEquals(2, testCard.getCurrentExp());
 
        assertEquals(10, testCard.getHealth());
        assertEquals(2, testCard.getBaseAttack());
        assertEquals(4, testCard.getBaseDefense()); 
        assertEquals(6, testCard.getBaseSpeed());
    }

    @Test
    void testTakeDamageAndIsDead() {
        testCard.takeDamage(20);
        assertEquals(30, testCard.getHealthPoints());
        assertFalse(testCard.isDead());

        testCard.takeDamage(1000);
        assertEquals(0, testCard.getHealthPoints());
        assertTrue(testCard.isDead());
    }

    @Test
    void testHealDamage() {
        testCard.takeDamage(30);
        assertEquals(20, testCard.getHealthPoints());
        testCard.healDamage(5);
        assertEquals(25, testCard.getHealthPoints());
        testCard.healDamage(100);
        assertEquals(testCard.getMaxHealthPoints(), testCard.getHealthPoints());
    }

    @Test
    void testDoTurnAttackAndHeal() {
        Card c1 = CardFactory.createCard("A", "fire_slime");
        Card c2 = CardFactory.createCard("B", "plant_slime");

        c1.doTurn(0, c2);
        assertEquals(90, c2.getHealthPoints());

        c2.doTurn(1, c1);
        assertEquals(100, c2.getHealthPoints());
    }

    @Test
    void testCardFactory_createsKnownTypes() {
        Card c1 = CardFactory.createCard("A", "fire_slime");
        Card c2 = CardFactory.createCard("B", "water_slime");
        Card c3 = CardFactory.createCard("C", "electric_slime");
        Card c4 = CardFactory.createCard("D", "plant_slime");

        assertNotNull(c1);
        assertNotNull(c2);
        assertNotNull(c3);
        assertNotNull(c4);

        assertNull(CardFactory.createCard("X", "unknown"));
    }

    @Test
    void testRenderCard() {
        testCard.learnMove(MoveInstances.getMoveInstance("fireball"));

        String card = testCard.renderCard();
        assertTrue(card.contains("Tester - lv.1(0/100"));
        assertTrue(card.contains("Type: ELECTRIC"));
        assertTrue(card.contains("HP: 50 / 50"));
        assertTrue(card.contains("Health    : 5"));
        assertTrue(card.contains("Attack    : 1"));
        assertTrue(card.contains("Defense   : 2"));
        assertTrue(card.contains("Speed     : 3"));
        assertTrue(card.contains("fireball"));

        testCard.gainExperience(150);
        testCard.learnMove(MoveInstances.getMoveInstance("discharge"));
        testCard.forgetMove(MoveInstances.getMoveInstance("fireball"));
        testCard.reset();

        card = testCard.renderCard();
        assertTrue(card.contains("Tester - lv.2(50/100"));
        assertTrue(card.contains("Type: ELECTRIC"));
        assertTrue(card.contains("HP: 100 / 100"));
        assertTrue(card.contains("Health    : 10"));
        assertTrue(card.contains("Attack    : 2"));
        assertTrue(card.contains("Defense   : 4"));
        assertTrue(card.contains("Speed     : 6"));
        assertFalse(card.contains("fireball"));
        assertTrue(card.contains("discharge"));

    }

}
