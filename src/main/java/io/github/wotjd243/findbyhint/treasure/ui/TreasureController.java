package io.github.wotjd243.findbyhint.treasure.ui;


import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author DoYoung
 *
 */

@Controller
public class TreasureController {

    private final TreasureService treasureService;

    public TreasureController(TreasureService treasureService) {
        this.treasureService = treasureService;
    }

    @GetMapping("/treasure/save")
    public String  saveTreasure(Treasure treasure){
        treasureService.save(treasure);
        return "/index";
    }
}
