package aily.server.repository;

import aily.server.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
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
