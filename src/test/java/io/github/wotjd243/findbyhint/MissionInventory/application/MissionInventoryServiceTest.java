package io.github.wotjd243.findbyhint.MissionInventory.application;

import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionInventory;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionInventoryRepository;
import io.github.wotjd243.findbyhint.hunter.domain.HunterDto;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.treasure.application.TreasureRequestDto;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionInventoryServiceTest {

    private MissionInventoryRepository missionInventoryRepository;
    private TreasureRepository treasureRepository;
    private HunterDto hunterDto;

    @Test
    public void 헌터아이디와_현재_진행중인_보물아이디_불러오기() {

        //given
        String hunterId = hunterDto.getHunterId();
        Treasure treasure = treasureRepository.findByTreasureInventoryByActive();
        Long treasureId = treasure.getTreasureId();
        System.out.println("hunterId:" + hunterId);
        System.out.println("treasureId:" + treasureId);

        //when
        Optional<MissionInventory> byHunterIdAndTreasureId = missionInventoryRepository.findByHunterIdAndTreasureId(HunterId.valueOf(hunterId), treasureId);

        //then
        assertThat(treasureId).isNotNull();
        assertThat(hunterId).isNotNull();
    }

}