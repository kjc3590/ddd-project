package io.github.wotjd243.findbyhint.util.VO;
/**
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.util.DateObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.java.Log;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.security.acl.LastOwnerException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Getter(AccessLevel.PROTECTED)
@Embeddable
@Log
public class
EventPeriod {

    public EventPeriod() {}

    // 이벤트 시작일
    @Column(nullable = false)
    private  LocalDate startDate;

    // 이벤트 종료일
    @Column(nullable = false)
    private  LocalDate endDate;

    private EventPeriod(LocalDate startDate, LocalDate endDate) {
        validation(startDate,endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static EventPeriod valueOf(LocalDate startDate, LocalDate endDate){
        return new EventPeriod(startDate,endDate);
    }

    //이벤트 기간
    public Period getPeriod(){
        return Period.between(this.startDate,this.endDate);
    }

    //validation
    private void validation(final LocalDate startDate,final LocalDate endDate){

        if(startDate == null){
            throw new IllegalArgumentException("이벤트의 시작일자가 존재하지 않습니다.");
        }

        if(endDate == null){
            throw new IllegalArgumentException("이벤트의 종료일자가 존재하지 없습니다.");
        }

        //현재 날짜
        DateObject dateObject= DateObject.getInstance();
        LocalDate todayDate = dateObject.getDate();

        if(startDate.isBefore(todayDate)){
            eventPeriodValidationInfo(startDate, endDate);
            throw new IllegalArgumentException("이벤트의 시작날짜가 현재 시간보다 느리면 안됩니다.");
        }

        if(endDate.isBefore(todayDate)){
            eventPeriodValidationInfo(startDate, endDate);
            throw new IllegalArgumentException("이벤트의 종료날짜가 현재 날짜보다 빠르면 안됩니다.");
        }

        if(endDate.isBefore(startDate)){
            eventPeriodValidationInfo(startDate, endDate);
            throw new IllegalArgumentException("이벤트의 종료날짜가 시작날짜 보다 빠르면 안됩니다.");
        }

    }

    private void eventPeriodValidationInfo(LocalDate startDate, LocalDate endDate){

        DateObject today = DateObject.getInstance();

        log.info("--------------------------------------------------------------");
        log.info("In The Event validationInfo");
        log.info("시작일자 ::"+ startDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        log.info("종료일자 ::"+startDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        log.info("시작일자 가 현재 날짜보다 이른다 " +startDate.isAfter(today.getDate()));
        log.info("종료일자 가 현재 날짜보다 이른다 " +endDate.isAfter(today.getDate()));
        log.info("종료일자 가 시작일자 보다 날짜보다 이른다 " +endDate.isAfter(startDate));
        log.info("--------------------------------------------------------------");

    }

}
