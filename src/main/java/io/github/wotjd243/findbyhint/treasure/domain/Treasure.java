package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import io.github.wotjd243.findbyhint.util.domain.DateTimeEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.servlet.View;

import javax.persistence.*;

@Entity
@Table(name = "treasure")
@Getter
@ToString
public class Treasure extends DateTimeEntity {

    public static final String SEQUENCE_NAME = "TREASURE_SEQ";

    //기본 생성자
    private Treasure() {}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long treasureId;

    @Embedded
    private QRCode qrCodeVO;

    @Embedded
    private TreasureInventory  treasureInventory = new TreasureInventory();

    public Treasure(String qrPw) {
        this.qrCodeVO = QRCode.valueOf(qrPw);
    }

    //RunningTime 으로 날짜 유효성 체크하기
    public int validEvent(){
        return this.treasureInventory.getRunningTime().checkEvent();
    }
    public Boolean isActive(){
        return this.treasureInventory.getRunningTime().isActive();
    }

    public Boolean isWait(){
        return this.treasureInventory.getRunningTime().isWait();
    }
    public Boolean isClose(){
        return this.treasureInventory.getRunningTime().isClose();
    }


    public Boolean activeEvent(){ return this.treasureInventory.getRunningTime().activeEvent(); }
    public Boolean closeEvent(){return this.treasureInventory.getRunningTime().closeEvent();}

    public static Treasure valueOf(String qrPw) {
        return new Treasure(qrPw);
    }

    public void setTreasureInventory(TreasureInventory treasureInventory) {
        this.treasureInventory = treasureInventory;
    }

    public View qrCodeView(){
        return this.qrCodeVO.getQrView();
    }

}
