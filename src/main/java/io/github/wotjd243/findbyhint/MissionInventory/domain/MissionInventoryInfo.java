package io.github.wotjd243.findbyhint.MissionInventory.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class MissionInventoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missionInventoryInfoId;

    private Long missionId;

    @ManyToOne
    @JoinColumn(name = "missionInventoryInfo")
    private MissionInventory missionInventory;

    @Embedded
    private MissionQnA missionQnA;

    private MissionSuccessStatus status;

    public MissionInventoryInfo() {
    }

    public MissionInventoryInfo(Long missionId, String question, String answer) {
        this.missionId = missionId;
        this.missionQnA = MissionQnA.valueOf(question, answer);
        this.status = MissionSuccessStatus.FAIL;
    }


    // 미션 성공
    public void missionComplete(){
        this.status = MissionSuccessStatus.SUCCESS;
    }

}
