package aily.server.controller;


import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import aily.server.service.PointService;
import aily.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final UserService userService;

    @PostMapping("/member/point")
    public ResponseEntity<String> savePoint(@RequestBody UserDTO userDTO){
        User user;
        if(!userService.test(userDTO.getPhonenumber()).equals("NFT")) {
            user = User.saveToEntity(userDTO);
            pointService.savepoint(user);
            return ResponseEntity.ok("PointSave");
        }else{
            int number = 0;
            number = number + 1;
            UserDTO newuserDTO = new UserDTO();
            newuserDTO.setPhonenumber(userDTO.getPhonenumber());
            newuserDTO.setNickname("NotUser" + number);
            newuserDTO.setPassword(userDTO.getPhonenumber());
            user = User.saveToEntity(newuserDTO);
            userService.signUp(user);
            user = User.saveToEntity(userDTO);
            pointService.savepoint(user);
            return ResponseEntity.ok("PointNotSave");
        }

    }

}
