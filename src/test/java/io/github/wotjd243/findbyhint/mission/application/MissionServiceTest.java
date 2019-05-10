package io.github.wotjd243.findbyhint.mission.application;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.mission.domain.MissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MissionServiceTest {

    @Mock
    private MissionRepository missionRepository;

    @InjectMocks
    private MissionService missionService;

    @Test
    public void 아이디_찾기() throws IOException{
        System.out.println(missionRepository.findById(MissionLevel.valueOf("HARD")));
        for(MissionLevel level : MissionLevel.values()) {
            level.getLevelValue();
            System.out.println("levelValue: "+level.getLevelValue());
            System.out.println("levelValueOf: "+MissionLevel.valueOf("HARD"));
            System.out.println(missionRepository.findById(any()));
        }

    }

    @Test
    public void 미션_맞추면_포인트_증정() throws IOException{

        // given
        given(missionRepository.findById(any()))
                .willReturn(
                        Optional.of(
                                new Mission(MissionLevel.valueOf("EASY"))
                        )
                )
        ;

        // when
        final int point = missionService.takePoint("EASY");

        // then
        assertThat(point).isNotZero();
        System.out.println(point);
    }

    @Test
    public void testMission() throws IOException {
        // given
        given(missionRepository.findById(any()))
                .willReturn(
                        Optional.of(
                                new Mission(MissionLevel.EASY)
                        )
                )
        ;
        int point = 0;
        int count = 12;

        for(int i = 3; i > 0; i--) {
            for(int i2 =1; i2 <= i; i2++){

                switch (i2) {
                    case 1:
                        System.out.println("브론즈 미션 생성");
                        break;
                    case 2:
                        System.out.println("실버 미션 생성");
                        break;
                    case 3:
                        System.out.println("골드 미션 생성");
                        break;
                }
                if(i == 1){
                    i = 3;
                    i2 = 0;
                }
                count--;

                if(count == 0){
                    break;
                }
            }
            if(count == 0){
                break;
            }
        }
        System.out.println("point: "+point);
        //when
        final int point2 = missionService.takePoint("EASY");

        //then
        assertThat(point2).isNotZero();
        System.out.println("point2: " +point2);
    }

}