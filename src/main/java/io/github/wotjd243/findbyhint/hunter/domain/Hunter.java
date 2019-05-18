package io.github.wotjd243.findbyhint.hunter.domain;

import io.github.wotjd243.findbyhint.util.domain.DateTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "hunter")
@Getter
public class Hunter extends DateTimeEntity {

    // TODO (1) 총알 리필 시간 관련 메소드 정리
    // TODO (1-1) 총알을 사용하는 메소드 > decreaseOneBullet
    //            DB에 총알의 리필완료시간을 업데이트 하는 메소드 >
    //            리필완료시간과 현 시간을 체크하는 메소드
    //            총알을 리필하는 메소드 > increaseOneBullet
    // TODO (3) 1급콜렉션 3개까지 허용

    public Hunter() {}

    @EmbeddedId
    private HunterId hunterId;

    @Embedded
    private HunterInfo hunterInfo;

    @Embedded
    private HunterPointBullet hunterPointBullet;

    public Hunter(String hunterId, String hunterPw, String hunterName, String hunterPicturePath, String hunterPictureName, int hunterPoint, int hunterBullet, Timestamp hunterBulletRefillTime) {
        this.hunterId = new HunterId(hunterId);
        this.hunterInfo = new HunterInfo(new HunterPw(hunterPw), new HunterName(hunterName), hunterPicturePath, new HunterPictureName(hunterPictureName));
        this.hunterPointBullet = new HunterPointBullet(new HunterPoint(hunterPoint), new HunterBullet(hunterBullet), hunterBulletRefillTime);
    }

    @Override
    public String toString() {
        return "Hunter{" +
                "hunterId=" + hunterId +
                ", hunterInfo=" + hunterInfo +
                ", hunterPointBullet=" + hunterPointBullet +
                '}';
    }

    public void buyOneBullet() {
        hunterPointBullet.buyOneBullet();
    }

}