package io.github.wotjd243.findbyhint.mission.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Embeddable
public class MissionInventory {

    private BigInteger missionId;

    @Enumerated(value = EnumType.STRING)
    private MissionSuccessStatus status;

    public MissionInventory(BigInteger missionId) {
        validation(missionId);
        this.missionId = missionId;
        this.status = MissionSuccessStatus.FAIL;
    }

    public static MissionInventory valueOf(BigInteger missionId) {
        return new MissionInventory(missionId);
    }

    private void validation(BigInteger missionId) {
        if(missionId == null) {
            throw new IllegalArgumentException("미션 아이디가 존재하지 않습니다.");
        }
    }


    // 미션 성공
    public void missionComplete(){
        this.status = MissionSuccessStatus.SUCCESS;
    }


}
