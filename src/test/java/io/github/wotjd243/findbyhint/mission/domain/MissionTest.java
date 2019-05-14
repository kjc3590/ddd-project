package io.github.wotjd243.findbyhint.mission.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissionTest {

    @Test
    public void 미션_생성() {
        // given
        // when
        // then
        new Mission(MissionLevel.EASY);
    }

    @Test
    public void 골드_레벨_미션_생성() {new Mission(MissionLevel.HARD);
    }




}