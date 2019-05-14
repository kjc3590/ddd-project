package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.util.VO.Distinguish;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;

@Entity
@Table(name = "targetPoint")
@Getter
@Log
public class TargetPoint {

    //기본생성자
    public TargetPoint() {}

    @ManyToOne
    @JoinColumn(name ="treasureId")
    private Treasure treasure;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long targetPointId;

    @Embedded
    //좌표 VO
    private Coordinates coordinates;

    //진짜 가짜 유무
    @Column(nullable = false)
    @Enumerated(value =EnumType.STRING)
    private Distinguish distinguish;



    public TargetPoint(Double latitude, Double longitude, Distinguish distinguish) {
        this.coordinates = Coordinates.valueOf(latitude,longitude);
        this.distinguish = distinguish;
    }

    public static TargetPoint valueOf(Double latitude, Double longitude){
        return new TargetPoint(latitude,longitude,Distinguish.REAL);
    }


    public void setTreasure(Treasure treasure) {

        //log.info("treasure :: " +treasure);

        if(this.treasure != null) {
            if (this.treasure.getTreasureInventory() != null) {
                if (this.treasure.getTreasureInventory().getTargetPointList() != null) {
                    this.treasure.getTreasureInventory().getTargetPointList().remove(this);
                }
            }
        }
        this.treasure = treasure;
        this.treasure.getTreasureInventory().getTargetPointList().add(this);

    }

}

