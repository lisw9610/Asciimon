package src.main.java.asciimon.move;

import src.main.java.asciimon.type.Type;

public class Attack extends Move{
    private Integer attackDamage;

    public Attack(String moveName, Type moveType, Integer attackDamage) {
        super(moveName, moveType);
        this.attackDamage = attackDamage;
    }

    public Integer getAttackDamage() {
        return this.attackDamage;
    }

}
