package io.github.wotjd243.findbyhint.mission.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionInfoRepository extends JpaRepository<MissionInfo, Long> {

}
