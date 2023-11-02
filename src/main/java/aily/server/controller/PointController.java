package aily.server.controller;


import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import aily.server.service.PointService;
import aily.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final UserService userService;

    int number = 0;

    @PostMapping("/member/point")
    public ResponseEntity<List<Map<String, Object>>> savePoint(@RequestBody UserDTO userDTO) throws IOException {
        String name;
        System.out.println("point" + userService.test(userDTO.getPhonenumber()));
        if(!userService.test(userDTO.getPhonenumber()).equals("NFT")) {
            System.out.println("userName : " + userService.userPhonenumberbyNickname(userDTO.getPhonenumber()));
            pointService.savepoint(User.saveToEntity(userDTO));
            name = userService.userPhonenumberbyNickname(userDTO.getPhonenumber());
            System.out.println("userOK");
        }else{
            number = number + 1;
            UserDTO newuserDTO = UserDTO.pointToUserDTO(userDTO, number);
            userService.getImage(newuserDTO.getNickname());
            userService.signUp(User.saveToEntity(newuserDTO));
            pointService.savepoint(User.saveToEntity(newuserDTO));
            name = newuserDTO.getNickname();
            System.out.println("userNO");
        }
        return ResponseEntity.ok().body(pointService.writeuserjson(name,userDTO));
    }

}
