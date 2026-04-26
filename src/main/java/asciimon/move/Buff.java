package asciimon.move;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.type.Type;

public class Buff extends Move {

    public Buff(String name, Type type, StatType stat, int amount, int duration) {
        super(name, type, stat, amount, duration, false);
    }

    @Override
    public void executeMove(Card user, Card target) {
        user.updateModifier(getImpactedStatEnum(), getStatImpact());
        user.addStatusEffect(new StatusEffectTracker(getImpactDuration(), this));
    }

    public void onExpire(Card target) {
        target.updateModifier(getImpactedStatEnum(), -getStatImpact());
    }
}
