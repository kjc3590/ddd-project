package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.util.VO.EventStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TreasureRepositoryTest {

    @Autowired
    private TreasureRepository treasureRepository;


    @Test
    public void 대기중인_보물_가져오기(){
        List<Treasure> treasureGroup = treasureRepository.findByTreasureInventoryByWait();
        for (Treasure treasure : treasureGroup) {
            if(treasure.isWait()){
                System.out.println(treasure);
            }
        }
    }

    @Test
    public void 보물_진행중으로_변경(){
        List<Treasure> treasureGroup = treasureRepository.findByTreasureInventoryByWait();
        for (Treasure treasure : treasureGroup) {
            if(treasure.isWait()){
                treasure.activeEvent();
                Treasure result = treasureRepository.save(treasure);
                if(result.isActive()){
                    System.out.println("active 변경 성공");
                    assertThat(result.getTreasureInventory().getRunningTime().getStatus()).isEqualTo(EventStatus.ACTIVE);
                }
            }
        }
    }

    @Test
    public void 보물_완료상태로_변경(){
        List<Treasure> treasureGroup = treasureRepository.findByTreasureInventoryByWait();
        for (Treasure treasure : treasureGroup) {
            if(treasure.isWait()){
                treasure.activeEvent();
                Treasure result = treasureRepository.save(treasure);

                result.closeEvent();
                Treasure result2 = treasureRepository.save(result);
                assertThat(result2.getTreasureInventory().getRunningTime().getStatus()).isEqualTo(EventStatus.CLOSE);
            }
        }
    }


    @Test
    public void 힌트반환_쿼리_테스트(){

        //given
        Treasure sample = treasureRepository.findById(1L).get();
        int hintCount = 2;
        Long treasureId= sample.getTreasureId();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        //when
        List<Long> result = treasureRepository.findTargetPointIds(treasureId,ids,hintCount);
        for (Long aLong : result) {
            System.out.println(aLong);
        }

        //then
        assertThat(result.size()).isEqualTo(hintCount);

    }

    @Test
    public void findMission_메소드테스트(){
        System.out.println("asd");
        Long treasureId = treasureRepository.findByTreasureInventoryByActive().getTreasureId();
        System.out.println("treasureId :: " + treasureId);
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        System.out.println("ids :: " + ids);
        List<Mission> mission = treasureRepository.findMission(treasureId,ids);
        System.out.println("mission :: "+ mission);
    }




}