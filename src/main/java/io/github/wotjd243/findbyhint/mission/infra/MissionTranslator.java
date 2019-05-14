package io.github.wotjd243.findbyhint.mission.infra;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionQnA;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Optional;

@Component
public class MissionTranslator {
    public Optional<Mission> translate(final MissionDto missionDto) {
            final Mission mission = new Mission(
                    missionDto.getDifficulty()
            );

        return Optional.ofNullable(mission);

    }
    public Mission translate(final Mission mission) {
        return null;
    }
}
