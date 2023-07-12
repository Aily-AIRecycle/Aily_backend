package aily.server.service;

import aily.server.entity.User;
import aily.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final UserRepository userRepository;
    public void savepoint(User user){
        userRepository.savePoint(user);
        System.out.println("Service : " + user.getMyPage().getCAN());
    }



}
