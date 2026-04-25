package asciimon.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asciimon.move.Move;
import asciimon.move.StatusEffectTracker;
import asciimon.type.Type;

public abstract class Card {
    private String name;
    private Integer level;
    private final Integer maxLevel = 50;
    private Integer experience;
    private Integer requiredExperience;
    private final Integer requiredExperienceIncreaseModifier; //A unique value that provides a modifier to the required experience based on the card
    private final Integer requiredExperienceIncreaseOnLevelUp = 100; //Default value the total required experience increases by for each level up for all cards.
    private final List<Integer> statIncreaseOnLevelUp; //Stores the amount each base card stat increases in the order (health, attack, defense, speed)
    private final List<String> statNames = Arrays.asList("Health", "Attack", "Defense", "Speed");
    private List<Integer> baseStats; //Stores the cards stats in the order (health, attack, defense, speed)
    private List<Integer> statModifiers = new ArrayList<>(Arrays.asList(0, 0, 0, 0));; //Stores the modifiers applied by buff and debuff moves to each stat in the order (health, attack, defense, speed)
    private Integer healthPoints;
    private final Type type;

    private List<StatusEffectTracker> statusEffects = new ArrayList<>();

    private final Integer maximumMoveCount = 4;
    private Integer currentMoveCount = 0;
    private List<Move> moves = new ArrayList<>();

    private final Integer CARD_WIDTH = 35;
    private final String asciiArt;

    public Card(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp, Type type) {
        this.name = name;
        this.asciiArt = asciiArt;
        this.level = 1;
        this.experience = 0;
        this.requiredExperienceIncreaseModifier = experienceModifier;
        this.requiredExperience = this.level * this.requiredExperienceIncreaseOnLevelUp * this.requiredExperienceIncreaseModifier;
        this.statIncreaseOnLevelUp = statIncreaseOnLevelUp;
        this.healthPoints = baseStats.get(0) * 10;
        this.baseStats = baseStats;
        this.type = type;
    }

    public void fullRestore() {
        this.healthPoints = getMaxHealthPoints();
        this.statModifiers.replaceAll(_ -> 0);
        this.statusEffects.clear();
    }

