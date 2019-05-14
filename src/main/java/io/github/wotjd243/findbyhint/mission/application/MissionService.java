package io.github.wotjd243.findbyhint.mission.application;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionInfo;
import io.github.wotjd243.findbyhint.mission.domain.MissionRepository;
import io.github.wotjd243.findbyhint.mission.domain.SuccessMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MissionService {

    private MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
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

}

