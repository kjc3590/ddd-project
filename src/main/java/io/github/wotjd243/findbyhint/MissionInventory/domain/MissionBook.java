package io.github.wotjd243.findbyhint.MissionInventory.domain;

import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class MissionBook {

    @Embedded
    @ElementCollection
    private List<MissionInventoryInfo> infoList = new ArrayList<>();

    public MissionBook() {}

    public MissionBook(List<MissionInventoryInfo> infoList) {
        this.infoList = infoList;
    }

    public void addMissionBook(List<MissionInventoryInfo> infoList){
        this.infoList.addAll(infoList);
    }

}
