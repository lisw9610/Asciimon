package asciimon.move;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.type.Type;

public class Attack extends Move{
    public Attack(String name, Type type, int power) {
        super(name, type, null, power, 0, true);
    }

    @Override
    public void executeMove(Card user, Card target) {
        int damage = user.calculateDamage(target, getStatImpact());

        double typeMultiplier = getType().getEffectiveness(target.getType());
        damage = (int) Math.round(damage * typeMultiplier);

        target.takeDamage(damage);
    }

}
