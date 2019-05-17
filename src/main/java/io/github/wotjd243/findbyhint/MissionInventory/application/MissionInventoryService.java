package io.github.wotjd243.findbyhint.MissionInventory.application;

import io.github.wotjd243.findbyhint.MissionInventory.domain.*;
import io.github.wotjd243.findbyhint.MissionInventory.infra.MissionApi;
import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class MissionInventoryService {

    // TODO(9-1)지금 풀어야하는 미션 Id 가져오기 (Mission)

    private MissionInventoryRepository missionInventoryRepository;
    private TreasureService treasureService;
    private MissionApi missionApi;

    public MissionInventoryService(MissionInventoryRepository missionInventoryRepository, TreasureService treasureService, MissionApi missionApi) {
        this.missionInventoryRepository = missionInventoryRepository;
        this.treasureService = treasureService;
        this.missionApi = missionApi;
    }

//    // TODO (1) 미션을 조회할 때 MissionInfo에 missionId가 존재하는지 확인
//    public MissionInventory findById(Long missionId) {
//        return missionInventoryRepository.findById(missionId)
//                .orElseThrow(() -> new IllegalArgumentException("missionId가 존재하지 않습니다."));
//    }


    // TODO (2) 미션을 호출할 때 MissionInventory에 해당 정보 저장하기 (헌터Id, 보물Id)
    public Optional<MissionInventory> callMissionApi(String hunterId) throws IOException, IllegalAccessException {

        Long treasureId = treasureService.getTreasureIdByActive();

        System.out.println("treasureId :: '" + treasureId + "'");
        System.out.println("hunterId :: '" + hunterId + "'");


        //루트 에그리거트 가져오기
        Optional<MissionInventory> optionalMissionInventory = missionInventoryRepository.findByHunterIdAndTreasureId(HunterId.valueOf(hunterId), treasureId);

        List<MissionInventoryInfo> infoList = missionIdList(hunterId, treasureId);

        //현재 미션이 가지고있는 missionId 들
        List<Long> ids = missionListToLongList(infoList);

        // TODO (4) 문제는 쉬운 문제부터 -> 어려운 문제로 나와야 함
        //풀어야할 미션 가져오기
        Optional<Mission> result = treasureService.getMission(treasureId, ids);
        MissionInventoryInfo missionInventoryInfo = null;
        //미션이 있다면
        if (result.isPresent()) {
            final Optional<MissionInventoryInfo> byMission = missionApi.findByMission(result.get());
            if (byMission.isPresent()) {
                missionInventoryInfo = byMission.get();
            }
        }

        MissionInventory missionInventory;

        if(optionalMissionInventory.isPresent()){
            missionInventory = optionalMissionInventory.get();
        }else{
            missionInventory = MissionInventory.valueOf(hunterId,treasureId);
        }


        if(missionInventoryInfo != null){
            MissionBook missionBook = missionInventory.getMissionBook();
            if(missionBook == null){
                missionBook = new MissionBook();
            }
            missionBook.addMissionBook(missionInventoryInfo);
            missionInventory.setMissionBook(missionBook);
            missionInventory = missionInventoryRepository.save(missionInventory);
        }

        return Optional.ofNullable(missionInventory);

    }



    public List<Long> missionListToLongList(List<MissionInventoryInfo> ids) {

        List<Long> list = new ArrayList<>();

        for (MissionInventoryInfo info : ids) {
            System.out.println("info.getMissionId() :: '" + info.getMissionId() + "'");
            list.add(info.getMissionId());
        }

        return list;
    }


    public List<MissionInventoryInfo> missionIdList(String hunterId, Long treasureId) {

//        MissionInventory missionInventory = missionInventoryRepository.findByHunterIdAndTreasureId(HunterId.valueOf(hunterId), treasureId).get();
//        List<MissionInventoryInfo> list = missionInventory.getMissionBook().getMissionBook();
        List<MissionInventoryInfo> list = new ArrayList<>();

        Optional<MissionInventory> byHunterIdAndTreasureId = missionInventoryRepository.findByHunterIdAndTreasureId(HunterId.valueOf(hunterId), treasureId);
        if (byHunterIdAndTreasureId.isPresent()) {
            list = byHunterIdAndTreasureId.get().getMissionBook().getMissionBook();
        }

        return list;

    }

}
