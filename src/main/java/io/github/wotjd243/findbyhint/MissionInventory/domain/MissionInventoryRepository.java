package io.github.wotjd243.findbyhint.MissionInventory.domain;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionInventoryRepository extends JpaRepository<MissionInventory , Long> {

    Optional<MissionInventory> findByHunterIdAndTreasureId(HunterId hunterId, Long treasureId);

    Optional<MissionInventory> findByTreasureId(Long treasureId);

}
