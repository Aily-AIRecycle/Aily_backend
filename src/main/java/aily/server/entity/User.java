package aily.server.entity;

import aily.server.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class User {
    @Id
    @Column(name = "phone_number")
    private String phonenumber;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_day")
    private String birth;

    @Column(name="email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private MyPage myPage;

    public static User saveToEntity(UserDTO userDTO) {
        User user = new User();
        MyPage myPage = new MyPage();
        user.setPassword(userDTO.getPassword());
        user.setBirth(userDTO.getBirth());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhonenumber(userDTO.getPhonenumber());
        myPage.setNickname(userDTO.getNickname());
        myPage.setProfile(userDTO.getProfile());
        myPage.setPoint(userDTO.getPoint());
        myPage.setPET(userDTO.getPET());
        myPage.setGEN(userDTO.getGEN());
        myPage.setCAN(userDTO.getCAN());
        user.setMyPage(myPage);
        myPage.setUser(user);
        System.out.println("메소드 실행됨");
        return user;
    }
}
