package io.github.wotjd243.findbyhint.hint.domain;
//TODO 1. 유저가 미션을 완료한 후 미션의 난이도에 따른 보물의 타겟포인트 ID를 가져옴
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class BringTarget {
    @ElementCollection
    private List<Long> bringtargetId;

    private BringTarget(final List<Long> bringtargetId) {
        this.bringtargetId = new ArrayList<>(bringtargetId);
    }

    public static BringTarget valueOf(List<Long> bringtargetId) {
        return new BringTarget(bringtargetId);
    }

    public List<Long> getBringtargetId() { return bringtargetId; }


}
