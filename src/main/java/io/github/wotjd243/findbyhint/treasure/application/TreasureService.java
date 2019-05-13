package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
//굳이 생성자를 만들지 않아도 해당 어노테이션으로 해결을 할 수 있는 것 같다. ,
@RequiredArgsConstructor
@Log
public class TreasureService {

    private final TreasureRepository treasureRepository;

    //보물을 등록하는 메소드
    public void save(TreasureRequestDto treasureRequestDto) {

        Treasure treasure = Treasure.valueOf(
                treasureRequestDto.getQrPw(),
                treasureRequestDto.getLatitude(),treasureRequestDto.getLongitude(),
                treasureRequestDto.getStartDate(), treasureRequestDto.getEndDate(),
                treasureRequestDto.getName()
        );

        treasureRepository.save(treasure);
    }

    //보물 가져오는
    private Treasure getTreasure(Long treasureId) {
        return treasureRepository.findById(treasureId).orElseThrow(IllegalArgumentException::new);
    }

    public boolean isExist(Long treasureId) {
        return !ObjectUtils.isEmpty(getTreasure(treasureId));
    }

//TODO (1) 현재 진행중인 보물이 있는지 확인 : findActiveTreasure() 메소드
//TODO (1 -1) 있다면 날짜가 유효한지 확인 날짜가 지났다면 : validEvent() 메소드만들기
//TODO (1 -2)  완료로 상태 변경하기 : closeTreasure() 메소드
//TODO (2) 대기중인 보물이 있는지 확인 : 메소드만들기
//TODO (2-1) 시작일 맞다면 진행중으로 상태 변경하기 activeTreasure() 메소드
    public void checkTreasure() {

        //현재 진행중인 보물이 있는지 확인
        Treasure activeTreasure = treasureRepository.findByTreasureInventory_RunningTime_Status_Active();
        if(activeTreasure != null && activeTreasure.isActive()){
            //있다면 날짜가 유효한지 체크하기
            int validNumber = activeTreasure.validEvent();
            if(validNumber == 1){
                //날짜가 지났다면 완료 상태로 만들기
                activeTreasure.isClose();
                treasureRepository.save(activeTreasure);
            }
        }

        //현재 대기중인 보물이 있는지 확인
        List<Treasure>  waitTreasureGroup = treasureRepository.findByTreasureInventory_RunningTime_Status_Wait();
        waitTreasureGroup.forEach(treasure -> {
            //있다면 날짜가 유효한지 체크하기
            int validNumber = treasure.validEvent();
            //오늘이 시작일이라면 진행중으로 상태 변경하기
            if(validNumber == 0){
                treasure.isActive();
                treasureRepository.save(activeTreasure);
            }
        });
    }
}
