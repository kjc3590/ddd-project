package io.github.wotjd243.findbyhint.hunter.application;

import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.hunter.domain.HunterDto;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.hunter.infra.HunterRepository;
import org.springframework.stereotype.Service;

@Service
public class HunterService {

    private HunterRepository repository;

    public HunterService(HunterRepository repository) {
        this.repository = repository;
    }


    public void hunterCreate(Hunter hunter) {
        repository.save(hunter);
    }

    public Hunter findById(String id) {
        return repository.findById(HunterId.valueOf(id)).get();
    }

    public Hunter getHunter(HunterDto dto) {

        Hunter hunter = new Hunter(dto.getHunterId(), dto.getHunterPw(), dto.getHunterName(), dto.getHunterPicturePath(), dto.getHunterPictureName(), dto.getHunterPoint(), dto.getHunterBullet());

        return hunter;
    }
}
