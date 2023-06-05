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


    @Query("SELECT COUNT(DISTINCT e.id) FROM AvgDataEntity e")
    Optional<Long> countDistinctNumber();

    @Query(value = "SELECT a.* FROM AvgDataEntity a ORDER BY a.day DESC LIMIT :limit", nativeQuery = true)
    Optional<List<AvgDataEntity>> findRecentDataOptional(@Param("limit") Optional<Long> limit);
}