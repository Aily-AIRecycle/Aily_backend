package aily.server.controller;

import aily.server.DTO.UserDTO;
import aily.server.entity.MyPage;
import aily.server.entity.User;
import aily.server.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/member/login")
    public String login(){
        return "signIn";
    }

    @PostMapping("/member/login")
    public String loginRe (@ModelAttribute UserDTO params, HttpSession session) {
        String loginResult = userService.signIn(params);
        session.setAttribute("result", loginResult);
        return "signIn";
    }



}
