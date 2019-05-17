package io.github.wotjd243.findbyhint.hint.application;

import io.github.wotjd243.findbyhint.hint.domain.Hint;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.treasure.domain.TargetPoint;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.lang.annotation.Target;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HintServiceTest {

    @Autowired
    private HintService hintService;

    @Autowired
    private TreasureService treasureService;

    @Test
    @Transactional
    public void 힌트추가하기() {

            Long treasureId =treasureService.getTreasureIdByActive();

            hintService.addHint("testHunter",2);

            Hint hint = hintService.findByHunterIdAndTreasureId("testHunter",treasureId);

            // 힌트가 가지고 있는 타겟포인트의 집합 추출 , List<Long>타입으로 리턴하는데 이는 리스트가 가진
            for (Long bringTargetPointId : hint.getBringTargetPointIds()) {
            System.out.println("bringTargetPointId :: "+bringTargetPointId);
        }

    }

    @Test
    @Transactional
    public void 힌트를_제외한_타겟포인트져오기(){
        Long treasureId =treasureService.getTreasureIdByActive();

        String hunterId = "testHunter";
        List<TargetPoint> result
                = hintService.getTargetPoints(hunterId,treasureId);

        for (TargetPoint targetPoint : result) {
            System.out.println("targetPoint.getTargetPointId() :: '"+targetPoint.getTargetPointId()+"'");
        }
    }

    @Test
    public void 헌터의_힌트리스트를_가져오는_테스트(){
        Long treasureId =treasureService.getTreasureIdByActive();

        String hunterId = "testHunter";

        List<Long> result
                = hintService.getHintTargetPointIds(hunterId,treasureId);

        result.forEach(aLong -> {
            System.out.println("aLong :: '" + aLong + "'");
        });


    }

}