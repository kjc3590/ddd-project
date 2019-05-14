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
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Repository
public interface TreasureRepository extends JpaRepository<Treasure,Long> {

    //활성화 되어있는 보물 가져오기
    @Query("select s from Treasure  s where s.treasureInventory.runningTime.status = '1'")
    Treasure findByTreasureInventoryByActive();

    //대기중인 보물 가져오기
    @Query("select s from Treasure  s where s.treasureInventory.runningTime.status = '0'")
    List<Treasure> findByTreasureInventoryByWait();

    //해당 보물의 해당 아이디들이 아니고 가짜 좌표인  아이디를 해당 개수의 맟춰서 가져오는 메소드
    @Query(value =
            (
                    "select TP.TARGET_POINT_ID " +
                    " from TREASURE T Inner join TARGET_POINT TP ON T.TREASURE_ID = TP.TREASURE_ID" +
                    " where T.TREASURE_ID = :treasureId" +
                    " AND TP.TARGET_POINT_ID NOT IN :ids " +
                    " AND TP.DISTINGUISH = 'FAKE' " +
                    " ORDER BY TP.TARGET_POINT_ID asc " +
                    " LIMIT :hintCount "
            )
            ,nativeQuery = true
            )
            List<Long> findTargetPointIds(@Param("treasureId") Long treasureId, @Param("ids") List<Long> ids,@Param("hintCount") int hintCount);

    //해당 보물의 해당 아이디들이 아니고 가장 난이도가 낮은 미션 아이디 하나 가져오는 메소드
    @Query(value =
            (
                    "select M.MISSION_ID, M.MISSION_LEVEL " +
                            " from TREASURE T inner join  MISSION M on T.TREASURE_ID = M.MISSION_ID " +
                            " where T.TREASURE_ID = :treasureId " +
                            " and M.MISSION_ID not in :ids " +
                            " order by M.MISSION_LEVEL asc " +
                            " LIMIT 1"
            )
            ,nativeQuery = true)
    Object[] findMission(@Param("treasureId") Long treasureId, @Param("ids") List<Long> ids);


}
