package io.github.wotjd243.findbyhint.mission.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

//    void save(MissionInfo missionInfo);
//    Optional<Mission> findById(final Long missionId) throws IOException;
}
