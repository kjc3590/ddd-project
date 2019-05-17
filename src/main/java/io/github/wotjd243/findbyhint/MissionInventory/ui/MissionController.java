package io.github.wotjd243.findbyhint.MissionInventory.ui;

import io.github.wotjd243.findbyhint.MissionInventory.application.MissionDto;
import io.github.wotjd243.findbyhint.MissionInventory.application.MissionInventoryService;
import io.github.wotjd243.findbyhint.MissionInventory.infra.MissionApi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MissionController {

    @GetMapping("/")
    public void index() {
    }


    private MissionInventoryService missionInventoryService;

    public MissionController(MissionInventoryService missionInventoryService) {
        this.missionInventoryService = missionInventoryService;
    }

    @GetMapping("/mission")
    public void mission(Model model) throws IOException, IllegalAccessException {
        missionInventoryService.callMissionApi("testHunter").ifPresent(missionInventory -> model.addAttribute("missionInventory",missionInventory));
    }
}
