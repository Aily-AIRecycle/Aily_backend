package aily.server.repository;

import aily.server.entity.MyPage;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MyPageRepository extends JpaRepository<MyPage, Long> {
    Optional<MyPage> findByNickname(String nickname);

    @Query(value = "SELECT u.myPage.CAN, u.myPage.GEN, u.myPage.PET FROM MyPage m JOIN User u ON m.user = u.phonenumber WHERE u.phonenumber = :phonenumber")
    String finduserTotalDonut(String phonenumber);



}
