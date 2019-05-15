package io.github.wotjd243.findbyhint.mission.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Party {
    @ElementCollection
    private List<MissionInventory> party = new ArrayList<>();

    public Party(List<MissionInventory> party) {
        this.party = new ArrayList<>(party);
    }

    public void addPary(List<MissionInventory> party){
        party.addAll(party);
    }

}
