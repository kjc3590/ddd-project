package io.github.wotjd243.findbyhint.mission.infra;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Optional;

@Component
public class MissionTranslator {
    public Optional<Mission> translate(final Mission mission) throws IOException {
            MissionApi missionApi = new MissionApi();

        return Optional.ofNullable(mission);

    }
    public Mission translate(final MissionApi missionApi) {
        return null;
    }
}
