package io.github.wotjd243.findbyhint.mission.domain;

import io.github.wotjd243.findbyhint.util.domain.DateTimeEntity;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Embeddable
public class MissionInventory {

    private Long missionId;

    @ColumnDefault("N")
    private String success;

    public MissionInventory(Long missionId, String success) {
        this.missionId = missionId;
        this.success = success;
    }

}
