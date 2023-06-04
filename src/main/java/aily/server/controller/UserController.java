package aily.server.controller;

import aily.server.DTO.MyPageDTO;
import aily.server.DTO.UserDTO;
import aily.server.authEmail.AuthRequest;
import aily.server.entity.User;
import aily.server.service.MailService;
import aily.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;
    public final MailService mailService;
    @PostMapping("/member/join")
    public ResponseEntity<String> save (@RequestBody UserDTO userDTO) {
        //System.out.println("userDTO = " + userDTO.toString() + " " + userDTO.getNickname());
        User user = User.saveToEntity(userDTO);
        userService.signUp(user);
        return ResponseEntity.ok("회원가입 완료!");

    }

    @PostMapping("/member/login")
    public ResponseEntity<UserDTO> loginRe (@RequestBody UserDTO params) {
        UserDTO loginResult = userService.signIn(params);
        System.out.println("loginResult = " + loginResult);
        return ResponseEntity.ok(loginResult);
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

    @PostMapping("/member/auth-email")
    public ResponseEntity<String> auth(@RequestBody AuthRequest request) throws Exception {
        String email = request.getEmail();
        System.out.println("요청한 Email = " + email + " 생성된 코드 = " + request.getCode());

        mailService.sendMail(email, "[Aily] 이메일 인증 코드 안내", request.getCode());

        return ResponseEntity.ok(request.getCode());
    }

}