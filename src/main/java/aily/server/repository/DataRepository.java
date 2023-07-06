package aily.server.repository;

import aily.server.entity.AvgDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DataRepository extends JpaRepository<AvgDataEntity, Long> {


    @Query("SELECT DISTINCT a.id FROM AvgDataEntity a")
    Optional<List<String>> findnumbers();

    @Query(value = "SELECT a FROM AvgDataEntity a WHERE a.id = :number ORDER BY a.day DESC LIMIT 1")
    Optional<AvgDataEntity> findLatestByNumber(@Param("number") String number);


}