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
    private Integer health;
    private Integer maxHealth;
    private Integer attack;
    private Integer defense;
    private Integer speed;
    private Type type;

    private final Integer maximumMoveCount = 4;
    private List<Move> moves = new ArrayList<>();

    public Card(String name, Integer health, Integer attack, Integer defense, Integer speed, List<Integer> nextLevelExperienceCounts, Type type) {
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.nextLevelExperienceCounts = nextLevelExperienceCounts;
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
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

    public Integer getMaxHealth() {
        return this.maxHealth;
    }

    public Integer getHealth() {
        return this.health;
    }

    public Integer getAttack() {
        return this.attack;
    }

    public Integer getDefense() {
        return this.defense;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public Type getType() {
        return this.type;
    }

    public Integer getMaxMoveCount() {
        return this.maximumMoveCount;
    }

    public void learnMove(Move move) {
        moves.add(move);
    }

    public void forgetMove() {
        moves.removeLast();
    }
}
