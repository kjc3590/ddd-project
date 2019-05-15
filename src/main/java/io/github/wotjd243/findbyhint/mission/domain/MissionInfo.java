package io.github.wotjd243.findbyhint.mission.domain;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.util.domain.DateTimeEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
@Getter
public class MissionInfo extends DateTimeEntity {

    // TODO (1) '일급 콜렉션' 필요한 부분에서 만들어보기
    // TODO (2) 인스턴스 변수 3개 이하로 줄이기

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missionInfoId;

    private HunterId hunterId; //헌터 아이디

    private Long treasureId; //보물 아이디

    @Embedded
    private Party party;

    @Embedded
    private MissionQnA missionQnA;

    public MissionInfo(String hunterId, Long treasureId) {
        this.hunterId = HunterId.valueOf(hunterId);
        this.treasureId = treasureId;
    }

    public static MissionInfo valueOf(String hunterId, Long treasureId) {
        return new MissionInfo(hunterId, treasureId);
    }


    public void add(List<MissionInventory> party){

        if(this.party == null){
            this.party = new Party(party);
        }

        this.party.addPary(party);
    }

}
