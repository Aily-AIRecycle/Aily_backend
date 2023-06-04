package aily.server.service;

import aily.server.DTO.MyPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import aily.server.DTO.UserDTO;
import aily.server.entity.User;
import aily.server.repository.UserRepository;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(User user) {
        userRepository.save(user);
    }

    public UserDTO signIn(UserDTO params) {
        Optional<User> user = userRepository.findByEmail(params.getEmail());
        if(user.isPresent()){
            UserDTO userDTO = UserDTO.toUserDTO(user.get());
            String dbPass = userDTO.getPassword();
            String inputPass = params.getPassword();
            System.out.println("db = " + dbPass);
            System.out.println("input = " + inputPass);
            if(dbPass.equals(inputPass)){
                //all_ok
                userDTO.setPassword("");
                System.out.println("all_ok");
                return userDTO;
            }
            //id만 맞고 비밀번호 틀렸을 때 id_ok
            System.out.println("id_ok");
            return params;
        } else {
            System.out.println("faild");
            params.setEmail("Faild");
            return params;
        }
    }

    public String test(String phone) {
        Optional<User> user = userRepository.findUserByPhonenumber(phone);
        if(user.isPresent()){
            MyPageDTO myPageDTO = MyPageDTO.toMyPageDTO(user.get().getMyPage());
            return myPageDTO.toString();
        } else {
            return "notFound";
        }
    }

    public String checkEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return "no";
        } else {
            return "yes";
        }
    }
}