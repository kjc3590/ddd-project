package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.util.VO.Event;
import io.github.wotjd243.findbyhint.util.domain.DateTimeEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// TODO (1) 1급컬렉션으로 만들수 있으면 만들어보기
// TODO (2) 인스턴스 변수 2개로 줄이기
// TODO (3) getHintCounter 완성시키기
// TODO (4) Domain Service 만들기

@Entity
@Table(name = "treasure")
@Getter
@ToString
public class Treasure extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long treasureId;

    @Embedded
    private QRCode qrCodeVO;

    @Embedded
    private TreasureInventory  treasureInventory;

    //기본 생성자
    private Treasure() {}

    public Treasure(String qrUrl, String qrPw,
                    Double latitude, Double hardness,
                    LocalDate startDate, LocalDate endDate, String name
                    ) {

        Event runningTime = Event.valueOf(startDate, endDate, name);
        TargetPoint targetPoint = TargetPoint.valueOf(latitude,hardness);
        Period period = runningTime.getEventPeriod().getPeriod();

        List<Mission> missionList = generateMissionList();


        this.qrCodeVO = QRCode.valueOf(qrUrl, qrPw);
        this.treasureInventory= TreasureInventory.valueOf(runningTime,targetPointList,missionList);

    }


    //러닝타임의 따라 풀어야 하는 미션의 개수
    // 공식 :  1주일에 개의 문제  7일당 5문제
    public int getMissionCountByRunningTime(Period period){
        int runningRangeDays = period.getDays();
        //설명변수
        int missionCount = runningRangeDays  - runningRangeDays/7 * 2;
        return missionCount;
    }



    //미션을 생성하는 메소드
    public List<Mission> generateMissionList(){

        int missionCount = getMissionCountByRunningTime();
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
        return  missionList;
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


    public static Treasure valueOf(String treasureName, QRCode qrCodeVO,
                                   List<TargetPoint> targetPointList,
                                   Event runningTime,
                                   List<Mission> missionList){
        return new Treasure(treasureName,qrCodeVO,targetPointList,runningTime);
    }

}
