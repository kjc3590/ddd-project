package io.github.wotjd243.findbyhint.util.VO;
/*
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.util.DateObject;
import lombok.Getter;
import lombok.extern.java.Log;
import org.thymeleaf.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Embeddable
@Log
public class Event {

    // 이벤트 상태
    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private EventStatus status;

    //이벤트 기간
    @Enumerated(value = EnumType.ORDINAL)
    private EventPeriod eventPeriod;

    // 이벤트 이름
    @Column(nullable = false)
    private String name;

    private Event(LocalDate startDate, LocalDate endDate,String name) {
        this.name = validation(name);
        this.status = EventStatus.WAIT;
        this.eventPeriod = EventPeriod.valueOf(startDate,endDate);
    }

    public static Event valueOf(final LocalDate startDate, final LocalDate endDate, final String name){
        return new Event(startDate,endDate,name);
    }

    //현재 시간과 비교하기
    public int checkEvent(){

        //현재 날짜가 이벤트기간안에 있으면 0
        if(safeEvent()){
            return 0;
        //현재 날짜가 이벤트기간보다 늦으면 1
        }else if(lateEvent()){
            return 1;
        //현재 날짜가 이벤트기간보다 빠르면 -1
        }else if(fastEvent()){
            return -1;

        //전부 아닐경우 -2
        }else{
            return -2;
        }
    }

    private Boolean safeEvent(){
        DateObject today = DateObject.getInstance();
        LocalDate todayDate =today.getDate();
        LocalDate eventEndDate = this.eventPeriod.getEndDate();

        return (todayDate.isEqual(eventEndDate) || todayDate.isAfter(eventEndDate));

    }

    private Boolean lateEvent(){
        DateObject today = DateObject.getInstance();
        LocalDate todayDate =today.getDate();
        LocalDate eventEndDate = this.eventPeriod.getEndDate();

        return (todayDate.isBefore(eventEndDate));

    }

    private Boolean fastEvent(){
        DateObject today = DateObject.getInstance();
        LocalDate todayDate =today.getDate();
        LocalDate eventStartDate = this.eventPeriod.getStartDate();

        return (todayDate.isAfter(eventStartDate));

    }

    //validation
    private String validation(final String name){
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("이벤트의 이름을 할당 받지 못했습니다.");
        }
        return name;
    }

    public Boolean isWait(){
        return this.status == EventStatus.WAIT;
    }

    public Boolean isActive(){
        return this.status == EventStatus.ACTIVE;
    }

    public Boolean isClose(){
        return this.status == EventStatus.CLOSE;
    }

    // eventStatus 변경하는 메소드 진행으로 완료로 대기로

    public Boolean waitEvent(){
        this.status = EventStatus.WAIT;
        return isWait();
    }
    public Boolean activeEvent(){
        this.status = EventStatus.ACTIVE;
        return isActive();
    }
    public Boolean closeEvent(){
        this.status = EventStatus.CLOSE;
        return isClose();
    }

    // 이벤트 기간 가져오는 메소드
    public Period getPeriod(){
        return this.eventPeriod.getPeriod();
    }


}
