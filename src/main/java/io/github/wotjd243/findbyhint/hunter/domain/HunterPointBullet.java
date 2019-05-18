package io.github.wotjd243.findbyhint.hunter.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Embeddable
@Getter
public class HunterPointBullet {

    //TODO(0) 총알 충전시간 객체 생성

    public HunterPointBullet() {
    }

    @Embedded
    private HunterPoint hunterPoint;

    @Embedded
    private HunterBullet hunterBullet;

    public HunterPointBullet(HunterPoint hunterPoint, HunterBullet hunterBullet) {
        this.hunterPoint = hunterPoint;
        this.hunterBullet = hunterBullet;
    }

    public void buyOneBullet() {

        log.println("before_hunterPoint : " + hunterPoint.getHunterPoint());
        log.println("before_hunterBullet : " + hunterBullet.getHunterBullet());

        if (hunterPoint.bulletBuyPointCheck()) {
            hunterPoint.hunterPointMinus(10);
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
