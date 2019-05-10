package io.github.wotjd243.findbyhint.mission.domain;

import javax.persistence.Embeddable;

@Embeddable
public class MissionAccess { //미션에 접근했을때 (문제가 보여질 때)
    private final int missionAccessId;

    public MissionAccess(int missionAccessId) {
        this.missionAccessId = missionAccessId;
    }
}
