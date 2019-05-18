package io.github.wotjd243.findbyhint.mission.domain;

import io.github.wotjd243.findbyhint.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuccessMissionService {

    private static final int LEVEL = 1; //나중에 DB에서 레벨 불러와야함
    private static final int RANDOM_INT = RandomUtils.nextInt(9);

    public int isSuccess() {
        return takePoint();
    }

    private int takePoint() {
        return LEVEL * RANDOM_INT;
    }


}
