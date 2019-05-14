package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.treasure.domain.TargetPoint;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureInventory;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import io.github.wotjd243.findbyhint.util.VO.Distinguish;
import io.github.wotjd243.findbyhint.util.VO.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
//굳이 생성자를 만들지 않아도 해당 어노테이션으로 해결을 할 수 있는 것 같다.
@RequiredArgsConstructor
@Log
public class TreasureService {

    private final TreasureRepository treasureRepository;

    //보물을 등록하는 메소드
    public Treasure save(TreasureRequestDto treasureRequestDto) {

        Treasure treasure = Treasure.valueOf(
                treasureRequestDto.getQrPw()
        );

        //진짜 타겟좌표로 만든 targetPoint 생성
        TargetPoint baseTargetPoint = TargetPoint.valueOf(treasureRequestDto.getLatitude(),treasureRequestDto.getLongitude());
        //연관관계 셋팅
        baseTargetPoint.setTreasure(treasure);

//        log.info("진짜 타겟좌표로 만든 targetPoint 생성");

        Event runningTime = Event.valueOf(treasureRequestDto.getStartDate(), treasureRequestDto.getEndDate(), treasureRequestDto.getName());
        Period runningTimePeriod = runningTime.getPeriod();

        List<Mission> missionList = generateMissionList(getMissionCountByRunningTime(runningTimePeriod),treasure);

//        log.info("미션 리스트 생성");

        List<TargetPoint> targetPointList = generateTargetPointList(treasure, getHintCount(missionList));
        targetPointList.add(baseTargetPoint);

//        log.info("TargetPoint 리스트 생성");

        TreasureInventory treasureInventory = TreasureInventory.valueOf(runningTime,targetPointList,missionList);
        treasure.setTreasureInventory(treasureInventory);

//        treasure.activeEvent();

//        log.info("TreasureInventory 리스트 생성");
        Treasure result = treasureRepository.save(treasure);
        // TODO (*) test 코드 상에서는 왜 result 가 null 이 나오는지 잘 모르겠다.

        //log.info("result :: "+ result );

        return treasure;

    }

    // COMPLETED 현재 진행중인 보불아이디 값 가져오기
    public Long getTreasureIdByActive(){
        Treasure treasure =  treasureRepository.findByTreasureInventoryByActive();
        if(treasure != null){
            return treasure.getTreasureId();
        }else{
            return null;
        }
    }

    //TODO (2) 미션 관련정보 넘겨주기
    public Mission getMission(Long treasureId, List<Long> ids){
        Object[] result= treasureRepository.findMission(treasureId,ids);
        Object[] result2 = (Object[])result[0];

        Long missionId= (Long)result2[0];
        MissionLevel missionLevel = (MissionLevel)result2[1];

        Mission mission = new Mission(missionLevel);
        mission.setMissionId(missionId);

        return mission;
    }

    //TODO (3) 힌트 관련 정보 넘겨주기
    public List<Long> getTargetPointIds(Long treasureId, List<Long> ids,int hintCount){
        return treasureRepository.findTargetPointIds(treasureId,ids,hintCount);
    }


    //보물 가져오는
    public Treasure getTreasure(Long treasureId) {
        return treasureRepository.findById(treasureId).orElseThrow(IllegalArgumentException::new);
    }

    public boolean isExist(Long treasureId) {
        return !ObjectUtils.isEmpty(getTreasure(treasureId));
    }


    public void checkTreasure() {
        checkActiveTreasure();
        checkWaitTreasure();
    }

    //현재 진행중인 보물이 있는지 확인
    private void checkActiveTreasure() {
        Treasure activeTreasure = treasureRepository.findByTreasureInventoryByActive();
        int validNumber = activeTreasure.validEvent();
        if(validNumber == 1){
            activeTreasure.closeEvent();
            treasureRepository.save(activeTreasure);
        }
    }

    //현재 대기중인 보물이 있는지 확인
    private void checkWaitTreasure() {
        List<Treasure>  waitTreasureGroup = treasureRepository.findByTreasureInventoryByWait();
        waitTreasureGroup.forEach(treasure -> {
            //있다면 날짜가 유효한지 체크하기
            int validNumber = treasure.validEvent();
            //오늘이 시작일이라면 진행중으로 상태 변경하기
            if(validNumber == 0){
                treasure.activeEvent();
                treasureRepository.save(treasure);
            }
        });
    }


    //=============================================================================================//
    //미션의 총 hintCount 가져오기
    private int getHintCount(List<Mission> missionList) /*throws IllegalAccessException */{

        AtomicInteger hintCounter = new AtomicInteger();

        for (Mission mission : missionList) {
            hintCounter.addAndGet(mission.getHintCounter());
        }

        return hintCounter.get();
    }


    //미션을 생성하는 메소드
    private List<Mission> generateMissionList(int missionCount, Treasure treasure){

        final List<Mission> missionList = new ArrayList<>();
        int missionLevelCount = MissionLevel.size();

        for (int i = missionLevelCount; i > 0; i--) {
            for (int i2 = 1; i2 <= i; i2++) {
                Mission mission;
                switch (i2) {
                    case 1:
                        mission = new Mission(MissionLevel.BRONZE);
                        mission.setTreasure(treasure);
                        missionList.add(mission);

                        break;
                    case 2:
                        mission = new Mission(MissionLevel.SILVER);
                        mission.setTreasure(treasure);
                        missionList.add(mission);
                        break;
                    case 3:
                        mission = new Mission(MissionLevel.GOLD);
                        mission.setTreasure(treasure);
                        missionList.add(mission);
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

    //러닝타임의 따라 풀어야 하는 미션의 개수를 구하는 메소드는 Event 말고 Treasure 에서 만듬
    // 공식 :  1주일에 개의 문제  7일당 5문제
    private int getMissionCountByRunningTime(Period period){
        int runningRangeDays = period.getDays();
        //설명변수
        int missionCount = runningRangeDays  - runningRangeDays/7 * 2;
        return missionCount;
    }



    //가짜 TargetPoint 생성
    private List<TargetPoint> generateTargetPointList(Treasure treasure, int hintCount) {

        List<TargetPoint> targetPointList = new ArrayList<>();

        IntStream.range(1,hintCount).forEach(i ->{
            targetPointList.add(getFakeTargetPoint(treasure));
        });

        return targetPointList;

    }
    private TargetPoint getFakeTargetPoint(Treasure treasure) {
        Random random = new Random();
        Double latitude = 33 + random.nextInt(10) + random.nextDouble();
        Double longitude = 124 + random.nextInt(8) + random.nextDouble();
        TargetPoint targetPoint = new TargetPoint(latitude, longitude, Distinguish.FAKE);
        targetPoint.setTreasure(treasure);
        return targetPoint;
    }

    //=============================================================================================//


}
