package io.github.wotjd243.findbyhint.hint.domain;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// save , List , delete
public interface HintRepository extends JpaRepository<Hint,Long> {

    @Query
    Hint findByHintMappedIds_HunterIdAndHintMappedIds_TreasureId(HunterId hunterId, Long treasureId);

//    List<Long> findHintInventory(@Param("treasureId") Long treasureId, @Param("ids") List<Long> ids);

}
