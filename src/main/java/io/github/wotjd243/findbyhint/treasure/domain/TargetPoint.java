package io.github.wotjd243.findbyhint.treasure.domain;

import org.springframework.util.StringUtils;

import java.util.HashMap;

public class TargetPoint {

    private Long targetPointId;

    /*실제 보물의 위치*/
    //위도
    private final String latitude;

    //경도
    private final String hardness;

    private TargetPoint(String latitude, String hardness) {
        validation(latitude,hardness);
        this.latitude = latitude;
        this.hardness = hardness;

    }

    public static TargetPoint valueOfIatitudeAndHardness (final String latitude,final String hardness){
    return new TargetPoint(latitude,hardness);
}

    //nullCHeck
<<<<<<< HEAD
    public void validation(final String latitude,final String hardness){
        if(StringUtils.isEmpty(latitude) || StringUtils.isEmpty(hardness)){
            throw new IllegalArgumentException("TargetPoint 지점에서 발생");
=======
    public void validation(final Double latitude,final Double hardness,final String distinguish){

        if(latitude == null || hardness ==null || StringUtils.isEmpty(distinguish)){
            throw new IllegalArgumentException("TargetPoint Exception !!!");
        }else if (latitude < 33 || latitude > 43 ||  hardness < 124 || hardness > 132){
            throw new IllegalArgumentException("경도와 위도범위가 맞지 않음 " + "경도 (hardness) :" + hardness + "위도(latitude) : "+latitude);
>>>>>>> afac6cd... *docs README 문서 수정
        }
    }

    public HashMap getTargetPoint(){
        HashMap targetPointMap = new HashMap();
        targetPointMap.put("latitude",this.latitude);
        targetPointMap.put("hardness",this.hardness);
        return targetPointMap;
    }
}

