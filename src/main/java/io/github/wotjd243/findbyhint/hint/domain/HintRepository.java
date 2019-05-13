package io.github.wotjd243.findbyhint.hint.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// save , List , delete
public interface HintRepository extends JpaRepository<Hint,Long> {
    Optional<Hint> findById(final Long id);

    Optional<HintInventory> findById2(final Long id);

    List<Long> findHintInventory(@Param("treasureId") Long treasureId, @Param("ids") List<Long> ids);
}
