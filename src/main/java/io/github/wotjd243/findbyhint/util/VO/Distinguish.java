package io.github.wotjd243.findbyhint.util.VO;

import lombok.Getter;

/**
 *
 * @author DoYoung
 *
 */

@Getter
public enum Distinguish {

    FAKE("0"), REAL("1");

    private final String destines;

    Distinguish(String destines) { this.destines = destines; }
}
