package io.github.wotjd243.findbyhint.hunter.infra;

import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HunterRepository extends JpaRepository<Hunter,String> {
}
