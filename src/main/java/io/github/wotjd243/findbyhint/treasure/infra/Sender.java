package io.github.wotjd243.findbyhint.treasure.infra;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.treasure.application.MessageSender;
import io.github.wotjd243.findbyhint.treasure.domain.SendList;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureChangedEvent;
import lombok.extern.java.Log;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
@Log
public class Sender implements MessageSender {

    @Override
    @Async
    @TransactionalEventListener
    @Order(value = 1)
    public void sendCongratulation(TreasureChangedEvent treasureChangedEvent) {
        SendList sendList= treasureChangedEvent.getSendList();
        List<HunterId> hunterIds = sendList.getHunterIds();
        hunterIds.forEach(i -> {
            String  hunterId = i.getHunterId();
            log.info("'"+hunterId+"' 님 " +treasureChangedEvent.getTreasureEventMessage().getEventMessage());
        });


    }
}
