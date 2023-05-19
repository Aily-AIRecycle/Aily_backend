package aily.server.DTO;

import aily.server.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO extends MyPageDTO {
    private String phonenumber;
    private String password;
    private String birth;
    private String gender;
    private String email;

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setPhonenumber(user.getPhonenumber());
        userDTO.setPassword(user.getPassword());
        userDTO.setBirth(user.getBirth());
        userDTO.setGender(user.getGender());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
