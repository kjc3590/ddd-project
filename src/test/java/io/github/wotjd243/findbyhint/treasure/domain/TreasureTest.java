package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.util.DateObject;
import org.junit.Test;

import java.time.LocalDate;

public class TreasureTest {

    @Test(expected = IllegalArgumentException.class)
    public void 보물생성() throws IllegalAccessException {

        // given
        DateObject dateObject = DateObject.getInstance();
        final String qrPw = "1234";
        final Double latitude = 126.9941658;
        final Double longitude = 37.4954676;
        final LocalDate startDate = dateObject.getDate().plusDays(10L);
        final LocalDate endDate = dateObject.getDate().plusDays(11L);
        final String name = "보물의 이름";

        Treasure.valueOf(qrPw,latitude,longitude,startDate,endDate,name);


        // when
        //Treasure result =  Treasure.valueOf(1L,"김도영","qrCode","상태",latitude,hardness,startDate,endDate);

        // then
        }   
    }

