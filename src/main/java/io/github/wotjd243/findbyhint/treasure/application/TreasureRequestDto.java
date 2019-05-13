package io.github.wotjd243.findbyhint.treasure.application;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TreasureRequestDto {

    private String qrPw;
    private Double latitude;
    private Double longitude;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;

}
