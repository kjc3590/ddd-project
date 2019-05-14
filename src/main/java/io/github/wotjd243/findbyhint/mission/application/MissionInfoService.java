package io.github.wotjd243.findbyhint.mission.application;

import io.github.wotjd243.findbyhint.mission.domain.MissionInfo;
import io.github.wotjd243.findbyhint.mission.domain.MissionInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class MissionInfoService {

    MissionInfoRepository missionInfoRepository;

    public MissionInfoService(MissionInfoRepository missionInfoRepository) {
        this.missionInfoRepository = missionInfoRepository;
    }

    // todo (1) 미션을 풀었을 때 MissionInfo에 해당 정보 저장하기 (헌터Id, 보물Id, 미션인벤토리)
    public void missionInfoInsert(MissionInfo missionInfo) {
        missionInfoRepository.save(missionInfo);
    }

    // todo (1) 미션을 조회할 때 MissionInfo에 missionId가 존재하는지 확인
    public MissionInfo findById(Long missionId) {
        return missionInfoRepository.findById(missionId).get();
    }
}
