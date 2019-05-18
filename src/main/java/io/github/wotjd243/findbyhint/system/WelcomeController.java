package io.github.wotjd243.findbyhint.system;

import io.github.wotjd243.findbyhint.hunter.application.HunterService;
import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    private HunterService hunterService;

    public WelcomeController(HunterService hunterService) {
        this.hunterService = hunterService;
    }

    @GetMapping("/")
    public String welcome(Model model) {

        Hunter hunter = hunterService.findById("testHunter");

        int hunterBullet =  hunter.getHunterPointBullet().getHunterBullet().getHunterBullet();
        int hunterPoint = hunter.getHunterPointBullet().getHunterPoint().getHunterPoint();

        model.addAttribute("hunterBullet", hunterBullet);
        model.addAttribute("hunterPoint", hunterPoint);

        return "/index";
    }

}
