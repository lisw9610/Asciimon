package asciimon;

import asciimon.card.Card;
import asciimon.card.StatType;

public class Match {
    private final Player player1;
    private final Player player2;
    private boolean isBattleOver = false;
    private Integer turnCount;

    public Match(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }


    //======================================== SETUP ========================================

    public void startBattle() {
        player1.getDeck().pickCard(0);
        player2.getDeck().pickCard(0);
    }

    //======================================== TURN ========================================

    public void playTurn(int p1MoveIndex, int p2MoveIndex) {
        if (isBattleOver) return;

        TurnAction action1 = new TurnAction(player1, p1MoveIndex);
        TurnAction action2 = new TurnAction(player2, p2MoveIndex);

        executeTurn(action1, action2);

        checkBattleEnd();
    }

    //======================================== EXECUTE TURN ========================================

    private void executeTurn(TurnAction a1, TurnAction a2) {
        Card c1 = a1.getPlayer().getActiveCard();
        Card c2 = a2.getPlayer().getActiveCard();

        //deciding order by speed
        boolean p1GoesFirst = c1.getModifiedStat(StatType.SPEED) >= c2.getModifiedStat(StatType.SPEED);

        if (p1GoesFirst) {
            executeAction(a1, c1, c2);
            if (!c2.isDead()) {
                executeAction(a2, c2, c1);
            }
        } else {
            executeAction(a2, c2, c1);
            if (!c1.isDead()) {
                executeAction(a1, c1, c2);
            }
        }
    }

    private void executeAction(TurnAction action, Card user, Card target) {
        user.doTurn(action.getMoveIndex(), target);

        if (target.isDead()) {
            handleFaint(action.getPlayer() == player1 ? player2 : player1);
        }
    }

    //======================================== FAINTING ========================================

    private void handleFaint(Player player) {
        Deck deck = player.getDeck();

        deck.discardCardInPlay();

        if (!deck.getDeck().isEmpty()) {
            deck.pickCard(0); // auto-pick next (can later let player choose)
        }
    }

    //======================================== END ========================================

    private void checkBattleEnd() {
        if (player1.hasLost() || player2.hasLost()) {
            isBattleOver = true;
        }
    }

    public Player getWinner() {
        if (!isBattleOver) return null;

        if (player1.hasLost()) return player2;
        if (player2.hasLost()) return player1;

        return null;
    }

    public boolean isBattleOver() {
        return isBattleOver;
    }
}

