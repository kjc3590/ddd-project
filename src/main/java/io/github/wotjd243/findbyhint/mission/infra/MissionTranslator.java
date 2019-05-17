package io.github.wotjd243.findbyhint.mission.infra;

import io.github.wotjd243.findbyhint.MissionInventory.application.MissionDto;
import io.github.wotjd243.findbyhint.mission.domain.Mission;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MissionTranslator {
    public Optional<Mission> translate(final MissionDto missionDto) {
            final Mission mission = new Mission(
                    missionDto.getLevel()
            );

        return Optional.ofNullable(mission);

    }
    public Mission translate(final Mission mission) {
        return null;
    }
}
