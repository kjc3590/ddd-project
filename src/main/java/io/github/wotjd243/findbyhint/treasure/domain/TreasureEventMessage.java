package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.util.VO.EventStatus;
import lombok.Getter;

@Getter
public enum TreasureEventMessage {

    START("보물이 생성되었습니다. 찾아보세욥 !!! "),
    END("보물기간이 종료되었습니다... "),
    FIND("누군가 보물을 찾아 보물기간이 종료되었습니다...");

    private String eventMessage;

    TreasureEventMessage( String eventMessage) {
        this.eventMessage = eventMessage;
    }

    TreasureEventMessage() {
    }
}
