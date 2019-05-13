package io.github.wotjd243.findbyhint.mission.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.thymeleaf.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "mission")
@Getter
public class Mission {

    // TODO (1) '미션’은 난이도가 4개가 있다. (브론즈, 실버, 골드, 플래티넘)
    // TODO (2) '미션' 은 Easy일 경우 브론즈, 실버 난이도로, Medium일 경우 골드 난이도로, Hard일 경우 플래티넘 난이도로 레벨이 적용된다.
    // TODO (3)‘미션’을 러닝타임 시간안에 푸는데 성공하면 거기에 맞는 힌트를 제공한다. --보물, 힌트도메인과 연관
    // TODO (4)‘미션’을 푸는데 성공하면 다음 난이도에 도전할 수 있다.
    // TODO (5)‘미션’은 누군가 보물의 QR코드를 접속하면 종료된다.->보물도메인에서
    // TODO (6) '미션 포인트'는 최대 100점을 넘을 수 없다.

    // todo (1) :: 미션을 맟췄는지 체크하는 메소드
    // todo (2) :: 헌터가 획득할 포인트 생성
    // todo (3) :: 미션인벤토리에서 제거한 가장 난이도 낮은 미션 가져오기
    // todo (3-1) :: 만약없다면 다푼 것으로 간주 -> 상태를 알려줘야함  Expcetion https://github.com/wotjd243/ddd-project/pull/19/files 여기 참고하면 될것같은데 너무 어렵다..


    public Mission() {
    }

    //미션키
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missionId;

    //미션 난이도
    //미션 난이도
    private MissionLevel missionLevel;



    public Mission(MissionLevel missionLevel/*, int missionKey, String question, String answer, final int point*/) {

        validation(missionLevel);
        this.missionLevel = missionLevel;
        /*this.point = MissionPoint.valueOf(point);
        this.missionKey = missionKey;
        this.question = question;
        this.answer = answer;*/
    }

    public void validation(final MissionLevel level) {
        if(level == null) {
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


    //private final int missionKey;

    //미션 난이도
//    private final MissionLevel level;
    //미션 문제
    //private final String question;
    //미션 답
    //private final String answer;

    //미션성공시 증정 포인트
    /*@Embedded
    private final MissionPoint point;*/

}

    /*public Mission(final int missionKey, final String question, final String answer, final String level, final int point) {
        validation(question, answer, level);
        this.missionKey = missionKey;
        this.question = question;
        this.answer = answer;
        this.missionLevel = MissionLevel.valueOf(missionLevel);
        this.level = new MissionLevel(level);
        this.point = MissionPoint.valueOf(point);
    }*/
