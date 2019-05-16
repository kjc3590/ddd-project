package io.github.wotjd243.findbyhint.MissionInventory.application;

import io.github.wotjd243.findbyhint.MissionInventory.domain.*;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class MissionInventoryService {

    private MissionInventoryRepository missionInventoryRepository;
    private TreasureService treasureService;

    public MissionInventoryService(MissionInventoryRepository missionInventoryRepository, TreasureService treasureService) {
        this.missionInventoryRepository = missionInventoryRepository;
        this.treasureService = treasureService;
    }

    // TODO (1) 미션을 조회할 때 MissionInfo에 missionId가 존재하는지 확인
    public MissionInventory findById(Long missionId) {
        return missionInventoryRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("missionId가 존재하지 않습니다."));
    }


    // TODO (2) 미션을 풀었을 때 MissionInfo에 해당 정보 저장하기 (헌터Id, 보물Id, 미션인벤토리, QnA)
        public MissionInventory save(MissionDto missionDto) {

        //보물아이디 가져와서 저장
        Long treasureId = treasureService.getTreasureIdByActive();

        //현재 보물의 미션 갯수중 가장 낮은 난이도의 미션 아이디를 가져온다.
        List<Long> ids = new ArrayList<>();

        Object [] result = treasureService.getMission(treasureId, ids);
        BigInteger id = (BigInteger) result[0];

        //임시로 헌터아이디 생성
        HunterId hunterId = HunterId.valueOf("aa");

        //인벤토리에 데이터 저장
        MissionInventory missionInventory = MissionInventory.valueOf(hunterId, treasureId);
        log.info("인벤토리에 데이터 저장");

        List<MissionInventoryInfo> missionInventoryInfoList = new ArrayList<>();
        MissionInventoryInfo missionInventoryInfo = new MissionInventoryInfo(id, missionDto.getQuestion(), missionDto.getAnswer());
        missionInventoryInfoList.add(missionInventoryInfo);
        MissionBook missionBook = new MissionBook();
        missionBook.setMissionBook(missionInventoryInfoList);

        missionInventory.setMissionBook(missionBook);


        log.info("treasureId: " +treasureId);
        log.info("missionId:" +id);
        log.info("missionDto.getHunterId(): "+missionDto.getHunterId());
        log.info("missionDto.getQuestion(): "+missionDto.getQuestion());
        log.info("missionDto.getAnswer(): "+missionDto.getAnswer());
        log.info("missionDto.getSuccess(): "+missionDto.getSuccess());

        MissionInventory result2 = missionInventoryRepository.save(missionInventory);

        return missionInventory;
    }


}
