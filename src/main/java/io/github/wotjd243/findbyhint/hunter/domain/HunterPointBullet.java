package io.github.wotjd243.findbyhint.hunter.domain;

import io.github.wotjd243.findbyhint.util.LocalDateTimePersistenceConverter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Embeddable
@Getter
public class HunterPointBullet {

    public HunterPointBullet() {
    }

    @Embedded
    private HunterPoint hunterPoint;

    @Embedded
    private HunterBullet hunterBullet;

    private Timestamp hunterBulletRefillTime;

    public HunterPointBullet(HunterPoint hunterPoint, HunterBullet hunterBullet, Timestamp hunterBulletRefillTime) {
        this.hunterPoint = hunterPoint;
        this.hunterBullet = hunterBullet;
        this.hunterBulletRefillTime = hunterBulletRefillTime;
    }

    public void buyOneBullet() {

        log.println("before_hunterPoint : " + hunterPoint.getHunterPoint());
        log.println("before_hunterBullet : " + hunterBullet.getHunterBullet());

        if (hunterPoint.bulletBuyPointCheck()) {
            hunterPoint.hunterPointMinus(100);
            hunterBullet.increaseOneBullet();
        } else {
            log.println("포인트가 부족합니다.");
            throw new IllegalStateException();
        }
        log.println("after_hunterPoint : " + hunterPoint.getHunterPoint());
        log.println("after_hunterBullet : " + hunterBullet.getHunterBullet());
    }

    @Override
    public String toString() {
        return "HunterPointBullet{" +
                "hunterPoint=" + hunterPoint +
                ", hunterBullet=" + hunterBullet +
                '}';
    }
}
