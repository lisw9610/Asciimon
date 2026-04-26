package asciimon;

import asciimon.Deck.DeckBuilder;
import asciimon.card.Card;
import asciimon.card.CardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private Player player1;
    private Player player2;
    private Match match;

    @BeforeEach
    void setup() {
        Card c1 = CardFactory.createCard("Slimey1", "plant_slime");
        Card c2 = CardFactory.createCard("Slimey2", "fire_slime");

        Deck deck1 = DeckBuilder.getInstance().setMaxDeckSize(6).addCard(c1).createDeck();
        Deck deck2 = DeckBuilder.getInstance().setMaxDeckSize(6).addCard(c2).createDeck();

        player1 = new Player("Alice", deck1);
        player2 = new Player("Bob", deck2);

        match = new Match(player1, player2);
        match.startBattle();
    }

    @Test
    void testBattleStartPicksActiveCard() {
        assertNotNull(player1.getActiveCard());
        assertNotNull(player2.getActiveCard());
        assertEquals("Slimey1", player1.getActiveCard().getName());
        assertEquals("Slimey2", player2.getActiveCard().getName());
    }

    @Test
    void testPlayersAttackEachOther() {
        TurnAction action1 = TurnAction.move(player1, 0);
        TurnAction action2 = TurnAction.move(player2, 0);

        match.playTurn(action1, action2);

        int hp1 = player1.getActiveCard().getHealthPoints();
        int hp2 = player2.getActiveCard().getHealthPoints();
        assertTrue(hp1 < player1.getActiveCard().getMaxHealthPoints() && hp2 < player2.getActiveCard().getMaxHealthPoints());

        List<String> log = match.getBattleLog();
        assertTrue(log.get(0).contains("used"));
    }


    @Test
    void testBattleEndsAndWinnerIsCorrect() {
        player2.getActiveCard().takeDamage(player2.getActiveCard().getMaxHealthPoints());

        TurnAction action1 = TurnAction.move(player1, 0);
        TurnAction action2 = TurnAction.move(player2, 0);

        match.playTurn(action1, action2);

        assertTrue(match.isBattleOver());
        assertEquals(player1, match.getWinner());
    }

    @Test
    void testSwitchingCardsLogsCorrectly() {
        Card c1 = CardFactory.createCard("Slimey1", "plant_slime");
        Card c2 = CardFactory.createCard("Slimey2", "fire_slime");
        Card c3 = CardFactory.createCard("ExtraSlime", "plant_slime");

        Deck deck1 = DeckBuilder.getInstance().setMaxDeckSize(2).addCard(c1).addCard(c3).createDeck();
        Deck deck2 = DeckBuilder.getInstance().setMaxDeckSize(1).addCard(c2).createDeck();

        player1 = new Player("Alice", deck1);
        player2 = new Player("Bob", deck2);

        match = new Match(player1, player2);
        match.startBattle();


        TurnAction switchAction = TurnAction.swap(player1, 1);
        TurnAction moveAction = TurnAction.move(player2, 0); 

        match.playTurn(switchAction, moveAction);

        List<String> log = match.getBattleLog();
        boolean switchLogged = log.stream().anyMatch(s -> s.contains("switched"));
        assertTrue(switchLogged);
    }
}