package io.github.wotjd243.findbyhint.hint.domain;
//TODO 1. 힌트 에그리거트는 헌터ID, 보물ID, 좌표값, 순서로 이루어져 있다.
//TODO 2. Treasure에 해당하는 Tartgetpoint를 가져오는 메서드  ( HintService에서 Delete 메서드 실행시 보물의 좌표를 제외하고 지워야함 )
//TODO 3. 미션을 맞출 때 마다 Mission Level에 해당하는 수 만큼의 Tagetpoint List를 힌트로 가져온다.  distinguish 가 1이어야함



//TODO EXAM       미션이 완료됨
//TODO EXAM       미션의 난이도에 따른 Treasure-TargetPoint 좌표의 PK값을 힌트애그리거트 내부 bringTargetPoint List에 ADD
//TODO EXAM       * 힌트의 도메인서비스에서 보물 도메인서비스로 넘겨주는 기능*
//TODO EXAM        Hint 에그리거트의 bringTargetPoint 의 값을 제외하고 Treasure-TargetPoint 좌표값을 Treasure서비스에 리턴 Treasure서비스에서 VO를 만들어서 헌터 맵보여줌
//TODO EXAM       테스트 코드 확인하기


import io.github.wotjd243.findbyhint.hunter.domain.HunterId;

import java.util.List;

public class
Hint {
    // 힌트 엔티티의 PK  보물 "" 에 해당하는 힌트값을 찾을 때 유용
    private Long hintId;

    private final HunterId hunterId;

    private final HintEffect hintEffect;

    // 매개변수는 모두가 아는 타입으로 설정 -> 매개변수를 토대로 VO 가져오기
    public Hint(String hunterId, Long treasureId, List<Long> bringtargetId){

        validation(bringtargetId);
        //hintEffect 를 생성하고 -> BringTarget 생성
        this.hunterId = new HunterId(hunterId);
        this.hintEffect = new HintEffect(treasureId , BringTarget.valueOf(bringtargetId));
    }




    public static Hint valueOf(String hunterId, Long treasureId, List<Long> bringtargetId) {
        return new Hint(hunterId, treasureId , bringtargetId);
    }



    public void validation(List<Long> bringtargetId) {

        if(bringtargetId.isEmpty()) {
            new IllegalArgumentException("Treasure Exception !!!");
        }
    }






}


/*


*/
