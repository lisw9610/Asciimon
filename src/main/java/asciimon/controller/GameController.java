package asciimon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/select")
    public String select(Model model) {
        model.addAttribute("cards", List.of(
                "Fire Slime",
                "Water Slime",
                "Electric Slime",
                "Plant Slime"
        ));
        return "select";
    }

    @GetMapping("/battle")
    public String battle(
            @RequestParam(defaultValue = "Fire Slime") String card,
            Model model
    ) {
        model.addAttribute("selectedCard", card);
        model.addAttribute("enemyCard", "Wild Slime");
        model.addAttribute("moves", List.of(
                "Basic Attack",
                "Special Move",
                "Buff",
                "Heal"
        ));
        return "battle";
    }

    @GetMapping("/result")
    public String result(
            @RequestParam(defaultValue = "You") String winner,
            Model model
    ) {
        model.addAttribute("winner", winner);
        return "result";
    }
}
