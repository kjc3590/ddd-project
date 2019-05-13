package io.github.wotjd243.findbyhint.util;

/*
 *
 * @author DoYoung
 *  SingleTon Object - DateObject
 */

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class DateObject {

    private LocalDate date;
    private LocalTime time;
    private LocalDateTime localDateTime;

    private  DateObject() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        date = localDateTime.toLocalDate();
        time = localDateTime.toLocalTime();
        this.localDateTime = localDateTime;
    }

    public static synchronized DateObject getInstance(){
        return new DateObject();
    }


}
