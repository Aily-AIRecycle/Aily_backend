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

    @GetMapping(value = "image/{userid}.png", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("userid") String id) throws IOException {
        String imagePath = "/home/lee/image/"+id+"/image.png";
        Path path = Paths.get(imagePath);

        byte[] imageBytes = Files.readAllBytes(path);

        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        return ResponseEntity.ok()
                .contentLength(imageBytes.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}

