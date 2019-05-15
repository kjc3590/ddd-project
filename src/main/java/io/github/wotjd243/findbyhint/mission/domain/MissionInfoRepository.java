package io.github.wotjd243.findbyhint.mission.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionInfoRepository extends JpaRepository<MissionInfo, Long> {

//    @Query("SELECT MP.MISSION_ID " +
//            "FROM MISSION_INFO_PARTY MP INNER JOIN MISSION M ON M.MISSION_ID = MP.MISSION_ID" )
//    MissionInfo findByMissionId();



}
