package aily.server.repository;

import aily.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User> findByEmail(String email);

    Optional<User> findUserByPhonenumber(String phone);
}
