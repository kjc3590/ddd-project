package io.github.wotjd243.findbyhint.treasure.domain;

import org.assertj.core.api.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinatesTest {

    @Test
    public void 좌표생성() {

        //given
        Double latitude =34.12;
        Double longitude = 125.2;

        //when
        Coordinates.valueOf(latitude,longitude);

        //then

    }
    @Test(expected = IllegalArgumentException.class)
    public void 위도가_잘못된_경우() {

        //given
        Double latitude =32.12;
        Double longitude = 125.2;

        //when
        Coordinates.valueOf(latitude,longitude);

        //then

    }
    @Test(expected = IllegalArgumentException.class)
    public void 경도가_잘못된_경우() {

        //given
        Double latitude =34.12;
        Double longitude = 122.2;

        //when
        Coordinates.valueOf(latitude,longitude);

        //then

    }


}