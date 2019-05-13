package io.github.wotjd243.findbyhint.hint.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class HintInventory {
    public HintInventory() {}

    private Long treasureId;
    @Embedded
    private BringTarget bringtarget;

    private HintInventory(final Long treasureId, BringTarget bringtarget) {
        this.treasureId = treasureId;
        this.bringtarget = bringtarget;
    }
    public static HintInventory valueOf(Long treasureId, BringTarget bringtarget) {
        return new HintInventory(treasureId,  bringtarget);
    }


}
