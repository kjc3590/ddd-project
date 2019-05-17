package io.github.wotjd243.findbyhint.hunter.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class HunterDto {

    private String hunterId;

    private String hunterPw;

    private String hunterName;

    private String hunterPicturePath;

    private String hunterPictureName;

    private int hunterPoint;

    private int hunterBullet;

    private Timestamp hunterBulletRefillTime;

}
