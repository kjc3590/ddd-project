package io.github.wotjd243.findbyhint.mission.domain;

import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Embeddable
public class MissionInventory {

    // TODO (1) '일급 콜렉션' 필요한 부분에서 만들어보기
    // TODO (2) 인스턴스 변수 3개 이하로 줄이기

    @Id
    private int inventoryId;

    @ManyToOne
    @JoinColumn(name = "hunterId")
    private Hunter hunter;

    @ManyToOne
    @JoinColumn(name = "treasureId")
    private Treasure treasure;

    @Embedded
    private MissionId missionId;

    private String success;

    @CreationTimestamp
    private Timestamp createTime;

    private String question;
    private String answer;

    private MissionInventory() {

    }

    public MissionInventory(int inventoryId, Hunter hunter, Treasure treasure, MissionId missionId, String success, Timestamp createTime, String question, String answer) {
        this.inventoryId = inventoryId;
        this.hunter = hunter;
        this.treasure = treasure;
        this.missionId = missionId;
        this.success = success;
        this.createTime = createTime;
        this.question = question;
        this.answer = answer;
    }
}
