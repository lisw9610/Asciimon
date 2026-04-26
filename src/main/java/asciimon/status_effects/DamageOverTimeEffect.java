package asciimon.status_effects;

import asciimon.card.Card;

public class DamageOverTimeEffect implements StatusEffect {

    private final Integer damagePerTurn;

    public DamageOverTimeEffect(Integer damagePerTurn) {
        this.damagePerTurn = damagePerTurn;
    }

    @Override
    public void onApply(Card target) {}

    @Override
    public void onTurn(Card target) {
        target.takeDamage(damagePerTurn);
    }

    @Override
    public void onExpire(Card target) {}
}
