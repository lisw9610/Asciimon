package asciimon.move;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.status_effects.DebuffEffect;
import asciimon.status_effects.StatusEffectTracker;
import asciimon.type.Type;

public class Debuff extends Move {

    public Debuff(String name, Type type, StatType stat, int amount, int duration) {
        super(name, type, stat, amount, duration, true);
    }

    @Override
    public void executeMove(Card user, Card target) {
        DebuffEffect effect = new DebuffEffect(getImpactedStat(), Math.abs(getStatImpact()));
        target.addStatusEffect(new StatusEffectTracker(getImpactDuration(), effect, target));
    }

    public void onExpire(Card target) {
        target.updateModifier(getImpactedStatEnum(), getStatImpact());
    }
}