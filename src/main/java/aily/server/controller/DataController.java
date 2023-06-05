package aily.server.controller;

import aily.server.DTO.AvgDataDTO;
import aily.server.Scheduled.AutoData;
import aily.server.service.DataService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;
    private final AutoData autoData;
    @GetMapping("/member/dataset")
    public void ddd() throws ParseException {
            dataService.ResultAvgNumber();
            autoData.avgdatasave();
        }
    @GetMapping("/member/showshowata")
    public List<AvgDataDTO> dddd() throws ParseException {

        return dataService.findAvgData();
    }
}
