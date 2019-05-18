package io.github.wotjd243.findbyhint.hint.ui;

import io.github.wotjd243.findbyhint.hint.application.HintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/hint/")
public class HintController {

    private HintService hintService;
    private final String viewName = "mapTest";

    public HintController(HintService hintService) {
        this.hintService = hintService;
    }

    @GetMapping("/showTargetPoints")
    public String showTargetPoints(Model model,String hunterId){
        hunterId = "testHunter";
        model.addAttribute("result",hintService.getTargetPoints(hunterId));
        return viewName;
    }
}
