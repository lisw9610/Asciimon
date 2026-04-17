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
    private Integer health;
    private final Type type;

    private final Integer maximumMoveCount = 4;
    private Integer currentMoveCount = 0;
    private List<Move> moves = new ArrayList<>();


    private final Integer ART_WIDTH = 25;
    private final Integer INFO_WIDTH = 35;
    private final String asciiArt;

    public Card(String name, String asciiArt, List<Integer> baseStats, Integer experienceModifier, List<Integer> statIncreaseOnLevelUp, Type type) {
        this.name = name;
        this.asciiArt = asciiArt;
        this.level = 1;
        this.experience = 0;
        this.requiredExperienceIncreaseModifier = experienceModifier;
        this.requiredExperience = this.requiredExperienceIncreaseOnLevelUp * this.requiredExperienceIncreaseModifier;
        this.statIncreaseOnLevelUp = statIncreaseOnLevelUp;
        this.health = baseStats.get(0);
        this.baseStats = baseStats;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getAsciiArt() {
        return this.asciiArt;
    }

    public String getCardGraphic() {
        String cardGraphic = "";

        return String.format(cardGraphic);
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
        this.requiredExperience = this.requiredExperience + (this.requiredExperienceIncreaseOnLevelUp * this.requiredExperienceIncreaseModifier);
    }

    public void gainExperience(Integer gainedExp) {
        this.experience += gainedExp;
        this.doLevelUp();
    }

    public Integer getMaxHealth() {
        return this.baseStats.get(0);
    }

    public Integer getHealth() {
        return this.health;
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

    private boolean canLevelUp() {
        if(this.experience >= this.getExperienceForNextLevel() && this.level < this.maxLevel) {
            return true;
        }
        return false;
    }

    private void doLevelUp() {
        if(canLevelUp()) {
            this.experience = this.experience - this.getExperienceForNextLevel();
            
            this.updateExperienceForNextLevel();

            this.level += 1;
            this.baseStats.set(0, getMaxHealth() + this.statIncreaseOnLevelUp.get(0));
            this.baseStats.set(1, getBaseAttack() + this.statIncreaseOnLevelUp.get(1));
            this.baseStats.set(2, getBaseDefense() + this.statIncreaseOnLevelUp.get(2));
            this.baseStats.set(3, getBaseSpeed() + this.statIncreaseOnLevelUp.get(3));

        }
    }

    public void takeDamage(Integer takenDamage) {
        this.health -= takenDamage;
        if(this.health < 0) {
            this.health = 0;
        }
    }

    public void healDamage(Integer healAmount) {
        this.health += healAmount;
        if(this.health > getMaxHealth()) {
            this.health = getMaxHealth();
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

    private List<String> renderAsciiArt() {
        //splits ascii art into lines and truncate to ART_WIDTH
        String[] artLines = asciiArt.split("\n");
        List<String> art = new ArrayList<>();
        for (String line : artLines) {
            if (line.length() > ART_WIDTH) {
                art.add(line.substring(0, ART_WIDTH));
            } else {
                art.add(String.format("%-" + ART_WIDTH + "s", line));
            }
        }

        return art;
    }

    private List<String> renderInfoColumns() {
        List<String> info = new ArrayList<>();

        //builds info column lines (name, level, HP, stats, moves)
        info.add(String.format("%-" + INFO_WIDTH + "s", this.toString()));
        info.add(String.format("%-" + INFO_WIDTH + "s", "Type: " + (this.getType() == null ? "—" : this.getType())));
        info.add(String.format("%-" + INFO_WIDTH + "s", "HP: " + this.getHealth() + " / " + this.getMaxHealth()));
        info.add(String.format("%-" + INFO_WIDTH + "s", ""));


        
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
            info.add(String.format("%-" + INFO_WIDTH + "s", strMove));
        }


        return info;
    }

    public String renderCard() {
        String spacer = "  "; // space between columns

        List<String> art = this.renderAsciiArt();

        List<String> info = this.renderInfoColumns();

        //makes both columns the same number of lines
        int maxLines = Math.max(art.size(), info.size());
        while (art.size() < maxLines) {
            art.add(String.format("%-" + ART_WIDTH + "s", ""));
        }

        while (info.size() < maxLines) {
            info.add(String.format("%-" + INFO_WIDTH + "s", ""));
        }

        //final lines
        StringBuilder out = new StringBuilder();
        String border = "+" + "-".repeat(ART_WIDTH + INFO_WIDTH + spacer.length()) + "+\n";
        out.append(border);
        
        for (int i = 0; i < maxLines; i++) {
            out.append("|");
            out.append(art.get(i));
            out.append(spacer);
            out.append(info.get(i));
            out.append("|\n");
        }

        out.append(border);

        return out.toString();
    }

}
