package io.github.wotjd243.findbyhint.hunter.domain;

import io.github.wotjd243.findbyhint.util.DateTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "hunter")
public class Hunter extends DateTimeEntity {

    // TODO (0) 올바른 Exception 처리 관련 질문해서 반영하기.
    // TODO (1) 총알 리필 시간 관련 메소드 정리
    // TODO (1-1) 총알을 사용하는 메소드 > decreaseOneBullet
    // TODO (1-2) DB에 총알의 리필완료시간을 업데이트 하는 메소드 >
    // TODO (2-1) 리필완료시간과 현 시간을 체크하는 메소드
    //            총알을 리필하는 메소드 > increaseOneBullet
    // TODO (2-2)
    // TODO (3) 1급콜렉션 3개까지 허용

    public Hunter() {}

    @EmbeddedId
    private HunterId hunterId;

    @Embedded
    private HunterInfo hunterInfo;

    @Embedded
    private HunterPointBullet hunterPointBullet;

    public Hunter(String hunterId, String hunterPw, String hunterName,String hunterPicturePath,String hunterPictureName,int hunterPoint, int hunterBullet) {
        this.hunterId = new HunterId(hunterId);
        this.hunterInfo = new HunterInfo(new HunterPw(hunterPw), new HunterName(hunterName), hunterPicturePath, new HunterPictureName(hunterPictureName));
        this.hunterPointBullet = new HunterPointBullet(new HunterPoint(hunterPoint), new HunterBullet(hunterBullet));
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