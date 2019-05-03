package io.github.wotjd243.findbyhint.treasure.domain;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class Treasure {

    // TODO (2) 보물의 위치 : 위도 경도 VO로 만들기
    // TODO (3) 러닝타임 : 시작일 종료일 VO로 만들기

    private Long id;

    //보물의 이름
    private String name;

    //현재상태
    private String runningStatus;

    private TargetPoint targetPoint;

    private RunningTime runningTime;

    //보물로 접근할 수 있는 QR코드
    private QRCodeVO qrCodeVO;


    private Treasure(Long id, String name,
                     String runningStatus,QRCodeVO qrCodeVO,
                     final String latitude,final String hardness,
                     final LocalDate startDate,final LocalDate endDate) {

        validation(id,name,runningStatus);
        this.id = id;
        this.name = name;
        this.runningStatus = runningStatus;
        this.qrCodeVO = qrCodeVO;
        this.targetPoint= TargetPoint.valueOfIatitudeAndHardness (latitude,hardness);
        this.runningTime = RunningTime.valueOfStartDateAndEndDate(startDate,endDate);
    }

    public static Treasure valueOf(Long id, String name, QRCodeVO qrCodeVO,
                                   String runningStatus,
                                   String latitude,final String hardness,
                                   final LocalDate startDate,final LocalDate endDate){
        return new Treasure(id,name,runningStatus,qrCodeVO,latitude,hardness,startDate,endDate);
    }

    public void validation(Long id, String name, String runningStatus){
        if(id == null || StringUtils.isEmpty(name)|| StringUtils.isEmpty(runningStatus)){
            new IllegalArgumentException("Treasure 에서 예외 발생");
        }
    }
    public String TreasureInfo() {
        return "Treasure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qrCodeVO='" + qrCodeVO + '\'' +
                ", runningStatus='" + runningStatus + '\'' +
                ", targetPoint=" + targetPoint.getTargetPoint().get("latitude") + targetPoint.getTargetPoint().get("hardness") + '\'' +
                ", runningTime=" + runningTime.getRunningTime().get("startDate") + runningTime.getRunningTime().get("endDate") + '\'' +
                '}';
    }
}