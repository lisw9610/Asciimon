package asciimon.controller;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asciimon.Deck;
import asciimon.Match;
import asciimon.Player;
import asciimon.TurnAction;
import asciimon.card.Card;
import asciimon.card.CardFactory;
import jakarta.servlet.http.HttpSession;

@Controller
public class GameController {

    private static final String MATCH_KEY = "match";
    private static final String PLAYER_KEY = "player";
    private static final String ENEMY_KEY = "enemy";
    private static final String PLAYER_NAME_KEY = "playerName";

    private final Random random = new Random();

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/select")
    public String select(
            @RequestParam(required = false) String playerName,
            HttpSession session,
            Model model
    ) {
        String actualName = normalizePlayerName(playerName, session);
        session.setAttribute(PLAYER_NAME_KEY, actualName);
        model.addAttribute("playerName", actualName);
        return "select";
    }

    @PostMapping("/battle/start")
    public String startBattle(
            @RequestParam String cardType,
            HttpSession session
    ) {
        String playerName = normalizePlayerName(null, session);

        Card playerCard = CardFactory.createCard(playerName + "'s Card", cardType);
        Card enemyCard = CardFactory.createCard("Enemy Card", randomEnemyType(cardType));

        Deck playerDeck = Deck.DeckBuilder.getInstance()
                .setMaxDeckSize(1)
                .addCard(playerCard)
                .createDeck();

        Deck enemyDeck = Deck.DeckBuilder.getInstance()
                .setMaxDeckSize(1)
                .addCard(enemyCard)
                .createDeck();

        Player player = new Player(playerName, playerDeck);
        Player enemy = new Player("Enemy", enemyDeck);

        Match match = new Match(player, enemy);
        match.startBattle();

        session.setAttribute(MATCH_KEY, match);
        session.setAttribute(PLAYER_KEY, player);
        session.setAttribute(ENEMY_KEY, enemy);

        return "redirect:/battle";
    }

    @GetMapping("/battle")
    public String battle(HttpSession session, Model model) {
        Match match = (Match) session.getAttribute(MATCH_KEY);
        Player player = (Player) session.getAttribute(PLAYER_KEY);
        Player enemy = (Player) session.getAttribute(ENEMY_KEY);

        if (match == null || player == null || enemy == null) {
            return "redirect:/home";
        }

        if (match.isBattleOver()) {
            return "redirect:/result";
        }

        Card playerCard = player.getActiveCard();
        Card enemyCard = enemy.getActiveCard();

        model.addAttribute("playerName", session.getAttribute(PLAYER_NAME_KEY));
        model.addAttribute("playerCardName", playerCard.getName());
        model.addAttribute("enemyCardName", enemyCard.getName());
        model.addAttribute("playerType", typeLabel(playerCard));
        model.addAttribute("enemyType", typeLabel(enemyCard));
        model.addAttribute("playerHp", playerCard.getHealthPoints());
        model.addAttribute("playerMaxHp", playerCard.getMaxHealthPoints());
        model.addAttribute("enemyHp", enemyCard.getHealthPoints());
        model.addAttribute("enemyMaxHp", enemyCard.getMaxHealthPoints());
        model.addAttribute("playerLevel", playerCard.getLevel());
        model.addAttribute("enemyLevel", enemyCard.getLevel());
        model.addAttribute("playerAscii", playerCard.renderCard());
        model.addAttribute("enemyAscii", enemyCard.renderCard());
        model.addAttribute("move0Label", moveLabel(playerCard, 0));
        model.addAttribute("move1Label", moveLabel(playerCard, 1));
        model.addAttribute("move2Label", moveLabel(playerCard, 2));
        model.addAttribute("move3Label", moveLabel(playerCard, 3));
        model.addAttribute("battleLogText", battleLogText(match.getBattleLog()));

        return "battle";
    }

    @PostMapping("/battle/move")
    public String playMove(
            @RequestParam int moveIndex,
            HttpSession session
    ) {
        Match match = (Match) session.getAttribute(MATCH_KEY);
        Player player = (Player) session.getAttribute(PLAYER_KEY);
        Player enemy = (Player) session.getAttribute(ENEMY_KEY);

        if (match == null || player == null || enemy == null) {
            return "redirect:/home";
        }

        if (match.isBattleOver()) {
            return "redirect:/result";
        }

        Card playerCard = player.getActiveCard();
        Card enemyCard = enemy.getActiveCard();

        if (!isValidMove(playerCard, moveIndex)) {
            return "redirect:/battle";
        }

        int enemyMoveIndex = randomValidMoveIndex(enemyCard);

        match.playTurn(
                TurnAction.move(player, moveIndex),
                TurnAction.move(enemy, enemyMoveIndex)
        );

        if (match.isBattleOver()) {
            Player winner = match.getWinner();
            if (winner == player) {
                player.addWin();
                enemy.addLoss();
            } else if (winner == enemy) {
                enemy.addWin();
                player.addLoss();
            }
            return "redirect:/result";
        }

        return "redirect:/battle";
    }

    @GetMapping("/result")
    public String result(HttpSession session, Model model) {
        Match match = (Match) session.getAttribute(MATCH_KEY);
        Player player = (Player) session.getAttribute(PLAYER_KEY);
        Player enemy = (Player) session.getAttribute(ENEMY_KEY);

        if (match == null || player == null || enemy == null) {
            return "redirect:/home";
        }

        String winner = match.getWinner() == null ? "No one" : match.getWinner().getName();

        model.addAttribute("winner", winner);
        model.addAttribute("playerName", session.getAttribute(PLAYER_NAME_KEY));
        model.addAttribute("battleLogText", battleLogText(match.getBattleLog()));

        return "result";
    }

    @PostMapping("/reset")
    public String reset(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }

    private String normalizePlayerName(String incomingName, HttpSession session) {
        if (incomingName != null && !incomingName.isBlank()) {
            return incomingName;
        }

        Object saved = session.getAttribute(PLAYER_NAME_KEY);
        if (saved instanceof String savedName && !savedName.isBlank()) {
            return savedName;
        }

        return "Player 1";
    }

    private String randomEnemyType(String playerChoice) {
        List<String> all = List.of("fire_slime", "water_slime", "electric_slime", "plant_slime");
        List<String> filtered = all.stream()
                .filter(type -> !type.equalsIgnoreCase(playerChoice))
                .toList();
        return filtered.get(random.nextInt(filtered.size()));
    }

    private boolean isValidMove(Card card, int moveIndex) {
        return moveIndex >= 0
                && moveIndex < card.getMoves().size()
                && card.getMoves().get(moveIndex) != null;
    }

    private int randomValidMoveIndex(Card card) {
        List<Integer> valid = List.of(0, 1, 2, 3).stream()
                .filter(i -> isValidMove(card, i))
                .toList();

        if (valid.isEmpty()) {
            return 0;
        }

        return valid.get(random.nextInt(valid.size()));
    }

    private String moveLabel(Card card, int index) {
        if (isValidMove(card, index)) {
            String moveName = card.getMoves().get(index).getMoveName();
            return Character.toUpperCase(moveName.charAt(0)) + moveName.substring(1);
        }
        return "Unavailable";
    }

    private String typeLabel(Card card) {
        String name = card.getType().getClass().getSimpleName();
        return name.replace("Type", "").toUpperCase();
    }

    private String battleLogText(List<String> entries) {
        if (entries == null || entries.isEmpty()) {
            return "No turns played yet.";
        }
        return String.join("\n", entries);
    }
}