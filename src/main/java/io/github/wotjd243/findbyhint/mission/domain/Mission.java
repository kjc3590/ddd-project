package io.github.wotjd243.findbyhint.mission.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mission")
@Getter
public class Mission {

    //미션 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missionId;
    // todo (1) '미션'을 맞췄는지 체크하는 메소드
    // todo (2) '헌터'가 획득할 포인트 생성
    // todo (3) '미션 인벤토리'에서 제거한 가장 난이도 낮은 미션 가져오기
    // todo (3-1) ->만약 없다면 다푼 것으로 간주 -> 상태를 알려줘야함  Expcetion https://github.com/wotjd243/ddd-project/pull/19/files 여기 참고하면 될것같은데 너무 어렵다..

    public Mission() {
    }

    //미션 난이도
    private MissionLevel missionLevel;

    public Mission(MissionLevel missionLevel) {
        validation(missionLevel);
        this.missionLevel = missionLevel;
    }

    public void validation(final MissionLevel level) {
        if (level == null) {
            throw new IllegalArgumentException("미션의 레벨이 없습니다.");
        }
    }

    public MissionLevel getMissionLevel() {
        return this.missionLevel;
    }

    // hintCounter 가져오기
    public int getHintCounter(){
        return this.missionLevel.getHintCounter();
    }
}