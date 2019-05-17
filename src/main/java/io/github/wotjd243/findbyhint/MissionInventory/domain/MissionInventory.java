package io.github.wotjd243.findbyhint.MissionInventory.domain;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.util.domain.DateTimeEntity;
import lombok.Getter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Getter
public class MissionInventory extends DateTimeEntity {

    // TODO (1) '일급 콜렉션' 필요한 부분에서 만들어보기
    // TODO (2) 인스턴스 변수 3개 이하로 줄이기

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long missionInfoId;

    private HunterId hunterId; //헌터 아이디

    private Long treasureId; //보물 아이디

    @Embedded
    private MissionBook missionBook;

    private MissionInventory(HunterId hunterId, Long treasureId) {
        validation(hunterId, treasureId);
        this.hunterId = hunterId;
        this.treasureId = treasureId;
    }

    public MissionInventory() {
    }

    public static MissionInventory valueOf(HunterId hunterId, Long treasureId){
        return new MissionInventory(hunterId, treasureId);
    }

    private void validation(HunterId hunterId, Long treasureId) {
        if(hunterId == null) {
            throw new IllegalArgumentException("헌터ID가 존재하지 않습니다.");
        }

        if(treasureId == null) {
            throw new IllegalArgumentException("보물ID가 존재하지 않습니다.");
        }

    }

    public void setMissionBook(MissionBook missionBook) {
        this.missionBook = missionBook;
    }
//    public void addMissionBook(List<MissionInventoryInfo> infoList){
//         if (this.missionBook == null) {
//             this.missionBook = new MissionBook(infoList);
//         }
//         if (this.missionBook != null) {
//             this.missionBook.addMissionBook(infoList);
//         }
//
//    }


}
