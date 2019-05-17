package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.util.DateObject;
import io.github.wotjd243.findbyhint.util.VO.Event;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class RunningTimeTest {

    final static String name ="러닝타임 테스트";
    @Test
    public void 러닝타임생성_정상() {
        // given
        DateObject dateObject = DateObject.getInstance();
        LocalDate startDate = dateObject.getDate().plusDays(10L);
        LocalDate endDate = dateObject.getDate().plusDays(11L);


        // when
        Event.valueOf(startDate,endDate,name);

        // then

    }

    @Test(expected = IllegalArgumentException.class)
    public void 러닝타임생성_오늘날짜보다_이전인경우() {
        // given
        DateObject dateObject =DateObject.getInstance();
        LocalDate startDate = dateObject.getDate().minusDays(10L);
        LocalDate endDate = dateObject.getDate().minusDays(13L);

        // when
        Event.valueOf(startDate,endDate,name);

        // then

    }
    @Test(expected = IllegalArgumentException.class)
    public void 러닝타임생성_종료날짜가_시작날짜보다_이전인경우() {
        // given
        DateObject dateObject = DateObject.getInstance();
        LocalDate startDate = dateObject.getDate().plusDays(12L);
        LocalDate endDate = dateObject.getDate().plusDays(11L);

        // when
        Event.valueOf(startDate,endDate,name);

        // then

    }

    @Test
    public void 러닝타임기간가져오기() {
        // given
        DateObject dateObject = DateObject.getInstance();
        int startDays = 11;
        int endDays = 12;
        int distance = endDays - startDays;

        LocalDate startDate = dateObject.getDate().plusDays(startDays);
        LocalDate endDate = dateObject.getDate().plusDays(endDays);

        // when
        Event.valueOf(startDate,endDate,name);

        //int runningRangeDays = runningTime.getRunningRangeDays();

        // then
        //assertThat(runningRangeDays).isEqualTo(distance);
    }

    @Test
    public void 러닝타임의_따라_풀어야_하는_미션의_개수() {
        // given
        DateObject dateObject = DateObject.getInstance();
        int startDays = 1;
        int endDays = 15;

        int distance = endDays - startDays;
        int expectedCount = distance - distance/7*2;

        LocalDate startDate = dateObject.getDate().plusDays(startDays);
        LocalDate endDate = dateObject.getDate().plusDays(endDays);

        // when
/*        Event runningTime = Event.valueOf(startDate,endDate);
        int countByRunningRangeDays = runningTime.getMissionCountByRunningRangeDays();

        assertThat(countByRunningRangeDays).isEqualTo(expectedCount);*/
    }


}