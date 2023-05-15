package aily.server.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignDTO {
    private String phonenumber;
    private String password;
    private String birth;
    private String gender;
    private String email;
    private String nickname;

    public static UserDTO toUserDTO(SignDTO signDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setPhonenumber(signDTO.getPhonenumber());
        userDTO.setPassword(signDTO.getPassword());
        userDTO.setBirth(signDTO.getBirth());
        userDTO.setGender(signDTO.getGender());
        userDTO.setEmail(signDTO.getEmail());
        userDTO.setNickname(signDTO.getNickname());
        return userDTO;
    }
}
