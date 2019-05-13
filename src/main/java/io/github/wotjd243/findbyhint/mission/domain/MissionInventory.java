package io.github.wotjd243.findbyhint.mission.domain;

import io.github.wotjd243.findbyhint.util.DateTimeEntity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Embeddable
public class MissionInventory extends DateTimeEntity {

    private Long missionId;

    @ColumnDefault("N")
    private String success;

    private MissionQnA missionQnA;

    public MissionInventory(Long missionId, String success) {
        this.missionId = missionId;
        this.success = success;
    }

}