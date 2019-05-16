package io.github.wotjd243.findbyhint.MissionInventory.application;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import lombok.*;

@Getter
@AllArgsConstructor
public class MissionDto {

    private String hunterId;
    private String question;
    private String answer;
    private MissionLevel level;
    private String success;

}
