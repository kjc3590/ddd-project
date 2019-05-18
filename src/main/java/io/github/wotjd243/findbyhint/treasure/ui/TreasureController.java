package io.github.wotjd243.findbyhint.treasure.ui;


import io.github.wotjd243.findbyhint.treasure.application.QrCodeService;
import io.github.wotjd243.findbyhint.treasure.application.TreasureRequestDto;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 *
 * @author DoYoung
 *
 */

@Controller
@Log
@RequestMapping("/treasure")
public class TreasureController {

    private final TreasureService treasureService;

    public TreasureController(TreasureService treasureService) {
        this.treasureService = treasureService;
    }

    @GetMapping("save")
    public String  saveTreasure(TreasureRequestDto treasureRequestDto){

        log.info("==========================================================");
        log.info("saveTreasure 실행 및 파라미터 확인");
        log.info("treasureRequestDto :: "+ treasureRequestDto);
        log.info("==========================================================");

        treasureService.save(treasureRequestDto);

        return "/index";

    }






}
