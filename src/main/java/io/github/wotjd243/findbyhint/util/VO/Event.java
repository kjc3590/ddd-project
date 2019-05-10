package io.github.wotjd243.findbyhint.util.VO;
/*
 *
 * @author DoYoung
 *
 */

import lombok.Getter;
import lombok.extern.java.Log;
import org.thymeleaf.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Embeddable
@Log
public class Event {

    // 이벤트 상태
    @Column(nullable = false)
    private EventStatus status;

    //이벤트 기간
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

    //TODO  eventStatus 변경하는 메소드 진행으로 완료로 대기로

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

}
