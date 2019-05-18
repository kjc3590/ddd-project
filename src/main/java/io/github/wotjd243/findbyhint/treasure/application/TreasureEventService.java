package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.hunter.application.HunterApiService;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureChangedEvent;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureEventMessage;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class TreasureEventService {
    private HunterApiService hunterApiService;
    private ApplicationEventPublisher publisher;

    public TreasureEventService(HunterApiService hunterApiService, ApplicationEventPublisher publisher) {
        this.hunterApiService = hunterApiService;
        this.publisher = publisher;
    }

    void sendFindMesseage(){
        publisher.publishEvent(TreasureChangedEvent.valueOf(hunterApiService.getSendListAll(),TreasureEventMessage.FIND));
    }

    void sendEndMessage(){
        publisher.publishEvent(TreasureChangedEvent.valueOf(hunterApiService.getSendListAll(),TreasureEventMessage.END));
    }

    void sendStartMessage(){
        publisher.publishEvent(TreasureChangedEvent.valueOf(hunterApiService.getSendListAll(),TreasureEventMessage.START));
    }
}
