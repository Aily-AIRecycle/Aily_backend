package aily.server.Scheduled;


import aily.server.DTO.AvgDataDTO;
import aily.server.service.DataService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AutoData {

    @Autowired
    private final DataService dataService;
    //flask와 시간을 맞춰 매일 정해진 시간에 작동하는 어노테이션
    //초 분 시
//    @Scheduled(cron = "03 30 15 * * *")
//    @Scheduled(cron = "0/10 * * * * *")
    public void avgdatasave() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://175.113.68.69:2222/member/ailydate";
        String response = restTemplate.getForObject(url, String.class);
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = (JSONObject) jsonParser.parse(response);
        System.out.println("obj size : : : : :" + obj.size());
        //        새 Garbege 데이터 생성
        //        randomService.randomdata();
        for (int i = 0; i < obj.size()-1; i++) {
            AvgDataDTO avgDataDTO = new AvgDataDTO();
            avgDataDTO.setAilynumber(String.valueOf(i + 1));
            JSONObject map = (JSONObject) obj.get(String.valueOf(i + 1));

            avgDataDTO.setAvgpet(String.valueOf(map.get("pet")));
            avgDataDTO.setAvggen(String.valueOf(map.get("gen")));
            avgDataDTO.setAvgcan(String.valueOf(map.get("can")));
            avgDataDTO.setDay(String.valueOf(map.get("day")));

            dataService.saveData(avgDataDTO);
        }

    }
}
