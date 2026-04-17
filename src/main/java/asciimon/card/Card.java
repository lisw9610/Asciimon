package asciimon.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asciimon.move.Move;
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
    private List<Integer> statModifiers = Arrays.asList(0, 0, 0, 0); //Stores the modifiers applied by buff and debuff moves to each stat in the order (health, attack, defense, speed)
    private Integer healthPoints;
    private final Type type;

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

    public void reset() {
        this.healthPoints = getMaxHealthPoints();
        this.statModifiers.replaceAll(_ -> 0);
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
        return this.requiredExperience;
    }

    private void updateExperienceForNextLevel() {
        this.requiredExperience = this.level * this.requiredExperienceIncreaseOnLevelUp * this.requiredExperienceIncreaseModifier; //experience for next level calculated by level*base EXP*EXP modifier
    }

    public void gainExperience(Integer gainedExp) {
        this.experience += gainedExp;
        this.doLevelUp();
    }

    public Integer getMaxHealthPoints() {
        return this.baseStats.get(0) * 10;
    }

    public Integer getHealthPoints() {
        return this.healthPoints;
    }

    public Integer getHealth() {
        return this.baseStats.get(0);
    }

    public Integer getBaseAttack() {
        return this.baseStats.get(1);
    }

    public Integer getModifiedAttack() {
        return this.getBaseAttack() + this.statModifiers.get(1);
    }

    public Integer getBaseDefense() {
        return this.baseStats.get(2);
    }

    public Integer getModifiedDefense() {
        return this.getBaseDefense() + this.statModifiers.get(2);
    }

    public Integer getBaseSpeed() {
        return this.baseStats.get(3);
    }

    public Integer getModifiedSpeed() {
        return this.getBaseSpeed() + this.statModifiers.get(3);
    }

    public Type getType() {
        return this.type;
    }

    public Integer getMaxMoveCount() {
        return this.maximumMoveCount;
    }

    public boolean canLevelUp() {
        if(this.experience >= this.getExperienceForNextLevel() && this.level < this.maxLevel) {
            return true;
        }
        return false;
    }

    private void doLevelUp() {
        while(canLevelUp()) {
            this.experience = this.experience - this.getExperienceForNextLevel();
            this.level += 1;

            this.updateExperienceForNextLevel();

            this.baseStats.set(0, getHealth() + this.statIncreaseOnLevelUp.get(0));
            this.baseStats.set(1, getBaseAttack() + this.statIncreaseOnLevelUp.get(1));
            this.baseStats.set(2, getBaseDefense() + this.statIncreaseOnLevelUp.get(2));
            this.baseStats.set(3, getBaseSpeed() + this.statIncreaseOnLevelUp.get(3));
            this.healthPoints = this.getMaxHealthPoints();

        }
    }

    public void takeDamage(Integer takenDamage) {
        this.healthPoints -= takenDamage;
        if(this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    public void healDamage(Integer healAmount) {
        this.healthPoints += healAmount;
        if(this.healthPoints > getMaxHealthPoints()) {
            this.healthPoints = getMaxHealthPoints();
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

    public void updateAttackModifier(Integer modifier) {
        this.statModifiers.set(1, this.statModifiers.get(1) + modifier);
    }

    public void updateDefenseModifier(Integer modifier) {
        this.statModifiers.set(2, this.statModifiers.get(2) + modifier);
    }

    public void updateSpeedModifier(Integer modifier) {
        this.statModifiers.set(3, this.statModifiers.get(3) + modifier);
    }

    private void handleMoveTargetingSelf(String impactedStat, Integer statImpact) {
        switch(impactedStat.toLowerCase()) {
            case "health":
                healDamage(statImpact);
                break;
            case "attack":
                updateAttackModifier(statImpact);
                break;
            case "defense":
                updateDefenseModifier(statImpact);
                break;
            case "speed":
                updateSpeedModifier(statImpact);
                break;
            default:
                break;
        }
    }

    private void handleMoveTargetingEnemy(Card enemyCard, String impactedStat, Integer statImpact) {
        switch(impactedStat.toLowerCase()) {
            case "health":
                enemyCard.takeDamage(statImpact);
                break;
            case "attack":
                enemyCard.updateAttackModifier(statImpact);
                break;
            case "defense":
                enemyCard.updateDefenseModifier(statImpact);
                break;
            case "speed":
                enemyCard.updateSpeedModifier(statImpact);
                break;
            default:
                break;
        }
    }

    private void turnPasses() {
        //applies status effects active on card
        //decrements status effects by 1 turn
    }

    public void doTurn(Integer moveIndex, Card enemyCard) {
        Move playedMove = moves.get(moveIndex);
        String impactedStat = playedMove.getImpactedStat();
        Integer statImpact = playedMove.getStatImpact();
        boolean targetsEnemy = playedMove.targetsEnemy();
        
        if(targetsEnemy) {
            handleMoveTargetingEnemy(enemyCard, impactedStat, statImpact);
        } else {
            handleMoveTargetingSelf(impactedStat, statImpact);
        }

        turnPasses();
    }

    public boolean isDead() {
        if (this.healthPoints == 0) {
            return true;
        }
        return false;
    }

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
