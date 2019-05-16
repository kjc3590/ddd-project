package io.github.wotjd243.findbyhint.hint.application;

import io.github.wotjd243.findbyhint.hint.domain.Hint;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HintServiceTest {

    @Autowired
    private HintService hintService;

    @Autowired
    private TreasureService treasureService;

    @Test
    public void 힌트추가하기() {

        Long treasureId =treasureService.getTreasureIdByActive();
        HunterId hunterId = new HunterId("hunterId");
        hintService.addHint(hunterId,2);

        Hint hint = hintService.findByHunterIdAndHintInventory_TreasureId(hunterId,treasureId);

        for (Long bringTargetPointId : hint.getBringTargetPointIds()) {
            System.out.println("bringTargetPointId :: "+bringTargetPointId);
        }

    }
}