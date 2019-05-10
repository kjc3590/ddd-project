package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

// TODO (1) Entity로 만들어보기
// TODO (2) domain service 만들어 보기

@Entity
@Table(name = "treasure")
public class Treasure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long treasureId;

    //보물의 이름
    @Column(nullable = false)
    @NonNull
    private String treasureName;

    //현재상태 기본값 대기
    @Column(nullable = false)
    private String runningStatus = "대기";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "targetPointId")
    private List<TargetPoint> targetPointList;

    @Embedded
    private RunningTime runningTime;

    //보물로 접근할 수 있는 QR코드
    @Embedded
    private QRCodeVO qrCodeVO;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "missionKey")
    private List<Mission> missionList;

    //기본 생성자
    private Treasure() {}

    public Treasure(String treasureName,
                    QRCodeVO qrCodeVO,
                    List<TargetPoint> targetPointList,
                    RunningTime runningTime,
                    final List<Mission> missionList) {
        validation(treasureName,targetPointList,runningTime,missionList);
        this.treasureName= treasureName;
        this.qrCodeVO = qrCodeVO;
        this.targetPointList= targetPointList;
        this.runningTime = runningTime;
        this.missionList = missionList;
    }

    public int targetPointCount(){
        return this.targetPointList.size();
    }

    public int missionCount(){
        return this.missionList.size();
    }

    public static Treasure valueOf(String treasureName, QRCodeVO qrCodeVO,
                                   List<TargetPoint> targetPointList,
                                   RunningTime runningTime,
                                   List<Mission> missionList){
        return new Treasure(treasureName,qrCodeVO,targetPointList,runningTime,missionList);
    }

    public void validation(String name,  List<TargetPoint> targetPointList,RunningTime runningTime, List<Mission> missionList ){
        if(missionList == null || missionList.isEmpty()|| StringUtils.isEmpty(name)|| targetPointList == null || targetPointList.isEmpty() || runningTime == null) {
            new IllegalArgumentException("Treasure Exception !!!");
        }
    }

    public Long getTreasureId() { return treasureId; }
    public String getTreasureName() { return treasureName; }
    public String getRunningStatus() { return runningStatus; }
    public List<TargetPoint> getTargetPointList() { return targetPointList; }
    public RunningTime getRunningTime() { return runningTime; }
    public QRCodeVO getQrCodeVO() { return qrCodeVO; }
    public List<Mission> getMissionList() { return missionList; }

    @Override
    public String toString() {
        return "Treasure{" +
                "treasureId=" + treasureId +
                ", treasureName='" + treasureName + '\'' +
                ", runningStatus='" + runningStatus + '\'' +
                ", targetPointList=" + targetPointList +
                ", runningTime=" + runningTime +
                ", qrCodeVO=" + qrCodeVO +
                ", missionList=" + missionList +
                '}';
    }
}
