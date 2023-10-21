package aily.server.repository;

import aily.server.entity.redict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository                                         //어떤엔티티를 넣을것이냐, 엔티티의 id의 타입
//@EnableJpaRepositories
public interface BoardRepository extends JpaRepository<redict, Long> {
    List<redict> findByTitleContaining(String searchKeyword);

    //URL 주소에 따라 DB에서 재활용사전 데이터 값 뽑음
    @Query(value = "SELECT r FROM redict r WHERE SUBSTRING(r.number, 1, 1) = :s1")
    List<redict> findByCategory(String s1);

}