
package io.github.wotjd243.findbyhint.hint.infra;

import io.github.wotjd243.findbyhint.hint.domain.Hint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyHintData {

    private static final Map<Long, Hint> data = new HashMap<>();

    static {
            final Long hintId = 1L;
            final String hunterId = "slsl";
            final Long treasureId = 2L;
            List<Long> bringtargetId = new ArrayList<>();
            bringtargetId.add(1L);    // 미션을 풀어서 전달된 좌표를 넘겨줌
            bringtargetId.add(2L);
            bringtargetId.add(3L);

        put(hintId,hunterId,treasureId,bringtargetId);
    }

    public static Hint get(final Long key) {
        return data.get(key);
    }

    private static Hint put(Long hintId,String hunterId,
                                Long treasureId,
                                List<Long> bringtargetId) {

   /*     List<Long> ids  = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);*/

        return data.put(hintId,new Hint(hunterId,treasureId,bringtargetId));


    }
}

