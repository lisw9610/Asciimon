package asciimon.move;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.type.Type;

public class Debuff extends Move {

    public Debuff(String name, Type type, StatType stat, int amount, int duration) {
        super(name, type, stat, -amount, duration, true);
    }


    @Override
    public void executeMove(Card user, Card target) {
        target.updateModifier(getImpactedStat(), getStatImpact());
        target.addStatusEffect(new StatusEffectTracker(getImpactDuration(), this));
    }

    public void onExpire(Card target) {
        target.updateModifier(getImpactedStat(), -getStatImpact());
    }
}

