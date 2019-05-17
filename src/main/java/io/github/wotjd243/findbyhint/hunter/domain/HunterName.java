package io.github.wotjd243.findbyhint.hunter.domain;

import io.github.wotjd243.findbyhint.util.check.Check;

import javax.persistence.Embeddable;

@Embeddable
public class HunterName {

    private String hunterName;

    public HunterName() {
    }

    public HunterName(String hunterName) {
        validation(hunterName);
        this.hunterName = hunterName;
    }

    private void validation(String hunterName) {

        Check.lengthLimit(hunterName, 15);

    }

    public String getHunterName() {
        return hunterName;
    }

    @Override
    public String toString() {
        return "HunterName{" +
                "hunterName='" + hunterName + '\'' +
                '}';
    }
}
