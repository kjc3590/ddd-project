package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TreasureRepository extends JpaRepository<Treasure,Long> {

//    @Query("select t from  Treasure t where t.treasureInventory.runningTime.EventStatus = 'ACTIVE'")
//    List<Treasure> findActiveTreasure();

    //활성화 되어있는 보물 가져오기
    @Query
    Treasure findByTreasureInventory_RunningTime_Status_Active();

    //대기중인 보물 가져오기
    @Query
    List<Treasure> findByTreasureInventory_RunningTime_Status_Wait();

    //해당 보물의 해당 아이디들이 아니고 가짜 좌표인 좌표 아이디를 가져오는 메소드
    @Query("select  t from Treasure t where t.treasureId =?1 and " +
            " t.treasureInventory.targetPointList.distinguish = 'FAKE' and " +
            " t.treasureInventory.targetPointList.targetPointId not in :ids ")
    List<Long> getTargetPointIds(Long treasureId, @Param("ids") List<Long> ids);

    //해당 보물의 해당 아이디들이 아니고 가장 난이도가 낮은 미션 아이디 하나 가져오는 메소드
    @Query("select t from Treasure t where t.treasureId = ?1 and t.treasureInventory.missionList.missionId not in :ids")
    Long findMissionId(Long treasureId, @Param("ids") List<Long> ids);

}
