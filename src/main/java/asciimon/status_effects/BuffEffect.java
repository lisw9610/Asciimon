package asciimon.status_effects;

import asciimon.card.Card;
import asciimon.card.StatType;

public class BuffEffect implements StatusEffect {

    private final StatType stat;
    private final Integer amount;

    public BuffEffect(StatType stat, Integer amount) {
        this.stat = stat;
        this.amount = amount;
    }

    @Override
    public void onApply(Card target) {
        target.updateModifier(stat, amount);
    }

    @Override
    public void onTurn(Card target) {
        // nothing extra each turn
    }

    @Override
    public void onExpire(Card target) {
        target.updateModifier(stat, -amount);
    }
}