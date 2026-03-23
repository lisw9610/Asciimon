package src.main.java.asciimon.move;

public class Attack extends Move{
    private Integer attackDamage;

    public Attack(String moveName, String moveType, Integer attackDamage) {
        super(moveName, moveType);
        this.attackDamage = attackDamage;
    }

}
