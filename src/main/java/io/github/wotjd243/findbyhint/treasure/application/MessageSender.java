package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.treasure.domain.TreasureChangedEvent;

public interface MessageSender {
    void sendCongratulation(TreasureChangedEvent treasureChangedEvent);
}
