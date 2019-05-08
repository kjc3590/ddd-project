package io.github.wotjd243.findbyhint.hint.infra;

import io.github.wotjd243.findbyhint.hint.domain.Hint;
import io.github.wotjd243.findbyhint.treasure.domain.TargetPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyHintData {

    private static final Map<Long, Hint> data = new HashMap<>();

    static {


    }

    public static Hint get(final Long key) {
        return data.get(key);
    }

    private static Hint put(Long Hintid,String HunterId,
                                Long treasureId,
                                List<TargetPoint> targetPointList) {

        return data.put(Hintid,new Hint(HunterId,treasureId,targetPointList));

    }
}
