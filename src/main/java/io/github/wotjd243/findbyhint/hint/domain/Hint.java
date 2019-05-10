package io.github.wotjd243.findbyhint.hint.domain;
//TODO 1. 힌트 에그리거트는 헌터ID, 보물ID, 좌표값, 순서로 이루어져 있다.
//TODO 2. Treasure에 해당하는 Tartgetpoint를 가져오는 메서드  ( HintService에서 Delete 메서드 실행시 보물의 좌표를 제외하고 지워야함 )
//TODO 3. 미션을 맞출 때 마다 Mission Level에 해당하는 수 만큼의 Tagetpoint List를 힌트로 가져온다.  distinguish 가 1이어야함

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;

public class Hint {
    // 힌트 엔티티의 PK  보물 "" 에 해당하는 힌트값을 찾을 때 유용
    private Long HintId;
    // FK
    private final HunterId hunterId;
    // FK 나중에 주입받는다 .(referenced Column ID)

    private final HintEffect hintEffect;

    public Hint(String hunterId, HintEffect hintEffect){
        this.hunterId = new HunterId(hunterId);
        this.hintEffect = hintEffect;
    }

    public static Hint valueOf(String hunterId, HintEffect hintEffect) {
        return new Hint(hunterId, hintEffect);
    }




}