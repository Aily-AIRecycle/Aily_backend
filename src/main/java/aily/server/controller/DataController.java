//package aily.server.controller;
//
//
//import aily.server.Scheduled.AutoData;
////import aily.server.Scheduled.AutoPercentData;
//import aily.server.service.DataService;
//import lombok.RequiredArgsConstructor;
//import org.json.simple.parser.ParseException;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@RestController
//@RequiredArgsConstructor
//public class DataController {
//
//    private final DataService dataService;
//    private final AutoData autoData;
////    private final AutoPercentData autoPercentData;
//
//    //데이터 파일 강제 저장 ( 테스트 용  )
//    @GetMapping("/member/dataset")
//    public void AvgData() throws ParseException {
//            autoData.avgdatasave();
//        }
//
//        //각종 테스트 url
////    @GetMapping("/member/test")
////    public Optional<List<String>> Test() {
////        return dataService.test();
////    }
//
//    //데이터 파일 리액트로 전송
//    @GetMapping("/member/avgdata")
//    public List<AvgDataDTO> AvgDataShow() {
//        return dataService.findAvgData();
//    }
//
//
////    @GetMapping("/member/sendpd")
////    public String PerCentDataSend() throws ParseException {
////        autoPercentData.PercentDataSend();
////        return "oK!";
////    }
//
//
//}
