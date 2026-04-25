package asciimon;

public class TurnAction {
    private final Player player;
    private final Integer moveIndex;   // null if switching
    private final Integer switchIndex; // null if attacking

    private TurnAction(Player player, Integer moveIndex, Integer switchIndex) {
        this.player = player;
        this.moveIndex = moveIndex;
        this.switchIndex = switchIndex;
    }

    public static TurnAction attack(Player player, int moveIndex) {
        return new TurnAction(player, moveIndex, null);
    }

    public static TurnAction swap(Player player, int switchIndex) {
        return new TurnAction(player, null, switchIndex);
    }

    public boolean isSwitching() {
        return switchIndex != null;
    }

    public Player getPlayer() { return player; }
    public Integer getMoveIndex() { return moveIndex; }
    public Integer getSwitchIndex() { return switchIndex; }
}