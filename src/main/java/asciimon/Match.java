package asciimon;

import asciimon.card.Card;

public class Match {
    private Deck player1Deck;
    private Deck player2Deck;
    private Integer turnNumber;

    public Match(Deck p1Deck, Deck p2Deck) {
        this.player1Deck = p1Deck;
        this.player2Deck = p2Deck;
        this.turnNumber = 0;
    }

    public Deck getP1Deck() {
        return this.player1Deck;
    }

    public Deck getP2Deck() {
        return this.player2Deck;
    }

    public Integer getTurnNumber() {
        return this.turnNumber;
    }

    private void playersDrawCards(Integer p1CardIndex, Integer p2CardIndex) {
        this.player1Deck.pickCard(0);
        this.player2Deck.pickCard(0);
    }

    private void doFight(Integer p1MoveIndex, Integer p2MoveIndex) {
        Card p1Card = this.player1Deck.getCardInPlay();
        Card p2Card = this.player2Deck.getCardInPlay();

        p1Card.doTurn(p1MoveIndex, p2Card);
        p2Card.doTurn(p2MoveIndex, p1Card);

    }

    public void playTurn() {
        playersDrawCards(0, 0);

        doFight(0, 0);

        turnNumber += 1;
    }

}
