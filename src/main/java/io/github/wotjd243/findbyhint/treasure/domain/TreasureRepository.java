package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TreasureRepository extends JpaRepository<Treasure,Long> {

//    @Query("select t from  Treasure t where t.treasureInventory.runningTime.EventStatus = 'ACTIVE'")
//    List<Treasure> findActiveTreasure();

    //활성화 되어있는 보물 가져오기
//    @Query("select s from Treasure  s where s.treasureInventory.runningTime.status = 'ACTIVE'")
//    Treasure findByTreasureInventory();
//
//    //대기중인 보물 가져오기
//    @Query
//    List<Treasure> findByTreasureInventory_RunningTime_Status_Wait();
//
//    //해당 보물의 해당 아이디들이 아니고 가짜 좌표인 좌표 아이디를 가져오는 메소드
//    @Query("select  t.treasureInventory.targetPointList.targetPointId from Treasure t where t.treasureId = :treasureId and " +
//            " t.treasureInventory.targetPointList.distinguish = 'FAKE' and " +
//            " t.treasureInventory.targetPointList.targetPointId not in :ids ")
//    List<Long> findTargetPointIds(@Param("treasureId") Long treasureId, @Param("ids") List<Long> ids);
//
//    //해당 보물의 해당 아이디들이 아니고 가장 난이도가 낮은 미션 아이디 하나 가져오는 메소드
//    @Query("select t from Treasure t join fetch t.treasureInventory ti  join fetch t.treasureInventory.targetPointList tp " +
//            " where t.treasureId = :treasureId and tp.targetPointId not in :ids ")
//    Mission findMission(@Param("treasureId") Long treasureId, @Param("ids") List<Long> ids);

}
