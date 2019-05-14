package io.github.wotjd243.findbyhint.hunter.domain;

import io.github.wotjd243.findbyhint.util.check.Check;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class HunterId implements Serializable {

    private String hunterId;

    public HunterId() {
    }

    public static HunterId valueOf(final String hunterId) {
        return new HunterId(hunterId);
    }

    public HunterId(final String hunterId) {

        validation(hunterId);

        this.hunterId = hunterId;
    }

    private void validation(String hunterId) {

        Check.startEngCheck(hunterId);

        Check.lengthLimit(hunterId, 20);

        Check.idpwCheck(hunterId);
    }

    @Override
    public String toString() {
        return "HunterId{" +
                "hunterId='" + hunterId + '\'' +
                '}';
    }
}
