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

    private static DateObject dateObject;

    private  DateObject() {
        date = LocalDate.now();
        time = LocalTime.now();
        localDateTime = LocalDateTime.now();
    }

    public static synchronized DateObject getInstance(){
        if(dateObject == null){
            dateObject = new DateObject();
        }
        return dateObject;
    }


}
