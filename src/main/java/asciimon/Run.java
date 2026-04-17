package asciimon;

import asciimon.card.Card;
import asciimon.card.CardFactory;

public class Run {
    public static void main(String[] args) {
        Card card = CardFactory.createCard("slimey", "fire_slime");

        card.gainExperience(300);

        System.out.println(card.renderCard());
    }
}
