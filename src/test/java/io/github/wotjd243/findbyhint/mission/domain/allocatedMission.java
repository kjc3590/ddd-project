package io.github.wotjd243.findbyhint.mission.domain;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class allocatedMission {

    @Test
    public void 미션_나오는_순서() {

        int point = 0;
        int count = 12;

        for (int i = 3; i > 0; i--) {
            for (int i2 = 1; i2 <= i; i2++) {

                switch (i2) {
                    case 1:
                        System.out.println("브론즈 미션 생성");
                        break;
                    case 2:
                        System.out.println("실버 미션 생성");
                        break;
                    case 3:
                        System.out.println("골드 미션 생성");
                        break;

                }
                if (i == 1) {
                    i = 3;
                    i2 = 0;
                }
                count--;

                if (count == 0) {
                    break;
                }
            }
            if (count == 0) {
                break;
            }
        }
    }
}

