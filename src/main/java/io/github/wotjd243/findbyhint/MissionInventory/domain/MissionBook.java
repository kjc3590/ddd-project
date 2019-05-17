package io.github.wotjd243.findbyhint.MissionInventory.domain;

import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class MissionBook {

    @ElementCollection
    private List<MissionInventoryInfo> missionBook = new ArrayList<>();

    public MissionBook() {}

    public void setMissionBook(List<MissionInventoryInfo> missionBook) {
        this.missionBook = missionBook;
    }

    public MissionBook(List<MissionInventoryInfo> missionBook) {
        this.missionBook = new ArrayList<>();
    }

//    public void addMissionBook(List<MissionInventoryInfo> infoList){
//        this.infoList.addAll(infoList);
//    }

}
