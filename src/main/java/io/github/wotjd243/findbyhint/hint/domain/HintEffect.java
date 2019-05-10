package io.github.wotjd243.findbyhint.hint.domain;

public class HintEffect {
    private final Long treasureId;
    private BringTarget bringtargetId;

    public HintEffect(final Long treasureId, BringTarget bringtargetId) {
        this.treasureId = treasureId;
        this.bringtargetId = bringtargetId;
    }
}
