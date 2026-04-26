package asciimon.status_effects;

import asciimon.card.Card;

public class StatusEffectTracker {
    private Integer turnsRemaining;
    private final StatusEffect statusEffect;


    public StatusEffectTracker(Integer duration, StatusEffect effect, Card target) {
        this.turnsRemaining = duration;
        this.statusEffect = effect;

        this.statusEffect.onApply(target);
    }

    public void processTurn(Card target) {
        this.statusEffect.onTurn(target);
        turnsRemaining--;
    }

    public boolean isExpired() {
        return turnsRemaining <= 0;
    }

    public void expire(Card target) {
        this.statusEffect.onExpire(target);
    }
}
