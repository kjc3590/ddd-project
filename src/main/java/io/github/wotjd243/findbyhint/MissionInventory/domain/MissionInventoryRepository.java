package io.github.wotjd243.findbyhint.MissionInventory.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionInventoryRepository extends JpaRepository<MissionInventory, Long> {

    Optional<MissionInventory> findByHunterId(String hunterId);

}
