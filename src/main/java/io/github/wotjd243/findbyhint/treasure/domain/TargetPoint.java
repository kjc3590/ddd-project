package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Random;
import java.util.function.DoubleUnaryOperator;

@Entity
@Table(name = "targetPoint")
@Getter
public class TargetPoint {



    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long targetPointId;

    /*실제 보물의 위치*/
    //위도
    @Column(nullable = false)
    private final Double latitude;

    //경도
    @Column(nullable = false)
    private final Double hardness;

    //진짜 가짜 유무
    //파라미터로 distinguish  값을 받으면 안될것같아서 내부적으로 만들어지게 설정
    @Column(nullable = false)
    private final Distinguish distinguish;

    private TargetPoint(Double latitude, Double hardness,Distinguish distinguish) {
        validation(latitude,hardness,distinguish);
        this.latitude = latitude;
        this.hardness = hardness;
        this.distinguish =distinguish;
    }

    public static TargetPoint valueOfIatitudeAndHardness (final Double latitude,final Double hardness){
        return new TargetPoint(latitude,hardness,Distinguish.REAL);
    }

    //nullCHeck
    public void validation(final Double latitude,final Double hardness,final Distinguish distinguish){

        if(latitude == null || hardness ==null || distinguish == null){
            throw new IllegalArgumentException("TargetPoint Exception !!!");
        }else if (latitude < 33 || latitude > 43 ||  hardness < 124 || hardness > 132){
            throw new IllegalArgumentException("경도와 위도범위가 맞지 않음 " + "경도 (hardness) :" + hardness + "위도(latitude) : "+latitude);
        }

    }

    public TargetPoint getFakeTargetPoint(){
        Random random = new Random();
        Double latitude = 33 + random.nextInt(10) +random.nextDouble();
        Double hardness = 124 + random.nextInt(8) +random.nextDouble();
        return new TargetPoint(latitude,hardness,Distinguish.FAKE);
    }


