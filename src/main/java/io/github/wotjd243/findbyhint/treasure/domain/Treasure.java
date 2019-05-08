package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Treasure {

    private Long treasureId;

    //보물의 이름
    //보물의 이름
    //보물의 이름  asd
    //보물의 이름  asd
    //보물의 이름  asd
    private String treasureName;

    //현재상태
    private String runningStatus;

    private List<TargetPoint> targetPointList;

    private RunningTime runningTime;

    //보물로 접근할 수 있는 QR코드
    private QRCodeVO qrCodeVO;

    private List<Mission> missionList;

    public Treasure(String treasureName,
                    String runningStatus, QRCodeVO qrCodeVO,
                    List<TargetPoint> targetPointList,
                    RunningTime runningTime,
                    final List<Mission> missionList) {
        validation(treasureName,runningStatus,targetPointList,runningTime,missionList);
        this.treasureId = UUID.randomUUID().getMostSignificantBits();
        this.treasureName= treasureName;
        this.runningStatus = runningStatus;
        this.qrCodeVO = qrCodeVO;
        this.targetPointList= targetPointList;
        this.runningTime = runningTime;
        this.missionList = missionList;
    }

    public static Treasure valueOf(String treasureName, QRCodeVO qrCodeVO,
                                   String runningStatus,
                                   List<TargetPoint> targetPointList,
                                   RunningTime runningTime,
                                   List<Mission> missionList){
        return new Treasure(treasureName,runningStatus,qrCodeVO,targetPointList,runningTime,missionList);
    }

    public void validation(String name, String runningStatus,  List<TargetPoint> targetPointList,RunningTime runningTime, List<Mission> missionList ){
        if(missionList.isEmpty()|| StringUtils.isEmpty(name)|| StringUtils.isEmpty(runningStatus) || targetPointList.isEmpty() || runningTime == null) {
            new IllegalArgumentException("Treasure Exception !!!");
        }
    }

}
