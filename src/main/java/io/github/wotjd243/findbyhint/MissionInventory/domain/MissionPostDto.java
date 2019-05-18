package io.github.wotjd243.findbyhint.MissionInventory.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MissionPostDto {

    private Long missionId;
    private String hunterId;
    private String answer;
    private String hunterAnswer;

}
