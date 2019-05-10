
package io.github.wotjd243.findbyhint.hint.infra;

import io.github.wotjd243.findbyhint.hint.domain.Hint;
import io.github.wotjd243.findbyhint.hint.domain.HintRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public class DummyHintRepository implements HintRepository {

    @Override
    public Optional<Hint> findById(final Long id) {
        return Optional.ofNullable(DummyHintData.get(id));
    }
}

