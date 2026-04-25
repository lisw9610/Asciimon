package asciimon;

import asciimon.card.Card;

public class Player {
    private final String name;
    private Deck deck;
    private Integer wins;
    private Integer losses;

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.wins = 0;
        this.losses = 0;
    }

    public String getName() {
        return this.name;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Integer getWins() {
        return this.wins;
    }

    public Integer getLosses() {
        return this.losses;
    }

    public void addWin() {
        this.wins++;
    }

    public void addLoss() {
        this.losses++;
    }

    public Card getActiveCard() {
        return deck.getCardInPlay();
    }

    public boolean hasLost() {
        return deck.hasNoCardsLeft();
    }

    @Override
    public String toString() {
        return this.name + " (Wins: " + this.wins + ", Losses: " + this.losses + ")";
    }
}

