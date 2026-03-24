package asciimon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import asciimon.card.Card;
import asciimon.card.CardFactory;

public class DeckTest {
    private Deck.DeckBuilder builder;

    @BeforeEach
    void setUp() {
        builder = Deck.DeckBuilder.getInstance();
    }

    @Test
    void createDeckWithNoCards() {
        Deck deck = builder.setMaxDeckSize(0).createDeck();

        assertNull(deck.getCardInPlay(), "Initially no card should be in play");

        deck.pickCard(-1);
        assertNull(deck.getCardInPlay());

        deck.pickCard(0);
        assertNull(deck.getCardInPlay());
    }

    @Test
    void testPickCardAndCardInPlay() {
        Card c1 = CardFactory.createCard("A", "fire slime");
        Card c2 = CardFactory.createCard("B", "plant slime");
        Deck deck = builder
                .setMaxDeckSize(2)
                .addCard(c1)
                .addCard(c2)
                .createDeck();


        deck.pickCard(0);
        assertEquals(c1, deck.getCardInPlay(), "Picking index 0 should set first card as in play");


        deck.pickCard(1);
        assertEquals(c2, deck.getCardInPlay(), "Picking index 1 should set second card as in play");

        deck.pickCard(2); 
        assertNull(deck.getCardInPlay());
    }

    @Test
    void testDiscardCardInPlay() {
        Card c1 = CardFactory.createCard("A", "fire slime");
        Card c2 = CardFactory.createCard("B", "plant slime");

        Deck deck = builder
                .setMaxDeckSize(2)
                .addCard(c1)
                .addCard(c2)
                .createDeck();

        
        
        deck.pickCard(0);
        assertEquals(c1, deck.getCardInPlay());
        deck.discardCardInPlay();
        assertNull(deck.getCardInPlay());

        deck.pickCard(0);
        assertEquals(c2, deck.getCardInPlay());
    }
}