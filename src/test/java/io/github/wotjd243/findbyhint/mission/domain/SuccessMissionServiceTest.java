package io.github.wotjd243.findbyhint.mission.domain;

import io.github.wotjd243.findbyhint.mission.application.MissionService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SuccessMissionServiceTest {
    private static final Mission TEST_MISSION_EASY_TAKE_POINT = new Mission(MissionLevel.BRONZE);
    private static final Mission TEST_MISSION_HARD_TAKE_POINT = new Mission(MissionLevel.GOLD);

    @Test
    public void 쉬운_문제_맞췄을때() {
        //given
        final SuccessMissionService successMissionService = new SuccessMissionService(TEST_MISSION_EASY_TAKE_POINT);

        //when
        final int point = successMissionService.isSuccess();

        //then
        assertThat(point).isNotZero();
        System.out.println("point: "+point);
    }

    @Test
    public void 어려운_문제_맞췄을때() {
        //given
        final SuccessMissionService successMissionService = new SuccessMissionService(TEST_MISSION_HARD_TAKE_POINT);

        //when
        final int point = successMissionService.isSuccess();

        //then
        assertThat(point).isNotZero();
        System.out.println("point: "+point);
    }



}