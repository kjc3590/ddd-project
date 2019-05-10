package io.github.wotjd243.findbyhint.mission.infra;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import org.glassfish.json.JsonUtil;
import org.springframework.ui.Model;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonParser;

import java.io.IOException;
import java.io.InputStream;

import java.lang.reflect.Member;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissionApi {



    public Map<String, Object> mapFromJson (Model model) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        // URL 에 있는 JSON String 을 Map으로 변환하기
        Map<String, Object> data = objectMapper.readValue(
                new URL("https://opentdb.com/api.php?amount=1"),
                new TypeReference<Map<String,Object>>(){});
        System.out.println("data::" +data);
        System.out.println("data.get: "+data.get("value"));
        System.out.println("data.results: "+data.get("results"));

        List<Map<String, Object>> results = (List<Map<String, Object>>)data.get("results");
        System.out.println("results: "+results);
        System.out.println("results.get(0).get('difficulty'): "+results.get(0).get("difficulty"));
        System.out.println("results.get(0).get('question'): "+results.get(0).get("question"));
        System.out.println("results.get(0).get('correct_answer'): "+results.get(0).get("correct_answer"));
        System.out.println("results.get(0).get('incorrect_answers'): "+results.get(0).get("incorrect_answers"));


        model.addAttribute("difficulty", results.get(0).get("difficulty"));
        model.addAttribute("question", results.get(0).get("question"));
        model.addAttribute("correct_answer", results.get(0).get("correct_answer"));
        model.addAttribute("incorrect_answers", results.get(0).get("incorrect_answers"));

        return mapFromJson(model);

    }

//    public static List<Map<String, Object>> getListMapFromJsonArray( JsonArray jsonArray )
//    {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        if( jsonArray != null )
//        {
//            int jsonSize = jsonArray.size();
//            for( int i = 0; i < jsonSize; i++ )
//            {
//                Map<String, Object> map = JsonUtil.getMapFromJsonObject((JsonObject) jsonArray.get(i) );
//                list.add( map );
//            }
//        }
//
//        return list;
//    }


    // TODO (1) 불러온 API 미션 로직에 따라 변경하기.
    // TODO (2) 현재는 난이도, 미션, 답만 무작위로 불러온 상태
    // TODO (3) 처음 문제 개수는 보물에 따라 정해져서 나옴
    // TODO (4) 문제는 쉬운 문제부터 -> 어려운 문제로 나와야 함
    // TODO (5) 문제를 맞출 때마다 포인트를 증정해 주어야함

    //public static void main(String[] args, Model model) throws IOException {
        public void execute2(Model model) throws IOException {

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

//            ObjectMapper objectMapper = new ObjectMapper();
//            // URL 에 있는 JSON String 을 Map으로 변환하기
//            Map<String, Object> data = objectMapper.readValue(
//                    new URL("https://opentdb.com/api.php?amount=1"),
//                    new TypeReference<Map<String,Object>>(){});
//            System.out.println("data::" +data);
//            System.out.println("data.get: "+data.get("value"));
//            System.out.println("data.results: "+data.get("results"));
//
//            List<Map<String, Object>> results = (List<Map<String, Object>>)data.get("results");
//            System.out.println("results: "+results);
//            System.out.println("results.get(0).get('difficulty'): "+results.get(0).get("difficulty"));
//            System.out.println("results.get(0).get('question'): "+results.get(0).get("question"));
//            System.out.println("results.get(0).get('correct_answer'): "+results.get(0).get("correct_answer"));
//            System.out.println("results.get(0).get('incorrect_answers'): "+results.get(0).get("incorrect_answers"));
//
//
//            model.addAttribute("data", data);


    }



//    public static void main(String[] args)  throws IOException {
//    public void execute(Model model) throws IOException {
//
//        URL url = new URL("https://opentdb.com/api.php?amount=10&type=boolean");
//        try(InputStream inputStream = url.openStream();
//            JsonParser jsonParser = Json.createParser(inputStream)) {
//            while ((jsonParser.hasNext())) {
//                JsonParser.Event event = jsonParser.next();
//                if(event == JsonParser.Event.KEY_NAME) {
//                    String key = jsonParser.getString();
//                    switch (jsonParser.getString()) {
//                        case "difficulty" :
//                            jsonParser.next();
//                            System.out.println("난이도: "+jsonParser.getString());
//                            model.addAttribute("difficulty", jsonParser.getString());
//                            break;
//                        case "question" :
//                            jsonParser.next();
//                            System.out.println("미션: "+jsonParser.getString());
//                            model.addAttribute("question", jsonParser.getString());
//                            break;
//                        case "correct_answer" :
//                            jsonParser.next();
//                            System.out.println("답: "+jsonParser.getString());
//                            model.addAttribute("correct_answer", jsonParser.getString());
//                            break;
//                        case "incorrect_answers" :
//                            jsonParser.next();
//                            System.out.println("틀린답: "+jsonParser.getArray());
//                            System.out.println("--------");
//                            if(key.equals("incorrect_answers")) {
//
//                                JsonArray jsonArray = jsonParser.getArray();
//                              model.addAttribute("incorrect_answers", jsonArray);
//                            }
//                            break;
//
//                    }
//                }
//
//            }
//        }
//
//    }

    //퀴즈 API
//    public static void main(String[] args) {
//
//        String content = null;
//        URLConnection connection = null;
//        String content2 = null;
//
//        try {
//            connection = new URL("https://opentdb.com/api_category.php").openConnection();
//            Scanner scanner = new Scanner(connection.getInputStream());
//            scanner.useDelimiter("\\Z");
//            content = scanner.next();
//            scanner.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("content: "+ content);
//        //return execute();
//    }

}
