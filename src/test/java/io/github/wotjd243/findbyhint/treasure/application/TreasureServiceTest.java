package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import io.github.wotjd243.findbyhint.util.DateObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreasureServiceTest {

    @Autowired
    private TreasureService treasureService;

    @Autowired
    private TreasureRepository treasureRepository;

    @Test
    public void saveTest(){
        // given
        DateObject dateObject = DateObject.getInstance();
        final String qrPw = "1234";
        final Double latitude = 126.9941658;
        final Double longitude = 37.4954676;
        final LocalDate startDate = dateObject.getDate().plusDays(10L);
        final LocalDate endDate = dateObject.getDate().plusDays(12L);
        final String name = "보물의 이름";

        TreasureRequestDto treasureRequestDto = new TreasureRequestDto(qrPw,latitude,longitude,startDate,endDate,name);

        // when
        final Treasure result = treasureService.save(treasureRequestDto);

        // then
        assertThat(result).isNotNull();
    }


    @Test
    public void 현재_진행중인_보물아이디_값_가져오기(){
        //given
        Treasure sample = getActiveSampleTreasure();

        System.out.println("sample :: "+ sample);
        Long originId = sample.getTreasureId();


        //when
        Long treasureId = treasureService.getTreasureIdByActive();
        System.out.println("treasureId :: "+ treasureId);

        assertThat(treasureId).isNotNull();
        assertThat(treasureId).isEqualTo(originId);

        Treasure result = treasureService.getTreasure(treasureId);
        assertThat(result.isActive()).isTrue();

    }

    @Test
    public void 힌트_관련_정보_넘겨주기(){

        //given
        getActiveSampleTreasure();
        Long treasureId = treasureService.getTreasureIdByActive();
        List<Long> ids = new ArrayList<>();
       // List<Long> targetPointIds = treasureService.getTargetPointIds(treasureId,ids);




    }

    @Test
    public void 미션_반환_쿼리_테스트(){
        //given
        Treasure sample = treasureRepository.findById(1L).get();
        Long treasureId= sample.getTreasureId();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        Mission mission = treasureService.getMission(treasureId,ids);

        assertThat(mission).isNotNull();


    }




    public Treasure getSampleTreasure(){
        DateObject dateObject = DateObject.getInstance();
        final String qrPw = "1234";
        final Double latitude = 126.9941658;
        final Double longitude = 37.4954676;
        final LocalDate startDate = dateObject.getDate().plusDays(10L);
        final LocalDate endDate = dateObject.getDate().plusDays(12L);
        final String name = "보물의 이름";
        return treasureService.save(new TreasureRequestDto(qrPw,latitude,longitude,startDate,endDate,name));
    }


    public Treasure getActiveSampleTreasure(){
        DateObject dateObject = DateObject.getInstance();
        final String qrPw = "1234";
        final Double latitude = 126.9941658;
        final Double longitude = 37.4954676;
        final LocalDate startDate = dateObject.getDate().plusDays(10L);
        final LocalDate endDate = dateObject.getDate().plusDays(12L);
        final String name = "보물의 이름";
        Treasure sample = treasureService.save(new TreasureRequestDto(qrPw,latitude,longitude,startDate,endDate,name));
        sample.activeEvent();
        treasureRepository.save(sample);
        return sample;
    }

}