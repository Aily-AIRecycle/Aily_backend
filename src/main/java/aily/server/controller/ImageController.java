package aily.server.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    private static final String IMAGE_DIRECTORY = "C:/Users/rnral/OneDrive/바탕 화면/스프링/image/";

    @GetMapping(value = "image/{userid}.png", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("userid") String id) throws IOException {
        String imagePath = IMAGE_DIRECTORY + id + "/image.png";
        Path path = Paths.get(imagePath);

        if (Files.exists(path)) {
            byte[] imageBytes = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(imageBytes);
            return ResponseEntity.ok()
                    .contentLength(imageBytes.length)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } else {
            // 파일이 존재하지 않을 경우
            // 요구에 맞게 파일 생성
            createImageFile(id);

            // 파일을 다시 읽어와 리턴
            byte[] imageBytes = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(imageBytes);
            return ResponseEntity.ok()
                    .contentLength(imageBytes.length)
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        }
    }

    private void createImageFile(String id) throws IOException {
        String directoryPath = IMAGE_DIRECTORY + id;

        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        // 신규 파일 생성
        Files.createFile(directory);

        // 파일 생성 후에 필요한 초기화 작업 등을 수행할 수 있음
        // ...
    }
}
