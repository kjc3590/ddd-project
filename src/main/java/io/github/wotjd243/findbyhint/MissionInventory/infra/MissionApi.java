package io.github.wotjd243.findbyhint.MissionInventory.infra;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wotjd243.findbyhint.MissionInventory.application.MissionDto;
import io.github.wotjd243.findbyhint.MissionInventory.application.MissionInventoryService;
import io.github.wotjd243.findbyhint.MissionInventory.domain.MissionSuccessStatus;
import io.github.wotjd243.findbyhint.mission.domain.Mission;
import io.github.wotjd243.findbyhint.mission.domain.MissionLevel;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonParser;

import java.io.IOException;
import java.io.InputStream;

import java.math.BigInteger;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Log
public class MissionApi {


    private TreasureService treasureService;
    private MissionInventoryService missionInventoryService;

    public MissionApi(TreasureService treasureService) {
        this.treasureService = treasureService;
    }

    // TODO (1) 불러온 API 미션 로직에 따라 변경하기.
    // TODO (2) 처음 문제 개수는 보물에 따라 정해져서 나옴
    // TODO (3) 문제를 맞출 때마다 포인트를 증정해 주어야함

    public void execute2(Model model, MissionDto missionDto) throws IOException, IllegalAccessException {

        Long treasureId = treasureService.getTreasureIdByActive();
        System.out.println("treasureId: " + treasureId);
        List<Long> ids = new ArrayList<>();

        // TODO (4) 문제는 쉬운 문제부터 -> 어려운 문제로 나와야 함
        Optional<Mission> result = treasureService.getMission(treasureId, ids);

        if (result != null) {
            Long id = result.get().getMissionId();
            String level = result.get().getMissionLevel().getLevelName();
            System.out.println("id: " + id);
            System.out.println("level: " + level);

//        ArrayList<Map<String, Object>> treasureList = 보물1 문제 데이터;
//            for(Map<String, Object> tr : treasureList) {
//                // tr.pk 데이터가 미션인벤토리 테이블에 값이있는지체크
//                if(값이없으면){
            URL url = new URL("https://opentdb.com/api.php?amount=1&difficulty="+level);

            System.out.println("url::" + url);

            ObjectMapper objectMapper = new ObjectMapper();
            // URL 에 있는 JSON String 을 Map으로 변환하기
            Map<String, Object> data = objectMapper.readValue(url,
                    new TypeReference<Map<String, Object>>() {
                    });

            List<Map<String, Object>> results = (List<Map<String, Object>>) data.get("results");
            if (results == null) {
                throw new IllegalAccessException("미션을 불러오지 못했습니다.");
            }
            System.out.println("results: " + results);
            String difficulty = (String) results.get(0).get("difficulty");
            String question = (String) results.get(0).get("question");
            String correct_answer = (String) results.get(0).get("correct_answer");
//            String incorrect_answers = (String) results.get(0).get("incorrect_answers");

            MissionLevel missionLevel = result.get().getMissionLevel();

            log.info("difficulty:: "+difficulty);
            log.info("question:: "+question);
            log.info("correct_answer:: "+correct_answer);
//            log.info("incorrect_answers:: "+incorrect_answers);

            missionDto = new MissionDto("aa", question, correct_answer, missionLevel);
            log.info("missionDto.getQuestion():: "+missionDto.getQuestion());
            log.info("missionDto.getAnswer():: "+missionDto.getAnswer());
            log.info("missionDto.getLevel():: "+missionDto.getLevel());
//            missionInventoryService.save(missionDto);

            model.addAttribute("question", question);
            model.addAttribute("correct_answer", correct_answer);
            model.addAttribute("incorrect_answers", results.get(0).get("incorrect_answers"));
//                    break;
//                }
        }
    }


    //open trivia db json
    public void execute(Model model) throws IOException {

        URL url = new URL("https://opentdb.com/api.php?amount=1");
        try (InputStream inputStream = url.openStream();
             JsonParser jsonParser = Json.createParser(inputStream)) {
            inputStream.toString();
            System.out.println("inputStream: " + inputStream);
            while (jsonParser.hasNext()) {
                JsonParser.Event event = jsonParser.next();
                if (event == JsonParser.Event.KEY_NAME) {
                    String key = jsonParser.getString();
                    event = jsonParser.next();
                    if (key.equals("question")) {
                        System.out.println("question: " + jsonParser.getString());
                    }
                    if (key.equals("correct_answer")) {
                        System.out.println("correct_answer: " + jsonParser.getString());
                    }
                    if (key.equals("incorrect_answers")) {

                        JsonArray jsonArray = jsonParser.getArray();
                        System.out.println("incurrect_answers: " + jsonArray);
                    }

                }
            }


        }

    }

}
