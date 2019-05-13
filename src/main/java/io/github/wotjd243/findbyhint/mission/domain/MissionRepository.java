package io.github.wotjd243.findbyhint.mission.domain;

import java.io.IOException;
import java.util.Optional;

public interface MissionRepository {
    Optional<Mission> findById(final Long missionId) throws IOException;
}
