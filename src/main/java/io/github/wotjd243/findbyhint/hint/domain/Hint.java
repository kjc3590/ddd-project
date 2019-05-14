package io.github.wotjd243.findbyhint.hint.domain;
//TODO 1. 힌트 에그리거트는 헌터ID, 보물ID, 좌표값, 순서로 이루어져 있다.
//TODO 2. 미션이 완료된 후 미션의 난이도에 따른 보물의  targetPointId 값을 bringTargetPoint List에 ADD
//TODO 3. 보물의 targetPointId 값과 bringTargetPoint의 값 중복되는 부분을 제외하고 Treasure-TargetPoint 좌표값을
// Treasure서비스에 리턴 Treasure서비스에서 VO를 만들어서 헌터 맵보여줌

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import lombok.Getter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Hint")
@Getter
@ToString
public class Hint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hintId;

    @Embedded
    private HunterId hunterId;

    // hint id , hunter Id , Trasure Id ,
    @Embedded
    private HintInventory hintInventory;

    // 매개변수는 모두가 아는 타입으로 설정 -> 매개변수를 토대로 VO 가져오기
    private Hint(String hunterId, Long treasureId, List<Long> bringtargetIds){
        validation(bringtargetIds);
        this.hunterId = new HunterId(hunterId);
        this.hintInventory = HintInventory.valueOf(treasureId, BringTarget.valueOf(bringtargetIds));
    }

    public static Hint valueOf(String hunterId, Long treasureId, List<Long> bringtargetIds) {
        return new Hint(hunterId, treasureId , bringtargetIds);
    }

    public void validation(List<Long> bringtargetIds) {
        if(bringtargetIds.isEmpty()) {
            new IllegalArgumentException("Treasure Exception !!!");
        }
    }




    // TODO.1 힌트에그리거트의 핵심기능 (넣을게 있는지?)
    // TODO.2






}


/*


*/
