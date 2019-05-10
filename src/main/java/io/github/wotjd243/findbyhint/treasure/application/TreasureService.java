package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
//굳이 생성자를 만들지 않아도 해당 어노테이션으로 해결을 할 수 있는 것 같다. ,
@RequiredArgsConstructor
public class TreasureService {

    private final TreasureRepository treasureRepository;


    private Treasure getTreasure(Long treasureId) {
        return treasureRepository.findById(treasureId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isExist(Long treasureId) {
        return !ObjectUtils.isEmpty(getTreasure(treasureId));
    }


    //보물을 등록하는 메소드
    public void save(Treasure treasure){



    }

    public TreasureRepository getTreasureRepository() {
        return treasureRepository;
    }
}
