package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.treasure.domain.*;
import io.github.wotjd243.findbyhint.util.VO.Distinguish;
import io.github.wotjd243.findbyhint.util.VO.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Target;
import java.math.BigInteger;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.wotjd243.findbyhint.treasure.domain.Coordinates.*;
import static java.util.Comparator.comparing;

@Service
@Log
public class TreasureService {

    private final TreasureRepository treasureRepository;

    public TreasureService(TreasureRepository treasureRepository) {
        this.treasureRepository = treasureRepository;
    }

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

        // TODO 활동중으로 임이 변경
        treasure.activeEvent();

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
            throw new IllegalArgumentException("진행중인 보물이 없습니다.");
        }
    }

    // COMPLETED 미션 관련정보 넘겨주기
    public Optional<Mission> getMission(Long treasureId, List<Long> ids){
        Optional<Treasure> optionalTreasure = treasureRepository.findById(treasureId);
        Mission mission = null;
        if(optionalTreasure.isPresent()){
            Treasure treasure = optionalTreasure.get();
            Optional<Mission> omission = treasure.getTreasureInventory().getMissionList()
                    .parallelStream()
                    .filter(d -> !ids.contains(d.getMissionId()))
                    .min(comparing(Mission::getMissionLevel));

            if (omission.isPresent()) {
                mission = omission.get();
            }else{
                throw new IllegalArgumentException("No Mission!");
            }
        }
        return Optional.ofNullable(mission);

    }
    // COMPLETED 힌트받은거 제외하고 모든 타겟 포인트 넘기기
    public List<TargetPoint> getTargetPointByIds(Long treasureId, List<Long> ids){
        Optional<Treasure> optionalTreasure = treasureRepository.findById(treasureId);
        List<TargetPoint> result = null;

        if(optionalTreasure.isPresent()){
            result = optionalTreasure.get().getTreasureInventory().getTargetPointList()
                    .parallelStream()
                    .filter(targetPoint -> !ids.contains(targetPoint.getTargetPointId()))
                    .collect(Collectors.toList());
        }

        return result;
    }

    // COMPLETED 힌트 관련 정보 넘겨주기
    public List<Long> getTargetPointIds(Long treasureId, List<Long> ids,int hintCount){
        //treasureRepository.findTargetPointIds(treasureId,ids,hintCount);
        Optional<Treasure> optionalTreasure = treasureRepository.findById(treasureId);
        List<Long> results = null;

        if(optionalTreasure.isPresent()){

            results = optionalTreasure.get().getTreasureInventory().getTargetPointList()
                    .parallelStream()
                    .filter(targetPoint -> !ids.contains(targetPoint.getTargetPointId()))
                    .filter(targetPoint -> targetPoint.getDistinguish().equals(Distinguish.FAKE))
                    .limit(hintCount)
                    .map(TargetPoint::getTargetPointId)
                    .collect(Collectors.toList());

        }
        return results;
    }


    //보물 가져오는
    public Treasure getTreasure(Long treasureId) {
        return treasureRepository.findById(treasureId).orElseThrow(IllegalArgumentException::new);
    }

    public boolean isExist(Long treasureId) {
        return !ObjectUtils.isEmpty(getTreasure(treasureId));
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

        IntStream.range(0,hintCount).forEach(i ->{
            targetPointList.add(getFakeTargetPoint(treasure));
        });

        return targetPointList;

    }
    private TargetPoint getFakeTargetPoint(Treasure treasure) {
        Random random = new Random();


        Double latitude = Coordinates.MIN_LATITUDE_VALUE + random.nextInt(LATITUDE_DIFFERENCE()) + random.nextDouble();
        Double longitude = Coordinates.MIN_LONGITUDE_VALUE + random.nextInt(LONGITUDE_DIFFERENCE()) + random.nextDouble();
        TargetPoint targetPoint = new TargetPoint(latitude, longitude, Distinguish.FAKE);
        targetPoint.setTreasure(treasure);
        return targetPoint;
    }

    //=============================================================================================//


}
