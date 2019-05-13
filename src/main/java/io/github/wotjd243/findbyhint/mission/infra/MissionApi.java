package io.github.wotjd243.findbyhint.mission.infra;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonParser;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.List;
import java.util.Map;

public class MissionApi {

    // TODO (1) 불러온 API 미션 로직에 따라 변경하기.
    // TODO (2) 현재는 난이도, 미션, 답만 무작위로 불러온 상태
    // TODO (3) 처음 문제 개수는 보물에 따라 정해져서 나옴
    // TODO (4) 문제는 쉬운 문제부터 -> 어려운 문제로 나와야 함
    // TODO (5) 문제를 맞출 때마다 포인트를 증정해 주어야함

        public void execute2(Model model) throws IOException, IllegalAccessException {
//        ArrayList<Map<String, Object>> treasureList = 보물1 문제 데이터;
//            for(Map<String, Object> tr : treasureList) {
//                // tr.pk 데이터가 미션인벤토리 테이블에 값이있는지체크
//                if(값이없으면){
                    URL url = new URL("https://opentdb.com/api.php?amount=1");

                    ObjectMapper objectMapper = new ObjectMapper();
                    // URL 에 있는 JSON String 을 Map으로 변환하기
                    Map<String, Object> data = objectMapper.readValue(url,
                            new TypeReference<Map<String,Object>>(){});

                    List<Map<String, Object>> results = (List<Map<String, Object>>)data.get("results");
                    if(results == null) {
                        throw new IllegalAccessException("미션을 불러오지 못했습니다.");
                    }
                    System.out.println("results: "+results);
                    System.out.println("results.get(0).get('difficulty'): "+results.get(0).get("difficulty"));
                    System.out.println("results.get(0).get('question'): "+results.get(0).get("question"));
                    System.out.println("results.get(0).get('correct_answer'): "+results.get(0).get("correct_answer"));
                    System.out.println("results.get(0).get('incorrect_answers'): "+results.get(0).get("incorrect_answers"));

                    model.addAttribute("question", results.get(0).get("question"));
                    model.addAttribute("correct_answer", results.get(0).get("correct_answer"));
                    model.addAttribute("incorrect_answers", results.get(0).get("incorrect_answers"));
//                    break;
//                }
            }


    //open trivia db json
    public void execute(Model model) throws IOException {

        URL url = new URL("https://opentdb.com/api.php?amount=1");
        try (InputStream inputStream = url.openStream();
             JsonParser jsonParser = Json.createParser(inputStream)) {
            inputStream.toString();
            System.out.println("inputStream: "+inputStream);
            while (jsonParser.hasNext()) {
                JsonParser.Event event = jsonParser.next();
                if (event == JsonParser.Event.KEY_NAME) {
                    String key = jsonParser.getString();
                    event = jsonParser.next();
                    if(key.equals("question")) {
                        System.out.println("question: " + jsonParser.getString());
                    }
                    if(key.equals("correct_answer")) {
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
