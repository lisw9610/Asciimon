package src.main.java.asciimon;

import src.main.java.asciimon.card.Card;
import src.main.java.asciimon.move.Move;

public class Match {
    private Deck player1Deck;
    private Deck player2Deck;
    private Card player1CardInPlay;
    private Card Player2CardInPlay;
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

    private void drawCards() {
        this.player1CardInPlay = this.player1Deck.pickCard(0);
        this.Player2CardInPlay = this.player2Deck.pickCard(0);
    }

    public void playTurn() {
        drawCards();

        Move p1Move = player1CardInPlay.playMove(0);
        Move p2Move = Player2CardInPlay.playMove(0);

        

        turnNumber += 1;
    }

}
