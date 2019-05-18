package io.github.wotjd243.findbyhint.treasure.domain;

import lombok.Getter;

@Getter
public class TreasureChangedEvent {

    private final SendList sendList;

    private final TreasureEventMessage treasureEventMessage;

    private TreasureChangedEvent(SendList sendList, TreasureEventMessage treasureEventMessage) {

        validation(/*sendList,*/ treasureEventMessage);

        this.sendList = sendList;
        this.treasureEventMessage = treasureEventMessage;

    }

    public static TreasureChangedEvent valueOf(SendList sendList, TreasureEventMessage treasureEventMessage){
        return new TreasureChangedEvent(sendList, treasureEventMessage);
    }

        public void validation(/*SendList sendList,*/ TreasureEventMessage treasureEventMessage){
//        if(sendList == null){
//            new  IllegalArgumentException("보내야할 목록이 없습니다.");
//        }
//        보내야할 사람이 없을 경우도 있음

        if(treasureEventMessage == null){
            throw new IllegalArgumentException("보내야할 메시지가 없습니다.");
        }
    }
}
