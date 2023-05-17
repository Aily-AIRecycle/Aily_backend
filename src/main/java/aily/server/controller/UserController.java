package aily.server.controller;

import aily.server.DTO.SignDTO;
import aily.server.DTO.UserDTO;
import aily.server.entity.MyPage;
import aily.server.entity.User;
import aily.server.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @PostMapping("/member/join")
    public ResponseEntity<String> save (@RequestBody SignDTO signDTO) {
        System.out.println("signDTO = " + signDTO.toString());
        UserDTO userDTO = SignDTO.toUserDTO(signDTO);
        User user = User.saveToEntity(userDTO);
        userService.signUp(user);
        return ResponseEntity.ok("회원가입 완료!");
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
    public String loginRe (@RequestBody UserDTO params) {
        String loginResult = userService.signIn(params);
        return loginResult;
    }

    @GetMapping("/member/{phone}")
    public String mypage (@PathVariable String phone) {
        String info = userService.test(phone);
        return info;
    }

    @PostMapping("/member/EmailCheck")
    public String emailCheck (@RequestBody UserDTO userDTO) {
        System.out.println("email = " + userDTO.getEmail());
        return userService.checkEmail(userDTO.getEmail());
    }

}
