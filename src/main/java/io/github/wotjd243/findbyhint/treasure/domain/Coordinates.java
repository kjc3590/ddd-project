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
    final static int MIN_longitude_VALUE =124;
    final static int MAX_LONGITUDE_VALUE =132;


    /*실제 보물의 위치*/
    //위도
    @Column(nullable = false)
    private Double latitude;
    //경도
    @Column(nullable = false)
    private Double longitude;

    public Coordinates() {
    }

    private Coordinates(Double latitude, Double longitude) {
        validation(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Coordinates valueOf(final Double latitude, final Double longitude) {
        return new Coordinates(latitude, longitude);
    }
    //nullCheck
    private void validation(final Double latitude, final Double longitude) {

//        log.info("MIN_LATITUDE_VALUE :: " +MIN_LATITUDE_VALUE);
//        log.info("MAX_LATITUDE_VALUE :: " +MAX_LATITUDE_VALUE);
//        log.info("MIN_longitude_VALUE :: " +MIN_longitude_VALUE);
//        log.info("MAX_LONGITUDE_VALUE :: " +MAX_LONGITUDE_VALUE);

        if (latitude == null) {
            throw new IllegalArgumentException("위도의 값을 할당 받지 못했습니다.");
        }

        if(longitude == null ){
            throw new IllegalArgumentException("경도의 값을 할당 받지 못했습니다.");
        }

        if(!isSafeZoneByLatitude(latitude)){
            throw new IllegalArgumentException("위도(Latitude)범위는 33 – 43 안에 범주하지 않습니다. Latitude:: "+ latitude);
        }

        if(!isSafeZoneByLongitude(longitude)){
            throw new IllegalArgumentException("위도(longitude)범위는 124 – 132 안에 범주하지 않습니다. longitude:: "+ longitude);
        }

    }

    private Boolean isSafeZoneByLatitude(Double latitude){
        return (latitude >= MIN_LATITUDE_VALUE || latitude <= MAX_LATITUDE_VALUE);
    }

    private Boolean isSafeZoneByLongitude(Double longitude){
        return ( longitude >= MIN_longitude_VALUE || longitude <= MAX_LONGITUDE_VALUE);
    }

}
