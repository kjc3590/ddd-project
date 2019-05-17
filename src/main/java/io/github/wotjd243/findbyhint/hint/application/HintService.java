package io.github.wotjd243.findbyhint.hint.application;

import io.github.wotjd243.findbyhint.hint.domain.Hint;
import io.github.wotjd243.findbyhint.hint.domain.HintRepository;

import io.github.wotjd243.findbyhint.hunter.domain.Hunter;
import io.github.wotjd243.findbyhint.hunter.domain.HunterId;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.treasure.domain.TargetPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HintService {
    private HintRepository hintRepository;

    private TreasureService treasureService;

    public HintService(HintRepository hintRepository, TreasureService treasureService) {
        this.hintRepository = hintRepository;
        this.treasureService = treasureService;
    }

    //다른 애그리거트가 가져올
    //힌트르 주는 메소드 (타겟포인트)
    //헌터 ID,  보물 ID, HintCounter 개수

    //TODO (1) : 지워야할 좌표 넘겨주기
    public void addHint(String hunterId,int hintCount){

        // 현재 진행중인  보물 ID
        Long treasureId = treasureService.getTreasureIdByActive();

        //해당 hunterId 와 treasureId 로 ids 를 가져와야함 : 완전한 객체로 가져옴
        Hint hint = findByHunterIdAndTreasureId(hunterId,treasureId);

        List<Long> ids = getHintTargetPointIds(hunterId,treasureId);
        //기존의 있는 아이디 값 가져옴
        if(hint == null){
            hint = Hint.valueOf(hunterId,treasureId);
        }

        //보물의 가짜좌표 (힌트) hintCount 만큼 가져옴
        List<Long> getIds = treasureService.getTargetPointIds(treasureId,ids,hintCount);

        //가져온 아이디 값 기존의 힌트에 넣어줌
        hint.addBringTargetPointIds(getIds);

        //저장
        hintRepository.save(hint);

    }

    private Hint getHint(final Long hintId) {
        return hintRepository.findById(hintId).orElseThrow(IllegalArgumentException::new);
    }




    public List<Long> getHintTargetPointIds(final String hunterId, final Long treasureId){
        // 힌트객체 가져오기
        final Hint hint  = findByHunterIdAndTreasureId(hunterId,treasureId);
        List<Long> ids = new ArrayList<>();
        //TODO.1 해당 힌트에 대한 타겟 포인트의 집합  (타겟포인트들의 집합을 구하는 법)
        if(hint != null){
            if(hint.getBringTargetPointIds() != null){
                ids = hint.getBringTargetPointIds();
            }
        }
        return ids;

    }


    protected Hint findByHunterIdAndTreasureId(String hunterId, Long treasureId) {
        return hintRepository.findByHintMappedIds_HunterIdAndHintMappedIds_TreasureId(HunterId.valueOf(hunterId),treasureId);
    }


    // TODO (*) 헌터에게 보여줄 TargetPoint 넘겨주기
    public List<TargetPoint> getTargetPoints(String hunterId, Long treasureId){

        //현재 가지고 있는 타겟포인트 가져오기
        List<Long> targetPointIds = getHintTargetPointIds(hunterId,treasureId);

        return  treasureService.getTargetPointByIds(treasureId,targetPointIds);

    }



}