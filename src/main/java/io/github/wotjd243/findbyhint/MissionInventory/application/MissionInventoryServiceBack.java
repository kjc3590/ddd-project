//package io.github.wotjd243.findbyhint.MissionInventory.application;
//
//import io.github.wotjd243.findbyhint.MissionInventory.domain.*;
//import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
//import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
//import lombok.extern.java.Log;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Log
//public class MissionInventoryServiceBack {
//
//    // TODO(9-1)지금 풀어야하는 미션 Id 가져오기 (Mission)
//
//    private MissionInventoryRepository missionInventoryRepository;
//    private TreasureService treasureService;
//
//    public MissionInventoryServiceBack(MissionInventoryRepository missionInventoryRepository, TreasureService treasureService) {
//        this.missionInventoryRepository = missionInventoryRepository;
//        this.treasureService = treasureService;
//    }
//
////    // TODO (1) 미션을 조회할 때 MissionInfo에 missionId가 존재하는지 확인
////    public MissionInventory findById(Long missionId) {
////        return missionInventoryRepository.findById(missionId)
////                .orElseThrow(() -> new IllegalArgumentException("missionId가 존재하지 않습니다."));
////    }
//
//
//    // TODO (2) 미션을 호출할 때 MissionInventory에 해당 정보 저장하기 (헌터Id, 보물Id)
//        public MissionInventory save(MissionDto missionDto) {
//
//
//        //임시로 헌터아이디 생성
//        HunterId hunterId = HunterId.valueOf("testHunter");
//
//        //인벤토리에 데이터 저장
//        MissionInventory missionInventory = MissionInventory.valueOf(hunterId, treasureId);
//        log.info("인벤토리에 데이터 저장");
//
//        //MissionBook에 데이터 저장
//        infoSave(id, missionInventory, missionDto);
//
//        log.info("treasureId: " +treasureId);
//            log.info("missionId:" +id);
//            log.info("missionDto.getHunterId(): "+hunterId);
//            log.info("missionDto.getQuestion(): "+missionDto.getQuestion());
//            log.info("missionDto.getAnswer(): "+missionDto.getAnswer());
////        log.info("missionDto.getSuccess(): "+missionDto.getSuccess());
//
//        MissionInventory result2 = missionInventoryRepository.save(missionInventory);
//
//        return result2;
//}
//
//    // TODO (2) 미션을 호출할 때 MissionInventory에 해당 정보 저장하기 (MissionBook)
//    public List<MissionInventoryInfo> infoSave(Long id, MissionInventory missionInventory, MissionDto missionDto) {
//
//        List<MissionInventoryInfo> missionInventoryInfoList = new ArrayList<>();
//
//        MissionInventoryInfo missionInventoryInfo = new MissionInventoryInfo(id, missionDto.getQuestion(), missionDto.getAnswer(), MissionSuccessStatus.FAIL);
//        missionInventoryInfoList.add(missionInventoryInfo);
//        MissionBook missionBook = new MissionBook();
//        missionBook.setMissionBook(missionInventoryInfoList);
//
//        missionInventory.setMissionBook(missionInventoryInfoList);
//
//        return missionInventoryInfoList;
//    }
//
//    public List<Long> missionListToLongList(List<MissionInventoryInfo> ids) {
//
//        List<Long> list = new ArrayList<>();
//
//        for (MissionInventoryInfo info : ids) {
//            list.add(info.getMissionId());
//        }
//
//        return list;
//    }
//
//
//    public List<MissionInventoryInfo> missionIdList(String hunterId, Long treasureId) {
//
////        MissionInventory missionInventory = missionInventoryRepository.findByHunterIdAndTreasureId(HunterId.valueOf(hunterId), treasureId).get();
////        List<MissionInventoryInfo> list = missionInventory.getMissionBook().getMissionBook();
//        List<MissionInventoryInfo> list = new ArrayList<>();
//
//        Optional<MissionInventory> byHunterIdAndTreasureId = missionInventoryRepository.findByHunterIdAndTreasureId(HunterId.valueOf(hunterId), treasureId);
//        if(byHunterIdAndTreasureId.isPresent()){
//            list = byHunterIdAndTreasureId.get().getMissionBook().getMissionBook();
//        }
//
//        return list;
//
//    }
//
//}
