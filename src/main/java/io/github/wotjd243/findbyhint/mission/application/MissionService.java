package io.github.wotjd243.findbyhint.mission.application;

import io.github.wotjd243.findbyhint.hunter.application.HunterService;
import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.mission.domain.*;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
public class MissionService {

    // TODO(9) 미션 도전 메소드 만들기

    // TODO(9-3) 미션에 대한 Model을 표현하는 화면을 구성한다.

    private MissionRepository missionRepository;
    private HunterService hunterService;

    public MissionService(MissionRepository missionRepository, HunterService hunterService) {
        this.missionRepository = missionRepository;
        this.hunterService = hunterService;
    }

    public int takePoint(final Long missionId) {
        final Mission mission = getMission(missionId);
        final SuccessMissionService successMissionService = new SuccessMissionService(mission);
        return successMissionService.isSuccess();
    }

    private Mission getMission(final Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(IllegalArgumentException::new);
    }

//    public Mission challengeMission(String hunterId) {
//
//        Hunter hunter = hunterService.findById(hunterId);
//
//
//
//    }

}

