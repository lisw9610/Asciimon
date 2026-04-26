package asciimon.move;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.status_effects.BuffEffect;
import asciimon.status_effects.StatusEffectTracker;
import asciimon.type.Type;

public class Buff extends Move {

    public Buff(String name, Type type, StatType stat, int amount, int duration) {
        super(name, type, stat, amount, duration, false);
    }


    @Override
    public void executeMove(Card user, Card target) {
        BuffEffect effect = new BuffEffect(getImpactedStat(), getStatImpact());
        user.addStatusEffect(new StatusEffectTracker(getImpactDuration(), effect, user));
    }

    public void onExpire(Card target) {
        target.updateModifier(getImpactedStat(), -getStatImpact());
    }
}
