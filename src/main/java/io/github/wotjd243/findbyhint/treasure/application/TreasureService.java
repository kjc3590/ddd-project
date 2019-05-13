package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
//굳이 생성자를 만들지 않아도 해당 어노테이션으로 해결을 할 수 있는 것 같다. ,
@RequiredArgsConstructor
@Log
public class TreasureService {
//
//    private final TreasureRepository treasureRepository;
//
//    //TODO (1) 현재 진행중인 보불아이디 값 가져오기
//    public Long getTreasureIdByActive(){
//        Treasure treasure =  treasureRepository.findByTreasureInventory();
//        if(treasure != null){
//            return treasure.getTreasureId();
//        }else{
//            return null;
//        }
//    }
//
//    //TODO (2) 미션 관련정보 넘겨주기
//    public Mission getMission(Long treasureId, List<Long> ids){
//        return treasureRepository.findMission(treasureId,ids);
//    }
//
//    //TODO (3) 힌트 관련 정보 넘겨주기
//    public List<Long> getTargetPointIds(Long treasureId, List<Long> ids){
//        return treasureRepository.findTargetPointIds(treasureId,ids);
//    }
//
//
//    //보물을 등록하는 메소드
//    public void save(TreasureRequestDto treasureRequestDto) {
//
//        Treasure treasure = Treasure.valueOf(
//                treasureRequestDto.getQrPw(),
//                treasureRequestDto.getLatitude(),treasureRequestDto.getLongitude(),
//                treasureRequestDto.getStartDate(), treasureRequestDto.getEndDate(),
//                treasureRequestDto.getName()
//        );
//
//        treasureRepository.save(treasure);
//    }
//
//    //보물 가져오는
//    private Treasure getTreasure(Long treasureId) {
//        return treasureRepository.findById(treasureId).orElseThrow(IllegalArgumentException::new);
//    }
//
//    public boolean isExist(Long treasureId) {
//        return !ObjectUtils.isEmpty(getTreasure(treasureId));
//    }
//
//    public void checkTreasure() {
//
//        checkActiveTreasure();
//
//        checkWaitTreasure();
//    }
//
//    //현재 진행중인 보물이 있는지 확인
//    private void checkActiveTreasure() {
//        Treasure activeTreasure = treasureRepository.findByTreasureInventory();
//        int validNumber = activeTreasure.validEvent();
//        if(validNumber == 1){
//            activeTreasure.isClose();
//            treasureRepository.save(activeTreasure);
//        }
//    }
//
//    //현재 대기중인 보물이 있는지 확인
//    private void checkWaitTreasure() {
//        List<Treasure>  waitTreasureGroup = treasureRepository.findByTreasureInventory_RunningTime_Status_Wait();
//        waitTreasureGroup.forEach(treasure -> {
//            //있다면 날짜가 유효한지 체크하기
//            int validNumber = treasure.validEvent();
//            //오늘이 시작일이라면 진행중으로 상태 변경하기
//            if(validNumber == 0){
//                treasure.isActive();
//                treasureRepository.save(treasure);
//            }
//        });
//    }

}
