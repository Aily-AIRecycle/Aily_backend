package aily.server.controller;

import aily.server.DTO.UserDTO;
import aily.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class ImageController {
    public final UserService userService;
    private static final String IMAGE_DIRECTORY = "/home/lee/image/";

        //유저 image 확인
        @GetMapping(value = "image/{userid}/image.png", produces = MediaType.IMAGE_JPEG_VALUE)
        public ResponseEntity<ByteArrayResource> getImage(@PathVariable("userid") String id) throws IOException {
            String imagePath = IMAGE_DIRECTORY + id + "/image.png";
            Path path = Paths.get(imagePath);
            System.out.println("getImage" + imagePath);
            byte[] imageBytes = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(imageBytes);
            return ResponseEntity.ok()
                    .contentLength(imageBytes.length)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
    }
    
    //유저 프로필사진 변경 url 경로
    @PostMapping("/member/upload/{userid}")
    public String handleFileUpload(@PathVariable("userid") String id,@RequestPart(value="image",required = false) MultipartFile file) throws IOException {
        System.out.println("============이미지 메소드 실행==============");
        if (file.isEmpty()) {
            // 파일이 없는 경우 처리
            return "redirect:/upload?error=empty";
        }

            System.out.println(id);
            // 저장할 디렉토리 경로
            Path uploadDir = Path.of(IMAGE_DIRECTORY + id);



            // 저장할 파일 경로1
//            Path filePath = Path.of(uploadDir + "/" + file.getOriginalFilename());
            Path filePath = uploadDir.resolve("image.png");
            System.out.println("handleFileUpload = "  + filePath);
            // 디렉토리가 없는 경우 생성
            File directory = new File(uploadDir.toUri());
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 파일 저장
            file.transferTo(new File(filePath.toUri()));

            // 파일 저장 성공 처리
            System.out.println("파일 저장 성공ok");
            return "redirect:/upload?success";

    }

    @PostMapping("/member/userimage")
    public ResponseEntity<String> userimageshow(@RequestBody UserDTO userDTO){
            userService.userimageshow(userDTO.getPhonenumber());
            String dd = userService.userimageshow(userDTO.getPhonenumber());
            System.out.println(dd);
            return ResponseEntity.ok().body(dd);
    }
}
