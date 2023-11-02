package aily.server.controller;

import aily.server.DTO.MyPageDTO;
import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import aily.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class MyPageController{


    public final UserService userService;

    //회원수정 ( 이메일 인증제외 전부 완성 )
    @PostMapping("/member/UIC")
    public User userinformationcheck(@RequestBody UserDTO userDTO) throws IOException {
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
        return userService.getHistory(params);
    }

    @PostMapping("/member/usertotalDonut")
    public List<Map<String, Integer>> TotalDonutChart(@RequestBody UserDTO params) {
        return userService.UserTotalDonutChat(params);
    }
}
