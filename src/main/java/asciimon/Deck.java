package src.main.java.asciimon;

import java.util.ArrayList;
import java.util.List;

import src.main.java.asciimon.card.Card;

public class Deck {
    private Integer maxDeckSize;
    private List<Card> deck;

    private Deck(DeckBuilder builder) {
        this.maxDeckSize = builder.maxDeckSize;
        this.deck = builder.deck;
    }

    public Card pickCard(Integer cardIndex) {
        if(cardIndex >= 0 && cardIndex < maxDeckSize) {
            return deck.get(cardIndex);
        }
        return null;
    }

    public static class DeckBuilder {
        private Integer maxDeckSize = 0;
        private List<Card> deck = new ArrayList<>();

        private DeckBuilder() {}

        public static DeckBuilder getInstance() {
            return new DeckBuilder();
        }

        public DeckBuilder setMaxDeckSize(Integer deckSize) {
            this.maxDeckSize = deckSize;
            return this;
        }

        public DeckBuilder addCard(Card card) {
            deck.add(card);
            return this;
        }

        public Deck createDeck() {
            return new Deck(this);
        }

    }

}
