package io.github.wotjd243.findbyhint.treasure.ui;

import io.github.wotjd243.findbyhint.treasure.application.DailyCheckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 *
 * @author DoYoung
 *
 */
@Controller
public class DailyCheckController {
    private final DailyCheckService dailyCheckService;

    public DailyCheckController(DailyCheckService dailyCheckService) {
        this.dailyCheckService = dailyCheckService;
    }

    //매일 자정 실행
    @Scheduled(cron = "0 0 0 * * *")
    public void checkTreasure(){
        dailyCheckService.checkTreasure();
    }
}
