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

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Entity
@Table(name = "treasure")
@Getter
@ToString
public class Treasure extends DateTimeEntity {

    //기본 생성자
    private Treasure() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long treasureId;

    @Embedded
    private QRCode qrCodeVO;

    @Embedded
    private TreasureInventory  treasureInventory;

    public Treasure(String qrPw,
                    Double latitude, Double longitude,
                    LocalDate startDate, LocalDate endDate, String name
                    ) {

        //보물의 이벤트 생성
        Event runningTime = Event.valueOf(startDate, endDate, name);

        //진짜 타겟좌표로 만든 targetPoint 생성
        TargetPoint baseTargetPoint = TargetPoint.valueOf(latitude,longitude);

        Period runningTimePeriod = runningTime.getPeriod();

        List<Mission> missionList = generateMissionList(getMissionCountByRunningTime(runningTimePeriod));

        List<TargetPoint> targetPointList = generateTargetPointList(baseTargetPoint, getHintCount(missionList));

        this.qrCodeVO = QRCode.valueOf(qrPw);

        this.treasureInventory= TreasureInventory.valueOf(runningTime,targetPointList,missionList);

    }

    //RunningTime 으로 날짜 유효성 체크하기
    public int validEvent(){
        return this.treasureInventory.getRunningTime().checkEvent();
    }


    public Boolean isActive(){
        return this.treasureInventory.getRunningTime().isActive();
    }
    public Boolean isWait(){
        return this.treasureInventory.getRunningTime().isWait();
    }
    public Boolean isClose(){
        return this.treasureInventory.getRunningTime().isClose();
    }


    //러닝타임의 따라 풀어야 하는 미션의 개수를 구하는 메소드는 Event 말고 Treasure 에서 만듬
    // 공식 :  1주일에 개의 문제  7일당 5문제
    public int getMissionCountByRunningTime(Period period){
        int runningRangeDays = period.getDays();
        //설명변수
        int missionCount = runningRangeDays  - runningRangeDays/7 * 2;
        return missionCount;
    }


    //미션의 총 hintCount 가져오기
    public int getHintCount(List<Mission> missionList) /*throws IllegalAccessException */{

        AtomicInteger hintCounter = new AtomicInteger();
//
//        if(missionList == null){
//            throw  new  IllegalAccessException("미션리스트가 존재 하지 않습니다.");
//        }

        for (Mission mission : missionList) {
            hintCounter.addAndGet(mission.getHintCounter());
        }

        return hintCounter.get();
    }


    //미션을 생성하는 메소드
    public List<Mission> generateMissionList(int missionCount){

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


    //가짜 TargetPoint 생성
    public List<TargetPoint> generateTargetPointList(TargetPoint realTargetPoint,int hintCount) /*throws IllegalAccessException*/ {

        List<TargetPoint> targetPointList = new ArrayList<>();

        IntStream.range(1,hintCount).forEach(i ->{
            targetPointList.add(realTargetPoint.getFakeTargetPoint());
        });

      return targetPointList;

    }


    public static Treasure valueOf(String qrPw,
                                   Double latitude, Double longitude,
                                   LocalDate startDate, LocalDate endDate, String name) {
        return new Treasure(qrPw,latitude,longitude,startDate,endDate,name);
    }



}
