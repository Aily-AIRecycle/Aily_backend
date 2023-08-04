package aily.server.controller;


import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import aily.server.service.PointService;
import aily.server.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final UserService userService;

    private static final String JSON_FILE = "/home/lee/image/";
    int number = 0;
    @PostMapping("/member/point")
    public ResponseEntity<List<Map<String, Object>>> savePoint(@RequestBody UserDTO userDTO) throws IOException {
        User user;
        User newuser;
        String name;
        System.out.println("point" + userService.test(userDTO.getPhonenumber()));
        if(!userService.test(userDTO.getPhonenumber()).equals("NFT")) {
            name = userService.userPhonenumberbyNickname(userDTO.getPhonenumber());
            System.out.println("userName : " + name);
            newuser = User.saveToEntity(userDTO);
            pointService.savepoint(newuser);
            System.out.println("userOK");
        }else{
            number = number + 1;
            UserDTO newuserDTO = new UserDTO();
            newuserDTO.setPhonenumber(userDTO.getPhonenumber());
            newuserDTO.setNickname("NotUser" + number);
            newuserDTO.setPassword(userDTO.getPhonenumber());
            user = User.saveToEntity(newuserDTO);
            userService.getImage(newuserDTO.getNickname());
            userService.signUp(user);
            user = User.saveToEntity(userDTO);
            pointService.savepoint(user);
            name = newuserDTO.getNickname();
            System.out.println("userNO");
        }
        String jsonfilepath = JSON_FILE + name + "/" + name + ".json";
        File file = new File(jsonfilepath);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> data = new ArrayList<>();
        if (file.length() > 0) {
            data = objectMapper.readValue(file, new TypeReference<>() {});
        }
        Map<String, Object> newDate = new HashMap<>();
        SimpleDateFormat newnow = new SimpleDateFormat("yyyy년 MM월 dd일");
        SimpleDateFormat newnow2 = new SimpleDateFormat("HH시 mm분 ss초");

        String formatedNow = newnow.format(new Date());

        String formatedNow2 = newnow2.format(new Date());

        newDate.put("day",formatedNow );
        newDate.put("time",formatedNow2 );
        newDate.put("can", userDTO.getCAN());
        newDate.put("gen", userDTO.getGEN());
        newDate.put("pet", userDTO.getPET());
        newDate.put("point", userDTO.getPoint());
        data.add(newDate);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);

        return ResponseEntity.ok().body(data);
    }

}