    //==========================================GETTERS==========================================

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
        return this.requiredExperience;
    }

    private Integer getStat(StatType stat) {
        return this.baseStats.get(stat.ordinal());
    }

    public Integer getModifiedStat(StatType stat) {
        return getStat(stat) + this.statModifiers.get(stat.ordinal());
    }

    private Integer getStatIncreaseOnLevelUp(StatType stat) {
        return this.statIncreaseOnLevelUp.get(stat.ordinal());
    }

    public Integer getMaxHealthPoints() {
        return getStat(StatType.HEALTH) * 10;
    }

    public Integer getHealthPoints() {
        return this.healthPoints;
    }

    public Type getType() {
        return this.type;
    }

    public Integer getMaxMoveCount() {
        return this.maximumMoveCount;
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public boolean isDead() {
        return this.healthPoints <= 0;
    }

    private Double getTypeMultiplier(Card enemy) {
        return this.type.getEffectiveness(enemy.getType());
    }

    //============================================EXPERIENCE===============================================

    private void updateExperienceForNextLevel() {
        this.requiredExperience = this.level * this.requiredExperienceIncreaseOnLevelUp * this.requiredExperienceIncreaseModifier; //experience for next level calculated by level*base EXP*EXP modifier
    }

    public void gainExperience(Integer gainedExp) {
        this.experience += gainedExp;
        this.doLevelUp();
    }


    //============================================STAT MODIFIERS===============================================

    private int clampModifier(int value) {
        Integer min = -10;
        Integer max = 10;
        return Math.max(min, Math.min(max, value));
    }

    public void updateModifier(StatType stat, int value) {
        int index = stat.ordinal();
        int newValue = statModifiers.get(index) + value;
        statModifiers.set(index, clampModifier(newValue));
    }


    //============================================STATUS EFFECTS===============================================

    public void addStatusEffect(StatusEffectTracker effect) {
        this.statusEffects.add(effect);
    }

    private void processStatusEffects() {
        List<StatusEffectTracker> expired = new ArrayList<>();

        for (StatusEffectTracker effect : statusEffects) {
            effect.apply(this);
            effect.turnPasses();

            if (effect.hasExpired()) {
                effect.getStatusEffect().onExpire(this);
                expired.add(effect);
            }
        }

        statusEffects.removeAll(expired);
    }


    //============================================LEVELING===============================================

    public boolean canLevelUp() {
        if(this.experience >= this.getExperienceForNextLevel() && this.level < this.maxLevel) {
            return true;
        }
        return false;
    }

    private void updateBaseStatByLevelUp(StatType stat) {
        this.baseStats.set(stat.ordinal(), getStat(stat) + getStatIncreaseOnLevelUp(stat));
    }

    private void doLevelUp() {
        while(canLevelUp()) {
            this.experience = this.experience - this.getExperienceForNextLevel();
            this.level += 1;

            this.updateExperienceForNextLevel();

            updateBaseStatByLevelUp(StatType.HEALTH);
            updateBaseStatByLevelUp(StatType.ATTACK);
            updateBaseStatByLevelUp(StatType.DEFENSE);
            updateBaseStatByLevelUp(StatType.SPEED);

            this.healthPoints = this.getMaxHealthPoints();

        }
    }


    //============================================HEALTH POINT MODIFIERS===============================================

    private void clampHealth() {
        this.healthPoints = Math.max(0, Math.min(this.healthPoints, getMaxHealthPoints()));
    }

    public void takeDamage(Integer takenDamage) {
        this.healthPoints -= takenDamage;
        clampHealth();
    }

    public void healDamage(Integer healAmount) {
        this.healthPoints += healAmount;
        clampHealth();
    }

    public Integer calculateDamage(Card enemy, Integer basePower) {
        Integer attack = this.getModifiedStat(StatType.ATTACK);
        Integer defense = enemy.getModifiedStat(StatType.DEFENSE);

        Integer multiplier = (int) Math.round(attack / Math.max(1, defense) * getTypeMultiplier(enemy));

        Integer damage = basePower * multiplier;

        return Math.max(1, damage);
    }


    //============================================MOVE HANDLING===============================================

    public void learnMove(Move move) {
        if (moves.size() >= maximumMoveCount) {
            throw new IllegalStateException("Move limit reached.");
        }
        moves.add(move);
    }

    public void forgetMove(Move move) {
        if (moves.remove(move)) {
            currentMoveCount -= 1;
        }
    }

    public void replaceMove(Integer index, Move newMove) {
        if (index < 0 || index >= maximumMoveCount) {
            throw new IllegalArgumentException("Invalid move slot.");
        }

        if (index < moves.size()) {
            moves.set(index, newMove);
        } else {
            learnMove(newMove);
        }
    }


    //============================================TURN HANDLING===============================================

    private void turnPasses() {
        processStatusEffects();
    }


    public void doTurn(Integer moveIndex, Card enemyCard) {
        if (moveIndex < 0 || moveIndex >= moves.size()) {
            throw new IllegalArgumentException("Invalid move index.");
        }

        Move playedMove = moves.get(moveIndex);

        if (playedMove.targetsEnemy()) {
            playedMove.executeMove(this, enemyCard);
        } else {
            playedMove.executeMove(this, this);
        }

        turnPasses();
    }
    




    //============================================DISPLAYING CARD===============================================

    @Override
    public String toString() {
        return this.name + " - lv." + this.level + "(" + this.experience + "/" + this.getExperienceForNextLevel() + ")";
    }

    private List<String> renderHeader() {
        List<String> header = new ArrayList<>();

        //builds header (name, level, HP)
        header.add(String.format("%-" + CARD_WIDTH + "s", this.toString()));
        header.add(String.format("%-" + CARD_WIDTH + "s", "Type: " + (this.getType() == null ? "—" : this.getType())));
        header.add(String.format("%-" + CARD_WIDTH + "s", ""));
        header.add(String.format("%-" + CARD_WIDTH + "s", "HP: " + this.getHealthPoints() + " / " + this.getMaxHealthPoints()));
        header.add(String.format("%-" + CARD_WIDTH + "s", ""));

        return header;
    }


    private List<String> renderAsciiArt() {
        //splits ascii art into lines
        String[] artLines = asciiArt.split("\n");
        List<String> art = new ArrayList<>();
        
        //finds the longest line to determine centering
        int maxLength = 0;
        for (String line : artLines) {
            line = line.replaceAll("\\r", "");
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        
        for (String line : artLines) {
            line = line.replaceAll("\\r", "");
            
            if (line.length() > CARD_WIDTH) {
                art.add(line.substring(0, CARD_WIDTH));
            } else {
                //calulates padding based on the longest line
                int totalPadding = CARD_WIDTH - maxLength;
                int leftPadding = totalPadding / 2;
                
                String centeredLine = " ".repeat(leftPadding) + line + " ".repeat(CARD_WIDTH - leftPadding - line.length());
                art.add(centeredLine);
            }
        }
        
        return art;
    }

    private List<String> renderInfo() {
        List<String> info = new ArrayList<>();
        
        //adds stats
        for (Integer i = 0; i < this.statNames.size(); i++) {
            Integer base = this.baseStats.get(i);
            Integer mod = this.statModifiers.get(i);
            String modStr = (mod == 0) ? "" : (mod > 0 ? " (+" + mod + ")" : " (-" + mod + ")");
            info.add(String.format("%-10s: %-6s%s", this.statNames.get(i), base, modStr));
        }


        //adds moves (up to maximumMoveCount)
        info.add("");
        info.add("Moves:");

        for (int i = 0; i < maximumMoveCount; i++) {
            String strMove;
            if (i < moves.size()) {
                Move move = moves.get(i);
                strMove = (i + 1) + ". " + move.getMoveName();
            } else {
                strMove = (i + 1) + ". ---";
            }
            info.add(String.format("%-" + CARD_WIDTH + "s", strMove));
        }


        return info;
    }

    public String renderCard() {
        List<String> header = this.renderHeader();
        List<String> art = this.renderAsciiArt();
        List<String> info = this.renderInfo();

        int totalWidth = CARD_WIDTH + 2;
        
        StringBuilder out = new StringBuilder();
        String border = "+" + "-".repeat(totalWidth) + "+\n";
        
        out.append(border);

        //header section
        for (String line : header) {
            out.append("|");
            out.append(String.format("%-" + totalWidth + "s", line));
            out.append("|\n");
        }

        out.append(border);
        
        //ASCII art section
        for (String line : art) {
            out.append("|");
            out.append(line);
            out.append(String.format("%" + (totalWidth - line.length()) + "s", ""));
            out.append("|\n");
        }
        
        out.append(border);
        
        //add info section (stats and moves)
        for (String line : info) {
            out.append("|");
            out.append(String.format("%-" + totalWidth + "s", line));
            out.append("|\n");
        }
        
        out.append(border);
        
        return out.toString();
    }

}
