package io.github.wotjd243.findbyhint.MissionInventory.infra;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionInventoryInfo;
import io.github.wotjd243.findbyhint.mission.domain.Mission;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.net.URL;

import java.util.*;

@Component
@Log
public class MissionApi {

    // COMPLETE (1) 불러온 API 미션 로직에 따라 변경하기.
    // COMPLETE (2) 처음 문제 개수는 보물에 따라 정해져서 나옴
    // TODO (3) 문제를 맞출 때마다 포인트를 증정해 주어야함

    // COMPLETE 받아온 미션 ID를 인자로 받는 미션 호출 API 수정한다.
    // COMPLETE List<Long> ids를 hunterId, activetreasureId 를 인자로 받아 찾아오는 메소드를 만들기

    public Optional<MissionInventoryInfo> findByMission(Mission mission) throws IOException, IllegalAccessException {

        if (mission == null) {
            throw new IllegalAccessException("미션이 없습니다.");
        }

        String levelName = mission.getMissionLevel().getLevelName();
        Long missionId = mission.getMissionId();

        URL url = new URL("https://opentdb.com/api.php?amount=1&difficulty=" + levelName);

        System.out.println("url::" + url);

        ObjectMapper objectMapper = new ObjectMapper();
        // URL 에 있는 JSON String 을 Map으로 변환하기
        Map<String, Object> data = objectMapper.readValue(
                url, new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> results = (List<Map<String, Object>>) data.get("results");

        if (results == null) {
            throw new IllegalAccessException("미션을 불러오지 못했습니다.");
        }

        //System.out.println("results: " + results);
        String difficulty = (String) results.get(0).get("difficulty");
        String question = (String) results.get(0).get("question");
        String answer = (String) results.get(0).get("correct_answer");
        String wrongAnswer = (results.get(0).get("incorrect_answers").toString());

        apiInfo(difficulty, question, answer, wrongAnswer);

        return Optional.ofNullable(MissionInventoryInfo.valueOf(missionId, question, answer, wrongAnswer));

    }

    //로그
    private void apiInfo(String difficulty, String question, String correct_answer, String wrongAnswer) {
        log.info("difficulty:: " + difficulty);
        log.info("question:: " + question);
        log.info("correct_answer:: " + correct_answer);
        log.info("wrongAnswer:: " + wrongAnswer);
    }

}
