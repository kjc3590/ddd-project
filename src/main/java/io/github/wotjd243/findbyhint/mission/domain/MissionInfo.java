package io.github.wotjd243.findbyhint.mission.domain;

import io.github.wotjd243.findbyhint.util.domain.DateTimeEntity;

import javax.persistence.*;


@Entity
public class MissionInfo extends DateTimeEntity {

    // TODO (1) '일급 콜렉션' 필요한 부분에서 만들어보기
    // TODO (2) 인스턴스 변수 3개 이하로 줄이기

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missionInfoId;

    private Long hunterId; //헌터 아이디

    private Long treasureId; //보물 아이디

    @Embedded
    private Party party;

    @Embedded
    private MissionQnA missionQnA;

    public MissionInfo(Long hunterId, Long treasureId) {
        this.hunterId = hunterId;
        this.treasureId = treasureId;
    }

    private MissionInfo() {

    }
}
