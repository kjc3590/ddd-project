package io.github.wotjd243.findbyhint.MissionInventory.application;

import io.github.wotjd243.findbyhint.mission.domain.MissionInfo;
import io.github.wotjd243.findbyhint.mission.domain.MissionInfoRepository;
import io.github.wotjd243.findbyhint.mission.domain.MissionInventory;
import io.github.wotjd243.findbyhint.mission.domain.MissionQnA;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class MissionInfoService {

    MissionInfoRepository missionInfoRepository;
    private TreasureService treasureService;

    public MissionInfoService(MissionInfoRepository missionInfoRepository, TreasureService treasureService) {
        this.missionInfoRepository = missionInfoRepository;
        this.treasureService = treasureService;
    }

    // TODO (1) 미션을 조회할 때 MissionInfo에 missionId가 존재하는지 확인
    public MissionInfo findById(Long missionId) {
        return missionInfoRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("missionId가 존재하지 않습니다."));
    }

    // TODO (2) 미션을 풀었을 때 MissionInfo에 해당 정보 저장하기 (헌터Id, 보물Id, 미션인벤토리, QnA)
    public MissionInfo save(MissionDto missionDto) {

        //보물아이디 가져와서 저장
        Long treasureId = treasureService.getTreasureIdByActive();

        //현재 보물의 미션 갯수중 가장 낮은 난이도의 미션 아이디를 가져온다.
        List<Long> ids = new ArrayList<>();

        Object [] result = treasureService.getMission(treasureId, ids);
        BigInteger id = (BigInteger) result[0];

        MissionInfo missionInfo = MissionInfo.valueOf(missionDto.getHunterId(), treasureId);

        //인벤토리에 데이터 저장
        MissionInventory missionInventory = MissionInventory.valueOf(id);
        log.info("인벤토리에 데이터 저장");

        MissionQnA missionQnA = MissionQnA.valueOf(missionDto.getQuestion(), missionDto.getAnswer());
        log.info("MissionQnA에 데이터 저장");

        log.info("treasureId: " +treasureId);
        log.info("missionId:" +id);
        log.info("missionDto.getHunterId(): "+missionDto.getHunterId());
        log.info("missionDto.getQuestion(): "+missionDto.getQuestion());
        log.info("missionDto.getAnswer(): "+missionDto.getAnswer());
        log.info("missionDto.getSuccess(): "+missionDto.getSuccess());

        MissionInfo result2 = missionInfoRepository.save(missionInfo);

        return missionInfo;
    }


}
