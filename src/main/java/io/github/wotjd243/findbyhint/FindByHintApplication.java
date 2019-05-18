package io.github.wotjd243.findbyhint;


import io.github.wotjd243.findbyhint.MissionInventory.application.MissionInventoryService;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionSuccessStatus;
import io.github.wotjd243.findbyhint.hint.domain.Hint;
import io.github.wotjd243.findbyhint.hint.domain.HintRepository;
import io.github.wotjd243.findbyhint.mission.application.MissionService;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.MissionInventory.application.MissionDto;

import io.github.wotjd243.findbyhint.hint.application.HintService;
import io.github.wotjd243.findbyhint.hunter.application.HunterService;
import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;

import io.github.wotjd243.findbyhint.treasure.application.TreasureRequestDto;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.util.DateObject;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DoYoung
 */
@EnableJpaAuditing
@EnableScheduling
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class}, // basePackageClasses 에 지정
        basePackages = {"io.github.wotjd243.findbyhint"})
@SpringBootApplication

@Log
public class FindByHintApplication implements CommandLineRunner {

    private final TreasureService treasureService;
    private final MissionInventoryService missionInventoryService;
    private final HintService hintService;
    private final HunterService hunterService;
    private final MissionService missionService;
    private final HintRepository hintRepository;

    public FindByHintApplication(TreasureService treasureService, MissionInventoryService missionInventoryService, HintService hintService, HunterService hunterService, MissionService missionService, HintRepository hintRepository) {
        this.treasureService = treasureService;
        this.missionInventoryService = missionInventoryService;
        this.hintService = hintService;
        this.hunterService = hunterService;
        this.missionService = missionService;
        this.hintRepository = hintRepository;
    }

    public static void main(String[] args) { SpringApplication.run(FindByHintApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        makeTreasureSample();
        hunterSampleCreate();
        //makeMissionInfoSample();
//        makeMissionInventorySample();
    }

    public void makeTreasureSample(){
        DateObject dateObject = DateObject.getInstance();
        final String qrPw = "1234";
        final Double latitude = 126.9941658;
        final Double longitude = 37.4954676;
        final LocalDate startDate = dateObject.getDate().plusDays(1L);
        final LocalDate endDate = dateObject.getDate().plusDays(10L);
        final String name = "보물의 이름";

        TreasureRequestDto treasureRequestDto = new TreasureRequestDto(qrPw,latitude,longitude,startDate,endDate,name);

        log.info("treasureRequestDto :: "+ treasureRequestDto);

        treasureService.save(treasureRequestDto);
    }

    public void hunterSampleCreate() {

        Hunter hunter = new Hunter("testHunter", "1234", "김헌터", "/test/test.png", "test.png", 0, 3);
        Hunter hunter2 = new Hunter("testHunter2", "1234", "부자헌터", "/test/test.png", "test.png", 100, 3);

        hunterService.hunterCreate(hunter);
        hunterService.hunterCreate(hunter2);
    }


    public void makeHintInventorySample(){

        final int hintCount =2;
        hintService.addHint("testHunter",hintCount);
    }


    public void makeMissionInventorySample(){
        final String hunterId = "testHunter";
        final Long treasureId = treasureService.getTreasureIdByActive();
        Hint hint = Hint.valueOf(hunterId,treasureId);
        List<Long>  ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        hint.addBringTargetPointIds(ids);
        hintRepository.save(hint);
    }

}