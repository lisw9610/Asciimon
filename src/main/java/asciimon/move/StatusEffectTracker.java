package asciimon.move;

import asciimon.card.Card;

public class StatusEffectTracker {
    private Integer turnDuration;
    private Move statusEffect;

    public StatusEffectTracker(Integer duration, Move statusEffect) {
        this.turnDuration = duration;
        this.statusEffect = statusEffect;
    }

    public Integer getDuration() {return this.turnDuration;}
    public Move getStatusEffect() {return this.statusEffect;}

    public void apply(Card target) {
        statusEffect.applyEffect(target);
    }

    public void turnPasses() {
        if(this.turnDuration > 0) {
            this.turnDuration -= 1;
        }
    }

    public boolean hasExpired() {
        return this.turnDuration <= 0;
    }

    public void onExpire(Card target) {
        statusEffect.onExpire(target);
    }

}
