package io.github.wotjd243.findbyhint.hint.domain;
// COMPLETED 힌트 에그리거트는 헌터 ID, 보물 ID, 좌표값, 순서로 이루어져 있다.
// COMPLETED 미션이 완료된 후 미션의 난이도에 따른 보물의  targetPointId 값을 bringTargetPoint List 에 ADD
// COMPLETED 보물의 targetPointId 값과 bringTargetPoint 의 값 중복되는 부분을 제외하고 Treasure-TargetPoint 좌표값을
// Treasure 서비스에 리턴 Treasure 서비스에서 VO를 만들어서 헌터 맵보여줌

// COMPLETED 힌트에그리거트의 핵심기능 (넣을게 있는지?)

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import lombok.Getter;
import lombok.ToString;
import org.thymeleaf.util.StringUtils;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Hint")
@Getter
@ToString
public class Hint {

    public Hint() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hintId;

    @Embedded
    private BringTarget bringtarget;

    @Embedded
    private HintMappedIds hintMappedIds;
    // hintId, hunterId , TreasureId
    // 매개변수는 모두가 아는 타입으로 설정 -> 매개변수를 토대로 VO 가져오기
    private Hint(String hunterId, Long treasureId){
        validation(hunterId,treasureId);
        this.hintMappedIds = HintMappedIds.valueOf(hunterId,treasureId);
    }

    //가져온 타겟 포인트 꺼내는 메소드
    public List<Long> getBringTargetPointIds(){
        if(this.bringtarget != null){
            return this.bringtarget.getBringtargetIds();
        }else{
            return null;
        }
    }

    //가져온 타겟 포인트를 추가하는 메소드
    public void addBringTargetPointIds(List<Long> ids){
        if(bringtarget == null){
            this.bringtarget = BringTarget.valueOf(ids);
        }else{
            this.bringtarget.addBringTargetIds(ids);
        }
    }

    public static Hint valueOf(String hunterId, Long treasureId) {
        return new Hint(hunterId, treasureId);
    }

    public void validation(String hunterId, Long treasureId) {
        if (StringUtils.isEmpty(hunterId) || treasureId == null) {
            new IllegalArgumentException("Treasure Exception !!!");
        }
    }


}
