package aily.server.service;

import aily.server.repository.MyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MyPageRepository myPageRepository;
    private static final String IMAGE_DIRECTORY = "/home/lee/image/";
    private static final String sourceFilePath = "/home/lee/image/default/image.png";

}
