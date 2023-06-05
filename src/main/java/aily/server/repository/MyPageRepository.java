package aily.server.repository;

import aily.server.entity.MyPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyPageRepository extends JpaRepository<MyPage, Long> {
    Optional<MyPage> findByNickname(String nickname);
}
