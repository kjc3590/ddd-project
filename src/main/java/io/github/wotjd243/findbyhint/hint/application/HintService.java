package io.github.wotjd243.findbyhint.hint.application;

import io.github.wotjd243.findbyhint.hint.domain.Hint;
import io.github.wotjd243.findbyhint.hint.domain.HintInventory;
import io.github.wotjd243.findbyhint.hint.domain.HintRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

//헌터 ID, 보물 ID를 통해서 Hint -> HintInventory 를 가져올 수 있음 => 가지고있는 타겟포인트 ID를 가져올수있음 -- 도메인

//터겟포인트 ID와 HintCounter로 가져와야할 FakeTargetPoint 의 아이디를 List<Long>가져올 수 있음  -- 도메인

//기존의 해당되는 힌트인벤토리에  HintInvenTory 들을 추가한다 -- 도메인

// save()


@Service
public class HintService {
    private HintRepository hintRepository;

    public HintService(final HintRepository hintRepository) {
        this.hintRepository = hintRepository;
    }
    //다른 애그리거트가 가져올
    //힌트르 주는 메소드 (타겟포인트)
    //헌터 ID,  보물 ID, HintCounter 개수

    //TODO (1) : 지워야할 좌표 넘겨주기
    public void addHint(final Long treasureId){
        // Treasure 서비스가 해야할 일
        int hintCounter = 2;
        List<Long> fakeTargetIds = new ArrayList<>();
        IntStream.range(1,hintCounter).forEach(i ->{
            fakeTargetIds.add(1L);
            fakeTargetIds.add(2L);
        });

/*
        hintRepository.save(fakeTargetIds);
*/


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