package aily.server.controller;

import aily.server.DTO.MyPageDTO;
import aily.server.DTO.UserDTO;
import aily.server.authEmail.AuthRequest;
import aily.server.entity.User;
import aily.server.service.MailService;
import aily.server.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class  UserController {
    public final UserService userService;
    public final MailService mailService;
    private static final String IMAGE_DIRECTORY = "/home/ubuntu/image/";


    //회원탈퇴 and redirect
    @RequestMapping("/member/leavuser")
    public void leaveuser(@RequestBody UserDTO userDTO, HttpServletResponse response) throws IOException {
        Map<String, String> ee = new HashMap<>();
        System.out.println(userDTO.getPassword());
        User user1 = User.saveToEntity(userDTO);
        try{
            if (!userService.test(userDTO.getPhonenumber()).equals("NFT") & userService.findpassworduser(user1).equals("yes")) {

                System.out.println(userService.test(userDTO.getPhonenumber()));
                User user = User.saveToEntity(userDTO);
                userService.deleteuser(user);
                String directoryPath = IMAGE_DIRECTORY + userDTO.getNickname();

                File directory = new File(directoryPath);
                if (directory.exists() && directory.isDirectory()) {
                    boolean deletionSuccess = deleteDirectory(directory);

                    if (deletionSuccess)  {
                        ee.put("result", "deleteuserok");
                    } else {
                        ee.put("result", "file error.vo1");
                    }
                } else {
                    ee.put("result", "file error.vo2");
                }
            }
        }catch (ClassCastException e){
            ee.put("result", "usererror");
        }
            String jsonResponse = new ObjectMapper().writeValueAsString(ee);
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
        }


    //----------------------------------------------------------------------------------------------
    //회원가입, 비회원으로 포인트 적립후, 회원가입시 기존 정보 삭제( MyPaeg 정보 옮기고)
    //사용자 폴더,파일 이름 변경
    //비회원일떄 회원가입
    @PostMapping("/member/join")
    public ResponseEntity<String> save1 (@RequestBody UserDTO userDTO) throws IOException {
        //System.out.println("userDTO = " + userDTO.toString() + " " + userDTO.getNickname());
        //if(userService.test(userDTO.getPhonenumber()).equals("NFT")) {
            User user = User.saveToEntity(UserDTO.FirstJoinUser(userDTO,"https://ailymit.store/member/image/" + userDTO.getNickname() + "/image.png"));
            userService.getImage(userDTO.getNickname());
            System.out.println("회원 아님");
            userService.signUp(user);
        //}
        return ResponseEntity.ok("회원가입 완료!");
    }
    //임시회원일떄 회원가입
    @PostMapping("/member/guestjoin")
    public ResponseEntity<String> save2 (@RequestBody UserDTO userDTO) throws IOException {
        //System.out.println("userDTO = " + userDTO.toString() + " " + userDTO.getNickname());

        //if(!userService.test(userDTO.getPhonenumber()).equals("NFT")){
            MyPageDTO usermypage = userService.getupdatemypage(userDTO.getPhonenumber());
            UserDTO joingestDTO = UserDTO.guestuserjoinUserDTO(userDTO,usermypage);
            User user = User.saveToEntity(joingestDTO);
            System.out.println("회원임");
            userService.delUser(user);
            userService.signUp(user);
            userService.renameFileFolder(userDTO.getNickname(), usermypage.getNickname());
        //}
        return ResponseEntity.ok("회원가입 완료!");
    }
    //----------------------------------------------------------------------------------------------

    @PostMapping("/member/login")
    public ResponseEntity<UserDTO> loginRe (@RequestBody UserDTO params) {
        UserDTO loginResult = userService.signIn(params);
        System.out.println("loginResult = " + loginResult);
        return ResponseEntity.ok(loginResult);
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

    //회원 관련 개인 폴더 삭제
    private static boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!deleteDirectory(file)) {
                        return false;
                    }
                }
            }
        }
        return directory.delete();
    }

}

