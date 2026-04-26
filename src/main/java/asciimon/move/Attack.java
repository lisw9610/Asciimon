package asciimon.move;

import asciimon.card.Card;
import asciimon.card.StatType;
import asciimon.type.Type;

public class Attack extends Move {
    public Attack(String name, Type type, int power) {
        super(name, type, null, power, 0, true);
    }

    @Override
    public void executeMove(Card user, Card target) {
        target.takeDamage(getStatImpact());
    }
}