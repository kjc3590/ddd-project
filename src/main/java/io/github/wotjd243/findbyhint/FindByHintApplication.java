package io.github.wotjd243.findbyhint;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.MissionInventory.application.MissionInventoryService;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.MissionInventory.application.MissionDto;
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
    private final MissionInventoryService missionInfoService;

    public FindByHintApplication(TreasureService treasureService, MissionInventoryService missionInfoService) {
        this.treasureService = treasureService;
        this.missionInfoService = missionInfoService;
    }

    public static void main(String[] args) { SpringApplication.run(FindByHintApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        makeTreasureSample();
        makeMissionInfoSample();
    }

    public void makeTreasureSample(){
        DateObject dateObject = DateObject.getInstance();
        final String qrPw = "1234";
        final Double latitude = 126.9941658;
        final Double longitude = 37.4954676;
        final LocalDate startDate = dateObject.getDate().plusDays(10L);
        final LocalDate endDate = dateObject.getDate().plusDays(12L);
        final String name = "보물의 이름";

        TreasureRequestDto treasureRequestDto = new TreasureRequestDto(qrPw,latitude,longitude,startDate,endDate,name);

        log.info("treasureRequestDto :: "+ treasureRequestDto);

        treasureService.save(treasureRequestDto);
    }

    public void makeMissionInfoSample() {
        final String hunterId = "aa";
        final String question = "A?";
        final String answer = "true";
        final MissionLevel level = MissionLevel.BRONZE;
        final String success = "Y";

        MissionDto missionDto = new MissionDto(hunterId, question, answer, level, success);

        log.info("missionDto: "+missionDto);
        missionInfoService.save(missionDto);

    }

}
