package io.github.wotjd243.findbyhint.mission.application;

import io.github.wotjd243.findbyhint.MissionInventory.application.MissionInventoryService;
import io.github.wotjd243.findbyhint.MissionInventory.domain.*;
import io.github.wotjd243.findbyhint.MissionInventory.infra.MissionApi;
import io.github.wotjd243.findbyhint.hint.application.HintService;
import io.github.wotjd243.findbyhint.hunter.application.HunterService;
import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.mission.domain.*;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log
public class MissionService {

    // TODO(9) 미션 도전 메소드 만들기

    // TODO(9-3) 미션에 대한 Model을 표현하는 화면을 구성한다.

    private MissionRepository missionRepository;
    private MissionInventoryRepository missionInventoryRepository;
    private MissionInventoryService missionInventoryService;
    private SuccessMissionService successMissionService;
    private TreasureService treasureService;
    private HunterService hunterService;
    private MissionApi missionApi;
    private HintService hintService;

    public MissionService(MissionRepository missionRepository, MissionInventoryRepository missionInventoryRepository, MissionInventoryService missionInventoryService, SuccessMissionService successMissionService, TreasureService treasureService, HunterService hunterService, MissionApi missionApi, HintService hintService) {
        this.missionRepository = missionRepository;
        this.missionInventoryRepository = missionInventoryRepository;
        this.missionInventoryService = missionInventoryService;
        this.successMissionService = successMissionService;
        this.treasureService = treasureService;
        this.hunterService = hunterService;
        this.missionApi = missionApi;
        this.hintService = hintService;
    }


    public int takePoint(final Long missionId) {
        final Mission mission = getMission(missionId);
        return successMissionService.isSuccess();
    }

    private Mission getMission(final Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void missionCall(Model model) throws IOException, IllegalAccessException {

        Hunter hunter = hunterService.findById("testHunter");

        hunter.getHunterPointBullet().getHunterBullet().challengeMissionBullet();

        Optional<MissionInventoryInfo> missionInventoryInfo = missionInventoryService.callMissionApi("testHunter");

        if (missionInventoryInfo.isPresent()) {
            missionInventoryInfo.ifPresent(missionInventory -> model.addAttribute("mission", missionInventory));
        }else{
            throw new IllegalStateException("더이상 도전 할 수있는 미션이 없습니다.");
        }

    }

    public Map<String, Object> missionSubmit(MissionPostDto missionPostDto, Model model) {

        String hunterId = "testHunter";

        Hunter hunter = hunterService.findById(hunterId);
        Mission mission = getMission(missionPostDto.getMissionId());
        int hinCount = mission.getHintCounter();

        boolean sucessCheck = missionPostDto.getAnswer().equals(missionPostDto.getHunterAnswer());
        int plusPoint = 0;

        if(sucessCheck) {
            plusPoint = successMissionService.isSuccess();
            hunterService.pointUpdate(hunter,plusPoint);
            hintService.addHint(hunterId,hinCount);

            Long treasureId = treasureService.getTreasureIdByActive();
            MissionInventory missionInventory = missionInventoryRepository.findByHunterIdAndTreasureId(HunterId.valueOf(hunterId),treasureId).get();
            missionInventory.getMissionBook().getMissionBook().parallelStream()
                    .filter(missionInventoryInfo -> missionInventoryInfo.getMissionId().compareTo(missionPostDto.getMissionId()) ==0)
                    .forEach(MissionInventoryInfo::missionSuccess);
            missionInventoryRepository.save(missionInventory);
        }

        System.out.println("hunterId :: '" + hunterId + "'");
        System.out.println("missionDto :: + " + missionPostDto.toString());

        model.addAttribute("sucessCheck", sucessCheck);
        model.addAttribute("plusPoint", plusPoint);
        model.addAttribute("hunterPoint", hunter.getHunterPointBullet().getHunterPoint());
        model.addAttribute("hunterBullet", hunter.getHunterPointBullet().getHunterBullet());

        Map<String, Object> map = new HashMap<String,Object>();
        if(sucessCheck){
            map.put("status","success");
            map.put("mmm","미션에 성공하였습니다. \n "+plusPoint + "포인트를 획득하셨습니다.");
        }else{
            map.put("status","warning");
            map.put("mmm","미션에 실패하였습니다. ㅜ0ㅜ");
        }
        map.put("url","/");

        return map;
    }

//    public Mission challengeMission(String hunterId) {
//
//        Hunter hunter = hunterService.findById(hunterId);
//
//
//
//    }

}

