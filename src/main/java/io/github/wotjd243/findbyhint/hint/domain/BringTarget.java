package io.github.wotjd243.findbyhint.hint.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class BringTarget {
    @ElementCollection
    private List<Long> bringtargetId;

    private BringTarget(final List<Long> bringtargetId) {
        this.bringtargetId = new ArrayList<>(bringtargetId);
    }

    public static BringTarget valueOf(List<Long> bringtargetId) {
        return new BringTarget(bringtargetId);
    }

}
