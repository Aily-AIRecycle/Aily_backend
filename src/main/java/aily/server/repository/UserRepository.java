package aily.server.repository;

import aily.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    @Query(value = "SELECT m.profile FROM MyPage m JOIN User u ON m.user = u.phonenumber where u.phonenumber = :phonenumber")
    String finduserprofile(String phonenumber);

    @Query(value = "SELECT u FROM MyPage m JOIN User u ON m.user = u.phonenumber where u.phonenumber = :pnumber")
    Optional<User> findByPhoneNumber(String pnumber);

    @Modifying
    @Query(value = "UPDATE User u SET u.email = :email WHERE u.phonenumber = :phonenumber")
    void updateUserEmail(String email, String phonenumber);

    @Modifying
    @Query(value = "UPDATE MyPage m SET m.nickname = :nickname WHERE m.user.phonenumber = :phonenumber")
    void updateMyPageNickname(String nickname, String phonenumber);

    @Modifying
    @Query(value = "UPDATE MyPage m SET m.profile = :profile WHERE m.user.phonenumber = :phonenumber")
    void updateMyPageProfile(String profile, String phonenumber);

    @Query(value = "SELECT true FROM User u where u.password = :password")
    boolean findUserPassword(String password);

    Optional<User> findByEmail(String email);

    Optional<User> findUserByPhonenumber(String phone);

    @Query(value = "SELECT u.phonenumber FROM MyPage m JOIN User u ON m.user = u.phonenumber WHERE m.nickname = :nickname")
    String findPhoneNumberByNickname(String nickname);

    @Query(value = "SELECT u.myPage.nickname FROM MyPage m JOIN User u ON m.user = u.phonenumber WHERE u.phonenumber = :phonenumber")
    String findNameByPhonenumber(String phonenumber);



    //비회원 회원 가입시 기존에있던 정보를 옮기고 저장시킴
    default void savePoint(User user1) {
        System.out.println("Service : " + user1.getMyPage().getCAN());
        Optional<User> userOptional = findUserByPhonenumber(user1.getPhonenumber());
        userOptional.ifPresent(user -> {
            user.getMyPage().setCAN(user.getMyPage().getCAN() + user1.getMyPage().getCAN());
            user.getMyPage().setGEN(user.getMyPage().getGEN() + user1.getMyPage().getGEN());
            user.getMyPage().setPET(user.getMyPage().getPET() + user1.getMyPage().getPET());
            user.getMyPage().setPoint(user.getMyPage().getPoint() + user1.getMyPage().getPoint());
            save(user);
        });
    }
}
