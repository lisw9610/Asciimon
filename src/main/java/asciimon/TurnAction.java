package asciimon;

public class TurnAction {
    private final Player player;
    private final int moveIndex;

    public TurnAction(Player player, int moveIndex) {
        this.player = player;
        this.moveIndex = moveIndex;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMoveIndex() {
        return moveIndex;
    }
}