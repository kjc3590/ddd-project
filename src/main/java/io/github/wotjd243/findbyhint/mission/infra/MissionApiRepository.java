package io.github.wotjd243.findbyhint.mission.infra;

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Optional;

@Repository
public class MissionApiRepository implements MissionRepository {
    private MissionTranslator missionTranslator;

    @Override
    public Optional<Mission> findById(Long missionId) throws IOException {
        return Optional.empty();
    }

}
