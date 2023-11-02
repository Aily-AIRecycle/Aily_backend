package aily.server.controller;

import aily.server.DTO.UserDTO;
import aily.server.service.ImageService;
import aily.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@RestController
@RequiredArgsConstructor
public class ImageController {
    public final UserService userService;

    public final ImageService imageService;

    //유저 image 확인
    @GetMapping(value = "/member/image/{userid}/image.png", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("userid") String id) throws IOException {
       return imageService.UserGetImage(id);
    }

    //유저 프로필사진 변경 url 경로
    @PostMapping("/member/upload/{userid}")
    public String handleFileUpload(@PathVariable("userid") String id,@RequestPart(value="image",required = false) MultipartFile file) throws IOException {

        return imageService.uploadUserImage(id,file);

    }

    @PostMapping("/member/userimage")
    public ResponseEntity<String> userimageshow(@RequestBody UserDTO userDTO){
        userService.userimageshow(userDTO.getPhonenumber());
        String dd = userService.userimageshow(userDTO.getPhonenumber());
        System.out.println(dd);
        return ResponseEntity.ok().body(dd);
    }
}
