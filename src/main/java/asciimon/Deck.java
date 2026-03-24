package asciimon;

import java.util.ArrayList;
import java.util.List;

import asciimon.card.Card;

public class Deck {
    private Integer maxDeckSize;
    private List<Card> deck = new ArrayList<>();
    private List<Card> discarded = new ArrayList<>();
    private Card inPlay = null;

    private Deck(DeckBuilder builder) {
        this.maxDeckSize = builder.maxDeckSize;
        this.deck = builder.deck;
    }

    public void pickCard(Integer cardIndex) {
        if(cardIndex >= 0 && cardIndex < maxDeckSize) {
            this.inPlay = deck.get(cardIndex);
        } else {
            this.inPlay = null;
        }
    }

    public Card getCardInPlay() {
        return this.inPlay;
    }

    public void discardCardInPlay() {
        deck.remove(inPlay);
        discarded.add(inPlay);
        this.inPlay = null;
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
