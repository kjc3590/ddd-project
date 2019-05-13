package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.util.DateObject;
import io.github.wotjd243.findbyhint.util.VO.Event;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    final static String name ="러닝타임 테스트";

    @Test
    public void 이벤트_대기상태_여부() {

        // given
        DateObject dateObject =DateObject.getInstance();
        LocalDate startDate = dateObject.getDate().minusDays(10L);
        LocalDate endDate = dateObject.getDate().minusDays(13L);
        Event event = Event.valueOf(startDate,endDate,name);
        // when
        assertThat(event.isWait()).isTrue();
        // then

    }

    @Test
    public void 이벤트_진행상태_여부() {
        // given
        DateObject dateObject =DateObject.getInstance();
        LocalDate startDate = dateObject.getDate().minusDays(10L);
        LocalDate endDate = dateObject.getDate().minusDays(13L);
        Event event = Event.valueOf(startDate,endDate,name);
        event.activeEvent();

        // when
        assertThat(event.isActive()).isTrue();
        // then
    }

    @Test
    public void 이벤트_종료상태_여부() {
        // given
        DateObject dateObject =DateObject.getInstance();
        LocalDate startDate = dateObject.getDate().minusDays(10L);
        LocalDate endDate = dateObject.getDate().minusDays(13L);
        Event event = Event.valueOf(startDate,endDate,name);
        event.closeEvent();

        // when
        assertThat(event.isClose()).isTrue();
        // then
    }



}