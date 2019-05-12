//package io.github.wotjd243.findbyhint.mission.application;
//
//import io.github.wotjd243.findbyhint.mission.domain.Mission;
//import io.github.wotjd243.findbyhint.mission.domain.MissionRepository;
//import io.github.wotjd243.findbyhint.mission.domain.SuccessMissionService;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//// todo (1) ::  1. 미션을 생성할떄 문제랑 답을 넣어 놓으냐 / 2. 미션을 풀때마다 문제랑 답을 api로 가져오느냐
//// todo  => 2번 선택 : 문제가 랜덤으로 가져와야하고
//// todo (2) :: 답을 어떻게 보여줘야 할지  1. VO로 만든다.
//
//@Service
//public class MissionService {
//    private MissionRepository missionRepository;
//
//    public MissionService(final MissionRepository missionRepository) {
//        this.missionRepository = missionRepository;
//    }
//
//    public int takePoint(final Long missionId) throws IOException{
//        final Mission mission = getMission(missionId);
//        final SuccessMissionService successMissionService = new SuccessMissionService(mission);
//        return successMissionService.isSuccess();
//    }
//
//    private Mission getMission(final Long missionId) throws IOException {
//        return missionRepository.findById(missionId)
//                .orElseThrow(IllegalArgumentException::new);
//    }
//
//}
//
