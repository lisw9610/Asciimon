package src.main.java.asciimon.card;

import java.util.ArrayList;
import java.util.List;

import src.main.java.asciimon.move.Move;
import src.main.java.asciimon.type.Type;

public abstract class Card {
    private String name;
    private Integer level;
    private Integer experience;
    private List<Integer> nextLevelExperienceCounts; //List of experience needed to level up for each level. Index 0 for level 2, index 1 for level 3, and so on.
    private final List<Integer> statIncreaseOnLevelUp; //Stores the amount each base card stat increases in the order (health, attack, defense, speed)
    private Integer health;
    private Integer maxHealth;
    private Integer attackModifier = 0;
    private Integer baseAttack;
    private Integer defenseModifier = 0;
    private Integer baseDefense;
    private Integer speedModifier = 0;
    private Integer baseSpeed;
    private Type type;

    private final Integer maximumMoveCount = 4;
    private Integer currentMoveCount = 0;
    private List<Move> moves = new ArrayList<>();



    public Card(String name, Integer health, Integer attack, Integer defense, Integer speed, List<Integer> nextLevelExperienceCounts, List<Integer> statIncreaseOnLevelUp, Type type) {
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.nextLevelExperienceCounts = nextLevelExperienceCounts;
        this.statIncreaseOnLevelUp = statIncreaseOnLevelUp;
        this.maxHealth = health;
        this.health = health;
        this.baseAttack = attack;
        this.baseDefense = defense;
        this.baseSpeed = speed;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Integer getCurrentExp() {
        return this.experience;
    }

    public Integer getExperienceForNextLevel() {
        return this.nextLevelExperienceCounts.get(level - 1);
    }

    public void gainExperience(Integer gainedExp) {
        this.experience += gainedExp;
    }

    public Integer getMaxHealth() {
        return this.maxHealth;
    }

    public Integer getHealth() {
        return this.health;
    }

    public void takeDamage(Integer takenDamage) {
        this.health -= takenDamage;
        if(this.health < 0) {
            this.health = 0;
        }
    }

    public void healDamage(Integer healAmount) {
        this.health += healAmount;
        if(this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    public Integer getBaseAttack() {
        return this.baseAttack;
    }

    public Integer getModifiedAttack(Integer modifier) {
        return this.baseAttack + this.attackModifier;
    }

    public Integer getBaseDefense() {
        return this.baseDefense;
    }

    public Integer getModifiedDefense(Integer modifier) {
        return this.baseDefense + this.defenseModifier;
    }

    public Integer getBaseSpeed() {
        return this.baseSpeed;
    }

    public Integer getModifiedSpeed(Integer modifier) {
        return this.baseSpeed + this.speedModifier;
    }

    public Type getType() {
        return this.type;
    }

    public Integer getMaxMoveCount() {
        return this.maximumMoveCount;
    }

    public boolean canLevelUp() {
        if(this.experience >= this.getExperienceForNextLevel()) {
            return true;
        }
        return false;
    }

    public void doLevelUp() {
        if(canLevelUp()) {
            this.level += 1;
            this.experience = this.experience - this.getExperienceForNextLevel();
            this.maxHealth += this.statIncreaseOnLevelUp.get(0); 
            this.baseAttack += this.statIncreaseOnLevelUp.get(1);
            this.baseDefense += this.statIncreaseOnLevelUp.get(2);
            this.baseSpeed += this.statIncreaseOnLevelUp.get(3);
        }
    }

    public void learnMove(Move move) {
        if(currentMoveCount < maximumMoveCount) {
            moves.add(move);
            currentMoveCount += 1;
        }
    }

    public void forgetMove(Move move) {
        moves.remove(move);
        currentMoveCount -= 1;
    }

    public void pickAndApplyMove(Integer moveIndex, Card enemyCard) {
        Move playedMove = moves.get(moveIndex);
        String impactedStat = playedMove.getImpactedStat();
        Integer statImpact = playedMove.getStatImpact();
        boolean targetsEnemy = playedMove.targetsEnemy();
        
        if(targetsEnemy) {

        } else {

        }

        switch(impactedStat.toLowerCase()) {
            case "health":
                healDamage(statImpact);
                break;
            case "attack":
                this.attackModifier += statImpact;
                break;
            case "defense":
                this.defenseModifier += statImpact;
                break;
            case "speed":
                this.speedModifier += statImpact;
                break;
            default:
                break;
        }
    }

    public boolean isDead() {
        if (this.health == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name + " - lv." + this.level + "(" + this.experience + "/" + this.getExperienceForNextLevel() + ")\n";
    }

}
