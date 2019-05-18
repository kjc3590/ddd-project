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

    private String question;
    private String answer;
    private String wrongAnswer;

    @Enumerated(EnumType.STRING)
    private MissionSuccessStatus status;

    private MissionInventoryInfo() {
    }

    public MissionInventoryInfo(Long missionId, String question, String answer, String wrongAnswer) {
        this.missionId = missionId;
        this.question = question;
        this.answer = answer;
        this.wrongAnswer = wrongAnswer;
        this.status = MissionSuccessStatus.FAIL;
    }

    public static MissionInventoryInfo valueOf(Long missionId, String question, String answer, String wrongAnswer) {
        return new MissionInventoryInfo(missionId, question, answer, wrongAnswer);
    }

    public void missionSuccess(){
        this.status =MissionSuccessStatus.SUCCESS;
    }

    public void changeQna(String question, String answer){
        this.question = question;
        this.answer = answer;
    }



    // 미션 성공
//    public void missionComplete(){
//        this.status = MissionSuccessStatus.SUCCESS;
//    }
//
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
