package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyCheckService {

    private TreasureRepository treasureRepository;

    private TreasureEventService treasureEventService;

    public DailyCheckService(TreasureRepository treasureRepository, TreasureEventService treasureEventService) {
        this.treasureRepository = treasureRepository;
        this.treasureEventService = treasureEventService;
    }

    public DailyCheckService() {
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

            treasureEventService.sendEndMessage();

        }
    }

    //현재 대기중인 보물이 있는지 확인
    private void checkWaitTreasure() {
        List<Treasure> waitTreasureGroup = treasureRepository.findByTreasureInventoryByWait();
        waitTreasureGroup.forEach(treasure -> {
            //있다면 날짜가 유효한지 체크하기
            int validNumber = treasure.validEvent();
            //오늘이 시작일이라면 진행중으로 상태 변경하기
            if(validNumber == 0){
                treasure.activeEvent();
                treasureRepository.save(treasure);

                treasureEventService.sendStartMessage();

            }
        });
    }

}
