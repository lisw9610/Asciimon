package asciimon.move;

import asciimon.card.Card;
import asciimon.status_effects.DamageOverTimeEffect;
import asciimon.status_effects.StatusEffectTracker;
import asciimon.type.Type;

public class DamageOverTime extends Move {

    public DamageOverTime(String name, Type type, Integer amount, Integer duration) {
        super(name, type, null, amount, duration, true);
    }


    @Override
    public void executeMove(Card user, Card target) {
        user.addStatusEffect(new StatusEffectTracker(getImpactDuration(), new DamageOverTimeEffect(getStatImpact()), target));
    }

    public void onExpire(Card target) {
        target.updateModifier(getImpactedStat(), -getStatImpact());
    }

}
