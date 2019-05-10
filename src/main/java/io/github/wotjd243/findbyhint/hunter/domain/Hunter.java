package io.github.wotjd243.findbyhint.hunter.domain;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class Hunter {

    // TODO (0) 올바른 Exception 처리 관련 질문해서 반영하기.
    // TODO (1) 총알 리필 시간 관련 메소드 정리
    // TODO (1-1) 총알을 사용하는 메소드 > decreaseOneBullet
    // TODO (1-2) DB에 총알의 리필완료시간을 업데이트 하는 메소드 >
    // TODO (2-1) 리필완료시간과 현 시간을 체크하는 메소드
    //            총알을 리필하는 메소드 > increaseOneBullet
    // TODO (2-2)
    // TODO (3) 1급콜렉션 3개까지 허용

    private final HunterId hunterId;

    private final HunterPw hunterPw;

    private final HunterName hunterName;

    private final String hunterPicturePath;

    private final HunterPictureName hunterPictureName;

    private final HunterPoint hunterPoint;

    private final HunterBullet hunterBullet;

    public Hunter(String hunterId, String hunterPw, String hunterName, String hunterPicturePath, String hunterPictureName, int hunterPoint, int hunterBullet) {
        this.hunterId = new HunterId(hunterId);
        this.hunterPw = new HunterPw(hunterPw);
        this.hunterName = new HunterName(hunterName);
        this.hunterPicturePath = hunterPicturePath;
        this.hunterPictureName = new HunterPictureName(hunterPictureName);
        this.hunterPoint = new HunterPoint(hunterPoint);
        this.hunterBullet = new HunterBullet(hunterBullet);
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
        return "Hunter{" +
                "hunterId=" + hunterId +
                ", hunterPw=" + hunterPw +
                ", hunterName=" + hunterName +
                ", hunterPicturePath='" + hunterPicturePath + '\'' +
                ", hunterPictureName=" + hunterPictureName +
                ", hunterPoint=" + hunterPoint +
                ", hunterBullet=" + hunterBullet +
                '}';
    }
}
