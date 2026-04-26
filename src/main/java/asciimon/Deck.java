package asciimon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import asciimon.card.Card;

public class Deck {
    private final Integer maxDeckSize;
    private final List<Card> deck;
    private final List<Card> discarded = new ArrayList<>();
    private Card inPlay = null;

    private Deck(DeckBuilder builder) {
        this.maxDeckSize = builder.maxDeckSize;
        this.deck = new ArrayList<>(builder.deck);
    }

    public Card pickCard(Integer cardIndex) {
        if (cardIndex >= 0 && cardIndex < deck.size()) {
            this.inPlay = deck.get(cardIndex);
        } else {
            this.inPlay = null;
        }

        return this.inPlay;
    }

    public void discardCardInPlay() {
        if (inPlay == null) return;

        deck.remove(inPlay);
        discarded.add(inPlay);
        inPlay = null;
    }

    public void handleFaint() {
        if (inPlay != null && inPlay.isDead()) {
            discardCardInPlay();

            if (!deck.isEmpty()) {
                inPlay = deck.get(0);
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public boolean hasNoCardsLeft() {
        return deck.isEmpty() && inPlay == null;
    }

    public Card getCardInPlay() {
        return this.inPlay;
    }

    public Integer getDeckSize() {
        return deck.size();
    }

    public List<Card> getDeck() {
        return new ArrayList<>(deck);
    }

    public List<Card> getDiscarded() {
        return new ArrayList<>(discarded);
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
            if (deck.size() >= maxDeckSize) {
                throw new IllegalStateException("Deck is full.");
            }
            deck.add(card);
            return this;
        }

        public Deck createDeck() {
            return new Deck(this);
        }
    }

}
