package io.github.wotjd243.findbyhint;

import io.github.wotjd243.findbyhint.treasure.application.TreasureRequestDto;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import io.github.wotjd243.findbyhint.treasure.ui.TreasureRequestDTO;
import io.github.wotjd243.findbyhint.util.DateObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
    public FindByHintApplication(TreasureService treasureService) {
        this.treasureService = treasureService;
    }


    public static void main(String[] args) { SpringApplication.run(FindByHintApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        makeTreasureSample();
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

}
