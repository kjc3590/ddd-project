package io.github.wotjd243.findbyhint.hunter.domain;

import io.github.wotjd243.findbyhint.util.check.Check;

import javax.persistence.Embeddable;

@Embeddable
public class HunterPoint {

    private int hunterPoint;

    public HunterPoint() {
    }

    public HunterPoint(int hunterPoint) {

        validation(hunterPoint);

        this.hunterPoint = hunterPoint;

    }

    private void validation(int hunterPoint) {

        Check.numberLimit(hunterPoint, 99999);

    }

    public boolean bulletBuyPointCheck() {
        if (hunterPoint > 10) {
            return true;
        } else {
            return false;
        }
    }

    public int hunterPointMinus(int minus) {
        hunterPoint = hunterPoint - minus;
        return hunterPoint;
    }


    public void hunterPointPlus(int plus) {
        hunterPoint = hunterPoint + plus;
    }

    public int getHunterPoint() {
        return hunterPoint;
    }

    @Override
    public String
    toString() {
        return "HunterPoint{" +
                "hunterPoint=" + hunterPoint +
                '}';
    }
}
