package io.github.wotjd243.findbyhint.hint.domain;

import java.util.Optional;

// save , List , delete
public interface HintRepository {
    Optional<Hint> findById(final Long id);
}
