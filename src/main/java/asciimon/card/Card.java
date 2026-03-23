package src.main.java.asciimon.card;

import java.util.ArrayList;
import java.util.List;

import src.main.java.asciimon.move.Move;
import src.main.java.asciimon.type.Type;

public abstract class Card {
    protected String name;
    protected Integer level;
    protected Integer health;
    protected Integer maxHealth;
    protected Integer attack;
    protected Integer defense;
    protected Integer speed;
    protected Type type;

    protected List<Move> moves;

    public Card(String name, Integer health, Integer attack, Integer defense, Integer speed) {
        this.name = name;
        this.level = 1;
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.moves = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Integer getHealth() {
        return this.health;
    }

    public Integer getAttack() {
        return this.attack;
    }

    public Integer getDefense() {
        return this.attack;
    }

    public Integer getSpeed() {
        return this.attack;
    }

    public Type getType() {
        return this.type;
    }
}
