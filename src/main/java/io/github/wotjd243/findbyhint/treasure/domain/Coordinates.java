package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.util.VO.Distinguish;
import lombok.Getter;
import lombok.extern.java.Log;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@Log
public class Coordinates {

    //위도 최대,최솟값
    final static int MIN_LATITUDE_VALUE =33;
    final static int MAX_LATITUDE_VALUE =43;

    //경도 최대,최솟값
    final static int MIN_HARDNESS_VALUE =124;
    final static int MAX_HARDNESS_VALUE =132;


    /*실제 보물의 위치*/
    //위도
    @Column(nullable = false)
    private final Double latitude;
    //경도
    @Column(nullable = false)
    private final Double hardness;


    private Coordinates(Double latitude, Double hardness) {
        validation(latitude, hardness);
        this.latitude = latitude;
        this.hardness = hardness;
    }

    public static Coordinates valueOf(final Double latitude, final Double hardness) {
        return new Coordinates(latitude, hardness);
    }
    //nullCHeck
    private void validation(final Double latitude, final Double hardness) {

        if (latitude == null) {
            throw new IllegalArgumentException("위도의 값을 할당 받지 못했습니다.");
        }

        if(hardness == null ){
            throw new IllegalArgumentException("경도의 값을 할당 받지 못했습니다.");
        }

        if(!isSafeZoneByLatitude(latitude)){
            throw new IllegalArgumentException("위도(Latitude)범위는 33 – 43 안에 범주하지 않습니다. Latitude:: "+ latitude);
        }

        if(!isSafeZoneByHardness(hardness)){
            throw new IllegalArgumentException("위도(Hardness)범위는 124 – 132 안에 범주하지 않습니다. Hardness:: "+ hardness);
        }

    }

    private Boolean isSafeZoneByLatitude(Double latitude){

        return (latitude < MIN_LATITUDE_VALUE || latitude > MAX_LATITUDE_VALUE);

    }

    private Boolean isSafeZoneByHardness(Double hardness){
        return ( hardness < MIN_HARDNESS_VALUE || hardness > MAX_HARDNESS_VALUE);
    }



}
