package io.github.wotjd243.findbyhint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 *
 * @author DoYoung
 *
 */
@EnableJpaAuditing
@EnableScheduling
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class}, // basePackageClasses 에 지정
        basePackages = {"io.github.wotjd243.findbyhint"})
@SpringBootApplication
public class FindByHintApplication {
    public static void main(String[] args) {
        SpringApplication.run(FindByHintApplication.class, args);
    }
}
