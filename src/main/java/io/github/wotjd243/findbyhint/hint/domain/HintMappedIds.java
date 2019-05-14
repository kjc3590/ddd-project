package io.github.wotjd243.findbyhint.hint.domain;

import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

@Embeddable
@Getter
public class HintMappedIds {
    public HintMappedIds() {}


    @Embedded
    private HunterId hunterId;

    private Long treasureId;

    private HintMappedIds(String hunterId, Long treasureId) {
        this.hunterId = new HunterId(hunterId);
        this.treasureId = treasureId;
    }

    public static HintMappedIds valueOf (String hunterId, Long treasureId){
        return new HintMappedIds(hunterId,treasureId);
    }
}
