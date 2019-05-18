package io.github.wotjd243.findbyhint.hunter.application;

import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.hunter.infra.HunterRepository;
import io.github.wotjd243.findbyhint.treasure.domain.SendList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HunterApiService {

    private HunterRepository repository;

    public HunterApiService(HunterRepository repository) {
        this.repository = repository;
    }

    public SendList getSendListAll(){
        List<HunterId> hunterIds = repository.findAll().parallelStream()
                .map(Hunter::getHunterId)
                .collect(Collectors.toList());
        return new SendList(hunterIds);
    }

}
