//package io.github.wotjd243.findbyhint.mission.domain;
//
//import jdk.nashorn.internal.runtime.logging.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Logger
//public class MissionInfoTest {
//
//    @Autowired
//    MissionInfoRepository missionInfoRepository;
//
//    @Test
//    public void 미션_문제와_답_저장() throws Exception {
//        List<MissionInventory> party = Arrays.asList(
//                new MissionInventory(1L, "Y"),
//                new MissionInventory(2L, "N")
//        );
//
//    }
//
//    @Test
//    public void 미션_한_사람과_정보_저장() throws Exception {
//
//        MissionInfo missionInfo = new MissionInfo(1L, 1L);
//
//        //given
//        missionInfoRepository.save(missionInfo);
//        System.out.println("missionInfo.getParty(): "+missionInfo.getParty());
//
//        //when
//
//        //then
//
//    }
//
//}