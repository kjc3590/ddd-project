package io.github.wotjd243.findbyhint.mission.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class MissionId { //일급콜렉션 - 여기서는 미션에 접근한 아이디 확인
    @ElementCollection
    private List<MissionAccess> missionId;

    public MissionId(List<MissionAccess> missionId) {
        this.missionId = new ArrayList<>(missionId);
    }
}
