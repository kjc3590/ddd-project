package io.github.wotjd243.findbyhint.hunter.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class HunterInfo {

    @Embedded
    private HunterPw hunterPw;
    @Embedded
    private HunterName hunterName;

    private String hunterPicturePath;

    @Override
    public String toString() {
        return "HunterInfo{" +
                "hunterPw=" + hunterPw +
                ", hunterName=" + hunterName +
                ", hunterPicturePath='" + hunterPicturePath + '\'' +
                ", hunterPictureName=" + hunterPictureName +
                '}';
    }

    @Embedded
    private HunterPictureName hunterPictureName;

    public HunterInfo(HunterPw hunterPw, HunterName hunterName, String hunterPicturePath, HunterPictureName hunterPictureName) {
        this.hunterPw = hunterPw;
        this.hunterName = hunterName;
        this.hunterPicturePath = hunterPicturePath;
        this.hunterPictureName = hunterPictureName;
    }

}
