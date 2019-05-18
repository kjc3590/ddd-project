package io.github.wotjd243.findbyhint.MissionInventory.ui;

import io.github.wotjd243.findbyhint.MissionInventory.application.MissionDto;
import io.github.wotjd243.findbyhint.MissionInventory.application.MissionInventoryService;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionInventory;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionInventoryInfo;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionInventoryRepository;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionPostDto;
import io.github.wotjd243.findbyhint.MissionInventory.infra.MissionApi;

import io.github.wotjd243.findbyhint.hunter.application.HunterService;
import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.mission.application.MissionService;
import io.github.wotjd243.findbyhint.mission.domain.SuccessMissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Optional;

@Controller
public class MissionController {

    private MissionService missionService;
    private SuccessMissionService successMissionService;
    private HunterService hunterService;

    public MissionController(MissionService missionService, SuccessMissionService successMissionService, HunterService hunterService) {
        this.missionService = missionService;
        this.successMissionService = successMissionService;
        this.hunterService = hunterService;
    }

    @GetMapping("/")
    public void index() {
    }


    @GetMapping("/mission")
    public void mission(Model model) throws IOException, IllegalAccessException {
        missionService.missionCall(model);
    }

    @PostMapping("/missionSubmit")
    public void missionSubmit(MissionPostDto missionPostDto, Model model) {

        Hunter hunter = hunterService.findById("testHunter");

        boolean sucessCheck = missionPostDto.getAnswer().equals(missionPostDto.getHunterAnswer());
        int plusPoint = 0;

        if(sucessCheck) {
            plusPoint = successMissionService.isSuccess();
            hunterService.pointUpdate(hunter,plusPoint);
        }

        System.out.println("missionDto :: + " + missionPostDto.toString());

        model.addAttribute("sucessCheck", sucessCheck);
        model.addAttribute("plusPoint", plusPoint);
        model.addAttribute("hunterPoint", hunter.getHunterPointBullet().getHunterPoint());
        model.addAttribute("hunterBullet", hunter.getHunterPointBullet().getHunterBullet());

    }

}
