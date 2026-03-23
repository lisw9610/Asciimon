package src.main.java.asciimon;

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

}
