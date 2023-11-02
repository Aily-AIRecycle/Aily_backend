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
        userDTO.setNickname(user.getMyPage().getNickname());
        userDTO.setProfile(user.getMyPage().getProfile());
        userDTO.setPoint(user.getMyPage().getPoint());
        userDTO.setCAN(user.getMyPage().getCAN());
        userDTO.setPET(user.getMyPage().getPET());
        userDTO.setGEN(user.getMyPage().getGEN());
        return userDTO;
    }

    public static UserDTO pointToUserDTO(UserDTO userDTO, int NotUserNumber){
        UserDTO pointUserDTO = new UserDTO();
        pointUserDTO.setPhonenumber(userDTO.getPhonenumber());
        pointUserDTO.setPassword(userDTO.getPhonenumber());
        pointUserDTO.setNickname("NotUser" + NotUserNumber);
        pointUserDTO.setProfile("https://ailymit.store/member/image/" + pointUserDTO.getNickname() + ".png");
        pointUserDTO.setPET(userDTO.getPET());
        pointUserDTO.setCAN(userDTO.getCAN());
        pointUserDTO.setGEN(userDTO.getGEN());

        return pointUserDTO;
    }

    public static UserDTO guestuserjoinUserDTO(UserDTO gestuserDTO, MyPageDTO gestuserMyPage){
        UserDTO newuserDTO = new UserDTO();
        newuserDTO.setPhonenumber(gestuserDTO.getPhonenumber());
        newuserDTO.setPassword(gestuserDTO.getPassword());
        newuserDTO.setBirth(gestuserDTO.getBirth());
        newuserDTO.setGender(gestuserDTO.getGender());
        newuserDTO.setEmail(gestuserDTO.getEmail());
        newuserDTO.setNickname(gestuserDTO.getNickname());
        newuserDTO.setProfile(gestuserDTO.getProfile());
        newuserDTO.setPoint(gestuserMyPage.getPoint());
        newuserDTO.setCAN(gestuserMyPage.getCAN());
        newuserDTO.setPET(gestuserMyPage.getPET());
        newuserDTO.setGEN(gestuserMyPage.getGEN());
        return newuserDTO;

    }

    public static UserDTO FirstJoinUser(UserDTO userDTO,String profileimageurl){
        userDTO.setProfile(profileimageurl);
        return userDTO;
    }
}
