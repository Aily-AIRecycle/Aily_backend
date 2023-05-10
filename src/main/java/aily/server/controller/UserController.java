package aily.server.controller;

import aily.server.DTO.UserDTO;
import aily.server.entity.MyPage;
import aily.server.entity.User;
import aily.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @PostMapping("/member/join")
    public String save (@ModelAttribute UserDTO userDTO, @RequestParam("nickname") String nickname) {
        System.out.println("userDTO = " + userDTO.toString() + ", nickname = " + nickname);
        User user = User.saveToEntity(userDTO, nickname);
        userService.signUp(user);
        return "signIn";
    }

    @GetMapping("/member/join")
    public String save() {
        return "signUp";
    }

    @PostMapping("/member/login")
    public UserDTO login (@RequestBody final UserDTO params) {
        System.out.println("dto's = " + params.toString());
        UserDTO userDTO = userService.signIn(params);
        System.out.println("return value = " + userDTO.toString());
        return userDTO;
    }

    @GetMapping("/member/login")
    public String login(){
        return "signIn";
    }

}
