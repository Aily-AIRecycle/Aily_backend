package aily.server.controller;

import aily.server.DTO.MyPageDTO;
import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import aily.server.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class MyPageController{
    private static final String JSON_FILE = "/home/lee/image/";


    public final UserService userService;

    //회원수정 ( 이메일 인증제외 전부 완성 )
    @PostMapping("/member/UIC")
    public User userinformationcheck(@RequestBody UserDTO userDTO){
        userDTO.setProfile("https://ailymit.store/member/image/" + userDTO.getNickname() + "/image.png");
        User user = User.saveToEntity(userDTO);
        MyPageDTO myPageDTO;
        myPageDTO = userService.getupdatemypage(userDTO.getPhonenumber());
        userService.renameFileFolder(user.getMyPage().getNickname(),myPageDTO.getNickname());
        userService.userupdateinformation(user,userDTO.getPhonenumber());
        return null;
    }


    //내 정보 페이지 표시
    @PostMapping("/member/UIS")
    public Optional<User> userinformationshow(@RequestBody UserDTO userDTO){
        System.out.println("UIS ::::::: " + userService.finduserinformation(userDTO.getPhonenumber()));
        return userService.finduserinformation(userDTO.getPhonenumber());
    }


    @PostMapping("/member/historypax")
    public List<Map<String, Object>> historyPax(@RequestBody UserDTO params) {
        // JSON 파일을 읽어옵니다.
        String json = JSON_FILE + params.getNickname() + "/" + params.getNickname() + ".json";

        List<Map<String, Object>> content = null;
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(json));
            ObjectMapper objectMapper = new ObjectMapper();
            content = objectMapper.readValue(jsonData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @PostMapping("/member/usertotalDonut")
    public List<Map<String, Integer>> TotalDonutChart(@RequestBody UserDTO params) {
        List<Map<String, Integer>> dataList = new ArrayList<>();

        String userData = userService.userTotalDonutes(String.valueOf(params.getPhonenumber()));
        String trashname = "can,gen,pet";
        String[] splitData = userData.split(",");
        String[] splitData1 = trashname.split(",");

        Map<String, Integer> dataMap = new HashMap<>();
        for (int i = 0; i < splitData.length && i < splitData1.length; i++) {
            int value = Integer.parseInt(splitData[i]);
            String key = splitData1[i];
            dataMap.put(key, value);
        }

        dataList.add(dataMap);

        return dataList;
    }
}
