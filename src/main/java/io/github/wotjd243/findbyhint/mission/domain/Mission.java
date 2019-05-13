package io.github.wotjd243.findbyhint.mission.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.thymeleaf.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "mission")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {

    // TODO (1)‘미션’을 러닝타임 시간안에 푸는데 성공하면 거기에 맞는 힌트를 제공한다. --보물, 힌트도메인과 연관
    // TODO (2)‘미션’을 푸는데 성공하면 다음 난이도에 도전할 수 있다.
    // TODO (3)‘미션’은 누군가 보물의 QR코드를 접속하면 종료된다.->보물도메인에서
    // TODO (4)'미션'을 맞췄는지 체크하는 메소드
    // TODO (4-1) -> 만약 없다면 다푼 것으로 간주 -> 상태를 알려줘야함  Expcetion https://github.com/wotjd243/ddd-project/pull/19/files 여기 참고하면 될것같은데 너무 어렵다..
    // TODO (5)'헌터'가 획득할 미션 포인트 생성
    // TODO (6)'미션 인벤토리' 에 있는 데이터를 제외한 가장 난이도 낮은 미션 가져오기

    //미션 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missionId;

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
        return missionLevel;
    }
}
