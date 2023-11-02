package aily.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageService {
    //User Image 파일 위치
    private static final String IMAGE_DIRECTORY = "/home/ubuntu/image/";

    public ResponseEntity<ByteArrayResource> UserGetImage(String id) throws IOException {
        String imagePath = IMAGE_DIRECTORY + id + "/image.png";
        Path path = Paths.get(imagePath);
        System.out.println("getImage: " + imagePath);
        byte[] imageBytes = Files.readAllBytes(path);

        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        CacheControl cacheControl = CacheControl.noCache();

        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .contentLength(imageBytes.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
    public String uploadUserImage(String id, MultipartFile file) throws IOException {
        System.out.println("============이미지 메소드 실행==============");
        if (file.isEmpty()) {
            // 파일이 없는 경우 처리
            return "redirect:/upload?error=empty";
        }
        // 저장할 디렉토리 경로
        Path uploadDir = Path.of(IMAGE_DIRECTORY + id);

        // 저장할 파일 경로1
        //Path filePath = Path.of(uploadDir + "/" + file.getOriginalFilename());
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
}
