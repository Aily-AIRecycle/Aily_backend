//package aily.server.Scheduled;
//
//
//import aily.server.service.DataService;
//import lombok.RequiredArgsConstructor;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//@Component
//@RequiredArgsConstructor
//public class AutoData {
//
//    @Autowired
//    private final DataService dataService;
//
//    //정해진 시간에 매일 한번만 작동하는 클래스
//    //flask서버에서 데이터를 받아와 가공 처리후 db에 저장
//    //초 분 시
//    @Scheduled(cron = "06 59 23 * * *")
//    public void avgdatasave() throws ParseException {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://175.113.68.69:2222/member/ailydate";
//        String response = restTemplate.getForObject(url, String.class);
//        JSONParser jsonParser = new JSONParser();
//        JSONObject obj = (JSONObject) jsonParser.parse(response);
//        System.out.println("obj size : : : : :" + obj.size());
//        for (int i = 0; i < obj.size(); i++) {
//            AvgDataDTO avgDataDTO = new AvgDataDTO();
//            avgDataDTO.setAilynumber(String.valueOf(i + 1));
//            JSONObject map = (JSONObject) obj.get(String.valueOf(i + 1));
//
//            avgDataDTO.setAvgpet(String.valueOf(map.get("pet")));
//            avgDataDTO.setAvggen(String.valueOf(map.get("gen")));
//            avgDataDTO.setAvgcan(String.valueOf(map.get("can")));
//            avgDataDTO.setDay(String.valueOf(map.get("day")));
//            System.out.println(avgDataDTO.getDay());
//            dataService.saveData(avgDataDTO);
//            System.out.println("Save OK");
//        }
//    }
//
//}
