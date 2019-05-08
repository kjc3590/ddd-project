package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TreasureRepository extends JpaRepository<Treasure,Long> {


    //완료 상태로 바꾸기
    @Query("update Treasure t set  t.runningStatus = '완료' where t.treasureId = ?1")
    @Transactional
    @Modifying
    public void treasureCompleateById(Long treasureId);

    //진행 상태로 바꾸기
    @Query("update Treasure t set  t.runningStatus = '진행' where t.treasureId = ?1")
    @Transactional
    @Modifying
    public void treasureStartById(Long treasureId);



}
