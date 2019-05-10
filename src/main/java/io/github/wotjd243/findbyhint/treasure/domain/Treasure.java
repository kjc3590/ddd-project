package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.util.DateTimeEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// TODO (1) 1급컬렉션으로 만들수 있으면 만들어보기
// TODO (2) 인스턴스 변수 2개로 줄이기

@Entity
@Table(name = "treasure")
@Getter
@ToString
public class Treasure extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long treasureId;


    @NonNull
    @Column(nullable = false)
    private String treasureName;

    @Embedded
    private RunningTime runningTime;

    @Embedded
    private QRCodeVO qrCodeVO;


    // SubEntity 로 묶기
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "targetPointId")
    private List<TargetPoint> targetPointList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "missionKey")
    private List<Mission> missionList = new ArrayList<>();





    //기본 생성자
    private Treasure() {}

    public Treasure(String treasureName,
                    QRCodeVO qrCodeVO,
                    List<TargetPoint> targetPointList,
                    RunningTime runningTime) {
        validation(treasureName,targetPointList,runningTime);
        this.treasureName= treasureName;
        this.qrCodeVO = qrCodeVO;
        this.targetPointList= targetPointList;
        this.runningTime = runningTime;
        generateMissionList();
    }


    //미션을 생성하는 메소드
    public void generateMissionList(){

        int missionCount = this.runningTime.getMissionCountByRunningRangeDays();
        final List<Mission> missionList = new ArrayList<>();
        int missionLevelCount = MissionLevel.size();

        for (int i = missionLevelCount; i > 0; i--) {
            for (int i2 = 1; i2 <= i; i2++) {

                switch (i2) {
                    case 1:
                        missionList.add(new Mission(MissionLevel.EASY));
                        break;
                    case 2:
                        missionList.add(new Mission(MissionLevel.MEDIUM));
                        break;
                    case 3:
                        missionList.add(new Mission(MissionLevel.HARD));
                        break;
                }

                if (i == 1) {
                    i = missionLevelCount;
                    i2 = 0;
                }

                missionCount--;
                if (missionCount == 0) {
                    break;
                }

            }

            if (missionCount == 0) {
                break;
            }

        }
        this.missionList=  missionList;
    }

    public void generateTargetPointList(TargetPoint realTargetPoint) throws IllegalAccessException {

        List<TargetPoint> targetPointList = new ArrayList<>();

        IntStream.range(1,this.getHintCounter()).forEach(i ->{
            targetPointList.add(realTargetPoint.getFakeTargetPoint());
        });

        this.targetPointList = targetPointList;

    }


    private int getHintCounter() throws IllegalAccessException {

        if(this.missionList == null){
            throw  new  IllegalAccessException("미션리스트가 존재 하지 않습니다.");
        }

        this.missionList.forEach(mission -> {
            mission.getMissionLevel().getHintCounter();
        });

        return 0;
    }


    public int getTargetPointCount(){
        return this.targetPointList.size();
    }

    public int getMissionCount(){
        return this.missionList.size();
    }

    public static Treasure valueOf(String treasureName, QRCodeVO qrCodeVO,
                                   List<TargetPoint> targetPointList,
                                   RunningTime runningTime,
                                   List<Mission> missionList){
        return new Treasure(treasureName,qrCodeVO,targetPointList,runningTime);
    }

    //유효성 체크
    public void validation(String name,  List<TargetPoint> targetPointList,RunningTime runningTime){
        if(StringUtils.isEmpty(name)|| targetPointList == null || targetPointList.isEmpty() || runningTime == null) {
            new IllegalArgumentException("Treasure Exception !!!");
        }
    }


}
