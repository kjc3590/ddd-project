package io.github.wotjd243.findbyhint.hint.application;

import org.springframework.stereotype.Service;



@Service
public class HintService {

    //다른 애그리거트가 가져올
    //힌트르 주는 메소드 (타겟포인트)

    //헌터 ID,  보물 ID, HintCounter 개수

    //TODO (1) :
    public void addHint(){

        //헌터 ID, 보물 ID를 통해서 Hint -> HintInventory 를 가져올 수 있음 => 가지고있는 타겟포인트 ID를 가져올수있음 -- 도메인

        //터겟포인트 ID와 HintCounter로 가져와야할 FakeTargetPoint 의 아이디를 List<Long>가져올 수 있음  -- 도메인

        //기존의 해당되는 힌트인벤토리에  HintInvenTory 들을 추가한다 -- 도메인

        // save()

    }

    // 지워야할 좌표의 집합


}