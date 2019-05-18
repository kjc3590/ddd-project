package io.github.wotjd243.findbyhint.treasure.domain;

import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SendList {

    private List<HunterId> hunterIds = new ArrayList<>();

    public SendList(List<HunterId> hunterIds) {
        this.hunterIds = hunterIds;
    }

    public void addAll(List<HunterId> hunterIds){
        this.hunterIds.addAll(hunterIds);
    }

    public void add(HunterId hunterId){
        this.hunterIds.add(hunterId);
    }

}
