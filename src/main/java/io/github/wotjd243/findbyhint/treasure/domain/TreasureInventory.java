package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.util.VO.Event;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Embeddable
@Getter
public class TreasureInventory {

    //다른 에그리거트에서 많이 조회할 것 같다 (runningTime, TargetPoint, Mission)

    public TreasureInventory() {}

    @Embedded
    @Column(name = "runningTime")
    private Event runningTime;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "treasure")
//    @JoinColumn(name = "targetPointId")
    private List<TargetPoint> targetPointList =new ArrayList<TargetPoint>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "treasure")
//    @JoinColumn(name = "missionId")
    private List<Mission> missionList = new ArrayList<Mission>();

    private TreasureInventory(
            Event runningTime, List<TargetPoint> targetPointList, List<Mission> missionList) {
        validation(runningTime, targetPointList, missionList);
        this.runningTime = runningTime;
        this.targetPointList = targetPointList;
        this.missionList = missionList;
    }

    public static TreasureInventory valueOf(Event runningTime, List<TargetPoint> targetPointList, List<Mission> missionList){
        return new TreasureInventory(runningTime, targetPointList, missionList);
    }

    private void validation(Event runningTime, List<TargetPoint> targetPointList, List<Mission> missionList) {

        if (runningTime == null) {
            throw new IllegalArgumentException("러닝타임을 할당 받지 못했습니다.");
        }

        if (targetPointList == null || targetPointList.isEmpty()) {
            throw new IllegalArgumentException("타겟포인트리스트를 할당 받지 못했습니다.");
        }

        if (missionList == null || missionList.isEmpty()) {
            throw new IllegalArgumentException("미션리스트 값을 할당 받지 못했습니다.");
        }

    }


}


