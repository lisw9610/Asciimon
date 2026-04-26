package asciimon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.move.Move;

public class Match {
    private final Player player1;
    private final Player player2;
    private boolean isBattleOver = false;
    private final Random random = new Random();
    private Integer turnCount;
    private final List<String> battleLog = new ArrayList<>();


    public Match(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }


    //======================================== SETUP ========================================

    public void startBattle() {
        player1.getDeck().pickCard(0);
        player2.getDeck().pickCard(0);
    }

    //======================================== LOGGING ========================================
    public List<String> getBattleLog() {
        return new ArrayList<>(battleLog);
    }

    private void log(String message) {
        battleLog.add(message);
    }


    //======================================== TURN ========================================

    public void playTurn(TurnAction action1, TurnAction action2) {
        if (isBattleOver) return;

        executeTurn(action1, action2);

        checkBattleEnd();
    }

    private void executeTurn(TurnAction a1, TurnAction a2) {
        Card c1 = a1.getPlayer().getActiveCard();
        Card c2 = a2.getPlayer().getActiveCard();

        if (a1.isSwitching() && !a2.isSwitching()) {
            executeSwitch(a1);
            executeAction(a2, c2, c1);
            return;
        }

        if (a2.isSwitching() && !a1.isSwitching()) {
            executeSwitch(a2);
            executeAction(a1, c1, c2);
            return;
        }

        // If both switch
        if (a1.isSwitching() && a2.isSwitching()) {
            executeSwitch(a1);
            executeSwitch(a2);
            return;
        }

        boolean p1First = compareSpeed(c1, c2);

        if (p1First) {
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

    private boolean compareSpeed(Card c1, Card c2) {
        int speed1 = c1.getModifiedStat(StatType.SPEED);
        int speed2 = c2.getModifiedStat(StatType.SPEED);

        if (speed1 > speed2) {
            return true;
        } else if (speed2 > speed1) {
            return false;
        } else {
            return random.nextBoolean();
        }

    }

    private void executeAction(TurnAction action, Card user, Card target) {
        Move move = user.getMoves().get(action.getMoveIndex());

        log(user.getName() + " used " + move.getMoveName());

        int beforeHP = target.getHealthPoints();

        user.doTurn(action.getMoveIndex(), target);

        int afterHP = target.getHealthPoints();
        int damage = beforeHP - afterHP;

        if (damage > 0) {
            log(target.getName() + " took " + damage + " damage");
        }

        if (target.isDead()) {
            log(target.getName() + " fainted!");
            handleFaint(action.getPlayer() == player1 ? player2 : player1);
        }
    }

    private void executeSwitch(TurnAction action) {
        Player player = action.getPlayer();
        Deck deck = player.getDeck();

        Card oldCard = deck.getCardInPlay();
        Card newCard = deck.pickCard(action.getSwitchIndex());

        log(player.getName() + " switched from " + (oldCard != null ? oldCard.getName() : "None") + " to " + newCard.getName());
    }

    //======================================== FAINTING ========================================

    private void handleFaint(Player player) {
        Deck deck = player.getDeck();

        deck.discardCardInPlay();

        if (!deck.getDeck().isEmpty()) {
            deck.pickCard(0); // auto-pick next (can later let player choose)
        }
    }

    //======================================== MATCH END ========================================

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

