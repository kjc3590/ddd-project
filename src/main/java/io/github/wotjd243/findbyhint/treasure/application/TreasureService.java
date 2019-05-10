package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.treasure.domain.RunningTime;
import io.github.wotjd243.findbyhint.treasure.domain.TargetPoint;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
//굳이 생성자를 만들지 않아도 해당 어노테이션으로 해결을 할 수 있는 것 같다. ,
@RequiredArgsConstructor
@Log
public class TreasureService {

    private final TreasureRepository treasureRepository;

    //보물을 등록하는 메소드
    public void save(Treasure treasure){

        // todo (1) ::  뭔가 메소드로 만들 수 있을 것 같다.
        // todo (1-1) :: 현재 힌트 카운터와 미션을 동시에 생성시키고있다. => 분리해야함
        // todo (1-2) :: 미션 생성하는 메소드 generateMission
        // todo => Treasure 가 자신을 찾기 위한 List<Mission> 을 만드는 거니까 Treasure 에 넣는게 좋을것같다.
        // todo (1-3) :: 힌트 카운터 세는 거 메소드 만들기
        // todo => Treasure 의 TargetPoint 를 만들기 위함이니 Treasure 에서 만드는게 좋을 것 같다.


        //generateMission
        treasure.generateMissionList();

        // todo 미완성 부분
        //generateTargetPoint
        //treasure.generateTargetPointList();


        // todo (3) :: 완전한 객체가 되었는지 확인 해주는 메서드

        treasureRepository.save(treasure);

    }

    private Treasure getTreasure(Long treasureId) {
        return treasureRepository.findById(treasureId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isExist(Long treasureId) {
        return !ObjectUtils.isEmpty(getTreasure(treasureId));
    }

}
