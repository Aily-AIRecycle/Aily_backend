package aily.server.DTO;

import aily.server.entity.MyPage;
import aily.server.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyPageDTO {
    private String phonenumber;
    private String nickname;
    private int point;
    private int PET;
    private int GEN;
    private int CAN;
    private String profile;

    public static MyPageDTO toMyPageDTO(MyPage myPage) {
        MyPageDTO myPageDTO = new MyPageDTO();
        myPageDTO.setPhonenumber(myPage.getUser().getPhonenumber());
        myPageDTO.setNickname(myPage.getNickname());
        myPageDTO.setPoint(myPage.getPoint());
        myPageDTO.setPET(myPage.getPET());
        myPageDTO.setGEN(myPage.getGEN());
        myPageDTO.setCAN(myPage.getCAN());
        myPageDTO.setProfile(myPage.getProfile());
        return myPageDTO;
    }
}
