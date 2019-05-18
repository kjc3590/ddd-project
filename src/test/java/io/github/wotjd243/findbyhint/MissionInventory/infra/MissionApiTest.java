package io.github.wotjd243.findbyhint.MissionInventory.infra;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionApiTest {

    @Test
    public void 미션_API_호출하기() throws IOException {

        URL url = new URL("https://opentdb.com/api.php?amount=1");
        try (InputStream inputStream = url.openStream();
             JsonParser jsonParser = Json.createParser(inputStream)) {
            inputStream.toString();
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

    @Test
    public void 미션_API_호출해서_MAP으로_변경하기() throws IOException, IllegalAccessException {

        URL url = new URL("https://opentdb.com/api.php?amount=1");

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

        //System.out.println("results: " + results);
        String difficulty = (String) results.get(0).get("difficulty");
        String question = (String) results.get(0).get("question");
        String correct_answer = (String) results.get(0).get("correct_answer");
        String incorrect_answers = (String) results.get(0).get("incorrect_answers").toString();

        System.out.println("difficulty:: " + difficulty);
        System.out.println("question:: " + question);
        System.out.println("correct_answer:: " + correct_answer);
        System.out.println("incorrect_answers:: " + incorrect_answers);

    }

}