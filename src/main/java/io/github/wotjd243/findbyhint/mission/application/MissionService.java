package io.github.wotjd243.findbyhint.mission.application;

import io.github.wotjd243.findbyhint.MissionInventory.application.MissionInventoryService;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionInventoryInfo;
import io.github.wotjd243.findbyhint.hunter.application.HunterService;
import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.mission.domain.*;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Optional;

@Service
@Log
public class MissionService {

    // TODO(9) 미션 도전 메소드 만들기

    // TODO(9-3) 미션에 대한 Model을 표현하는 화면을 구성한다.

    private MissionRepository missionRepository;
    private MissionInventoryService missionInventoryService;
    private SuccessMissionService successMissionService;
    private HunterService hunterService;

    public MissionService(MissionRepository missionRepository, MissionInventoryService missionInventoryService, SuccessMissionService successMissionService, HunterService hunterService) {
        this.missionRepository = missionRepository;
        this.missionInventoryService = missionInventoryService;
        this.successMissionService = successMissionService;
        this.hunterService = hunterService;
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

//    public Mission challengeMission(String hunterId) {
//
//        Hunter hunter = hunterService.findById(hunterId);
//
//
//
//    }

}

