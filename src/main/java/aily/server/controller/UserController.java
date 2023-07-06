package aily.server.controller;

import aily.server.DTO.UserDTO;
import aily.server.authEmail.AuthRequest;
import aily.server.entity.MyPage;
import aily.server.entity.User;
import aily.server.service.MailService;
import aily.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class  UserController {
    public final UserService userService;
    public final MailService mailService;


    //각종 테스트 url
//    @PostMapping("/member/join")
//    public ResponseEntity<String> Test(@RequestBody UserDTO userDTO) {
//        //System.out.println("userDTO = " + userDTO.toString() + " " + userDTO.getNickname());
//        userDTO.setProfile("dddd");
//        System.out.println("DtoMypqge :: " + userDTO.getProfile());
//        User user = User.saveToEntity(userDTO);
//        System.out.println("mypqge :: " + user.getMyPage().getProfile());
//        userService.imageUp(user);
//
//        return ResponseEntity.ok("회원가입 완료!");
//    }


    @PostMapping("/member/join")
    public ResponseEntity<String> save (@RequestBody UserDTO userDTO) throws IOException {
        //System.out.println("userDTO = " + userDTO.toString() + " " + userDTO.getNickname());

        userDTO.setProfile("http://localhost:8072/member/image/" + userDTO.getNickname() + ".png");
        User user = User.saveToEntity(userDTO);
        userService.getImage(userDTO.getNickname());
        System.out.println("Nickname :: " + userDTO.getNickname());
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
        return userService.test(phone);
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

    @GetMapping("/member/EmailFind/{email}")
    public ResponseEntity<String> emailFind(@PathVariable String email){
        String result = userService.checkEmail(email);
        if(result.equals("no")){
            //중복됨 == 있다는 거
            result = email;
        } else if(result.equals("yes")){
            //중복안됨 == 없음
            result = "NotFound";
        } else {
            result = "error";
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/member/ChPwd")
    public ResponseEntity<String> checkPwd(@RequestBody UserDTO userDTO){
        String result = userService.checkPwd(userDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/member/ChPwd/ch")
    public ResponseEntity<String> changPwd(@RequestBody UserDTO userDTO){
        String result = userService.changPwd(userDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/member/ChNick/{nickname}")
    public ResponseEntity<String> checkNick(@PathVariable String nickname){
        String result = userService.checkNick(nickname);
        return ResponseEntity.ok(result);
    }

}