package aily.server.service;

import aily.server.entity.User;
import aily.server.repository.MyPageRepository;
import aily.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final UserRepository userRepository;

    private final MyPageRepository myPageRepository;
    private static final String IMAGE_DIRECTORY = "/home/lee/image/";
    private static final String sourceFilePath = "/home/lee/image/default/image.png";

    @Transactional
    public void userupdateinformation(User user, String number){
        System.out.println(user.getMyPage().getNickname());
        userRepository.updateUserEmail(user.getEmail(), number);
        userRepository.updateMyPageNickname(user.getMyPage().getNickname(), number);
        userRepository.updateMyPageProfile(user.getMyPage().getProfile(), number);
    }

    //회원 내정보 수정 데이터 조회
    public Optional<User> finduserinformation(String phonenumber){
        return userRepository.findByPhoneNumber(phonenumber);
    }

    //개인 쓰레기 데이터 조회
    public String userTotalDonutes(String phonenumber){
        return myPageRepository.finduserTotalDonut(phonenumber);
    }
}
