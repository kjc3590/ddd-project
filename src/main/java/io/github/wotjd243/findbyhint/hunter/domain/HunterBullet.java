package io.github.wotjd243.findbyhint.hunter.domain;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class HunterBullet {

    private int hunterBullet;

    public HunterBullet() {
    }

    public HunterBullet(int hunterBullet) {

        hunterBullet = maxBullet(hunterBullet);

        this.hunterBullet = hunterBullet;
    }

    public int maxBullet(int hunterBullet) {
        if(hunterBullet > 3){
            hunterBullet = 3;
        }
        return hunterBullet;
    }

    public int getHunterBullet() {
        return hunterBullet;
    }

    public void decreaseOneBullet() {

        if (hunterBullet < 1) {
            throw new IllegalStateException();
        }
        hunterBullet--;
    }

    public void challengeMissionBullet() {

        if (hunterBullet > 0) {
            decreaseOneBullet();
        } else{
            throw new IllegalStateException("No bullet");
        }

    }

    public void increaseOneBullet() {
        if (hunterBullet >= 3) {
            log.println("이미 총알이 3개입니다.");
            throw new IllegalStateException();
        }
        hunterBullet++;
    }

    @Override
    public String toString() {
        return "HunterBullet{" +
                "hunterBullet=" + hunterBullet +
                '}';
    }
}
