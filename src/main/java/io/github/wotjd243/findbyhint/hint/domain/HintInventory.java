package io.github.wotjd243.findbyhint.hint.domain;

public class HintInventory {
    private final Long treasureId;
    private BringTarget bringtargetId;

    public HintInventory(final Long treasureId, BringTarget bringtargetId) {
        this.treasureId = treasureId;
        this.bringtargetId = bringtargetId;
    }
}
