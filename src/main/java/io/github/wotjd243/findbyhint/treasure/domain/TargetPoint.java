package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.util.VO.Distinguish;
import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "targetPoint")
@Getter(value = AccessLevel.PROTECTED)
public class TargetPoint {

    //longitude 경도
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long targetPointId;


    @Embedded
    private Coordinates coordinates;

    //진짜 가짜 유무
    //파라미터로 distinguish  값을 받으면 안될것같아서 내부적으로 만들어지게 설정
    @Column(nullable = false)
    @Enumerated(value =EnumType.ORDINAL)
    private final Distinguish distinguish;

    private TargetPoint(Double latitude, Double longitude,Distinguish distinguish) {
        this.coordinates = Coordinates.valueOf(latitude,longitude);
        this.distinguish = distinguish;
    }

    public static TargetPoint valueOf(Double latitude, Double longitude){
        return new TargetPoint(latitude,longitude,Distinguish.REAL);
    }

    TargetPoint getFakeTargetPoint() {
        Random random = new Random();
        Double latitude = 33 + random.nextInt(10) + random.nextDouble();
        Double longitude = 124 + random.nextInt(8) + random.nextDouble();
        return new TargetPoint(latitude, longitude, Distinguish.FAKE);
    }
}

