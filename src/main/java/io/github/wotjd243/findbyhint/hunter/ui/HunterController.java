package io.github.wotjd243.findbyhint.hunter.ui;

import io.github.wotjd243.findbyhint.hunter.application.HunterService;
import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.hunter.domain.HunterDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HunterController {

    private HunterService hunterService;

    public HunterController(HunterService hunterService) {
        this.hunterService = hunterService;
    }

    @GetMapping("/login")
    public String naverLogin(Model model) {

        return "/login";
    }

    @PostMapping("/hunterJoin")
    public String hunterJoin(HunterDto hunterDto) {

        Hunter hunter = hunterService.getHunter(hunterDto);

        hunterService.hunterCreate(hunter);

        return "redirect:/";

    }

    @GetMapping("/hunterCheck")
    public String hunterCheck(String hunterId) {

        Hunter hunter = hunterService.findById(hunterId);

        System.out.println("Hunter : " + hunter);

        return "redirect:/";


    }

    @PostMapping("/buyOneBullet")
    public String buyOneBullet(HunterDto hunterDto) {
        Hunter hunter = hunterService.getHunter(hunterDto);
        hunter.buyOneBullet();

        // 추후 화면 개발 후 변경
        return "redirect:/login";
    }

}
