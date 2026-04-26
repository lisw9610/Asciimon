package asciimon.status_effects;

import asciimon.card.Card;

public interface StatusEffect {

    void onApply(Card target);

    void onTurn(Card target);

    void onExpire(Card target); 
}