package io.github.wotjd243.findbyhint.treasure.ui;


import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 *
 * @author DoYoung
 *
 */

@Controller
@Log
public class TreasureController {

    private final TreasureService treasureService;

    public TreasureController(TreasureService treasureService) {
        this.treasureService = treasureService;
    }

    @GetMapping("/treasure/save")
    public String  saveTreasure(@Valid Treasure treasure){

        log.info("saveTreasure 실행 및 파라미터 확인");
//        log.info("saveTreasure treasure -> " + treasure.getQrCodeVO());
//        log.info("saveTreasure RunningStatus -> " + treasure.getRunningStatus());
//        log.info("saveTreasure TreasureName -> " + treasure.getTreasureName());
//        log.info("saveTreasure TargetPointList -> " + treasure.getTargetPointList());

        treasureService.save(treasure);

        return "/index";

    }
}
