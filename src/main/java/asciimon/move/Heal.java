package asciimon.move;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.type.Type;

public class Heal extends Move{
    public Heal(String name, Type type, int amount) {
        super(name, type, StatType.HEALTH, amount, 0, false);
    }

    @Override
    public void executeMove(Card user, Card target) {
        user.healDamage(getStatImpact());
    }

}
