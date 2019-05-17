package io.github.wotjd243.findbyhint.MissionInventory.domain;

import lombok.Getter;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.math.BigInteger;

@Embeddable
@Getter
@Log
public class MissionInventoryInfo {

    @Column(nullable = false)
    private Long missionId;

//    @Embedded
//    private MissionQnA missionQnA;

    private String question;
    private String answer;

    @Enumerated(EnumType.STRING)
    private MissionSuccessStatus status;

    private MissionInventoryInfo() {
    }

    public MissionInventoryInfo(Long missionId, String question, String answer, MissionSuccessStatus status) {
        this.missionId = missionId;
        this.question = question;
        this.answer = answer;
        this.status = MissionSuccessStatus.FAIL;
    }

    public static MissionInventoryInfo valueOf(Long missionId, String question, String answer, MissionSuccessStatus status) {
        return new MissionInventoryInfo(missionId, question, answer, status);
    }


    // 미션 성공
    public void missionComplete(){
        this.status = MissionSuccessStatus.SUCCESS;
    }

//    public void setMissionInventory(MissionInventory missionInventory) {
//
//        log.info("missionInventory.getHunterId(): "+missionInventory.getHunterId());
//        log.info("missionInventory.getTreasureId(): "+missionInventory.getTreasureId());
//        if(this.missionInventory != null) {
//            if(this.missionInventory.getMissionInventoryInfoList() != null) {
//                this.missionInventory.getMissionInventoryInfoList().remove(this);
//            }
//        }
//        this.missionInventory = missionInventory;
//        this.missionInventory.getMissionInventoryInfoList().add(this);
//    }
}
