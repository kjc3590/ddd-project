package io.github.wotjd243.findbyhint.util.VO;
/**
 *
 * @author DoYoung
 *
 */

import lombok.Getter;

@Getter
public enum EventStatus {

    WAIT("0","대기"), ACTIVE("1","진행"), CLOSE("-1","종료");

    EventStatus() {}

    private String statusValue;
    private String statusName;

    EventStatus(String statusValue, String statusName) {
        this.statusValue = statusValue;
        this.statusName = statusName;
    }
}
