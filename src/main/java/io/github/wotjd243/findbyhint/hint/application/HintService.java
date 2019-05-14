package io.github.wotjd243.findbyhint.hint.application;

import io.github.wotjd243.findbyhint.hint.domain.Hint;
import io.github.wotjd243.findbyhint.hint.domain.HintRepository;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//헌터 ID, 보물 ID를 통해서 Hint -> HintInventory 를 가져올 수 있음 => 가지고있는 타겟포인트 ID를 가져올수있음 -- 도메인

//터겟포인트 ID와 HintCounter로 가져와야할 FakeTargetPoint 의 아이디를 List<Long>가져올 수 있음  -- 도메인

//기존의 해당되는 힌트인벤토리에  HintInvenTory 들을 추가한다 -- 도메인

// save()


@Service
public class HintService {
    private HintRepository hintRepository;

    private TreasureService treasureService;

    public HintService(HintRepository hintRepository, TreasureService treasureService) {
        this.hintRepository = hintRepository;
        this.treasureService = treasureService;
    }

    //다른 애그리거트가 가져올
    //힌트르 주는 메소드 (타겟포인트)
    //헌터 ID,  보물 ID, HintCounter 개수

    //TODO (1) : 지워야할 좌표 넘겨주기
    public void addHint(HunterId hunterId,int hintCount){

        // 현재 진행중인  보물 ID
        Long treasureId = treasureService.getTreasureIdByActive();

        //해당 hunterId 와 treasureId 로 ids 를 가져와야함 : 완전한 객체로 가져옴
        Hint hint = findByHunterIdAndHintInventory_TreasureId(hunterId,treasureId);

        List<Long> ids = new ArrayList<>();
        //기존의 있는 아이디 값 가져옴
        if(hint == null){
            hint = Hint.valueOf(hunterId.getHunterId(),treasureId);
        }

        //보물의 가짜좌표 (힌트) hintCount 만큼 가져옴
        List<Long> getIds = treasureService.getTargetPointIds(treasureId,ids,hintCount);

        //가져온 아이디 값 기존의 힘트에 넣어줌
        hint.addBringTargetPointIds(getIds);

        //저장
        hintRepository.save(hint);

    }

    protected Hint findByHunterIdAndHintInventory_TreasureId(HunterId hunterId, Long treasureId) {
        return hintRepository.findByHintMappedIds_HunterIdAndHintMappedIds_TreasureId(hunterId,treasureId);
    }


    // 조회해서 넣어라 넣는 방법은 모르지만 일단 넣어라

    private Hint getHint(final Long hintId) {
        return hintRepository.findById(hintId).orElseThrow(IllegalArgumentException::new);
    }

/*    private HintInventory getHintInventory(final Long trasureId,  List<Long> bringtargetList) {
        return hintRepository.findHintInventory(trasureId,bringtargetList);
    }*/

    // 지워야할 좌표의 집합


}