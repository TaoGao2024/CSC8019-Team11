package com.team11.castleproj.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team11.castleproj.Entity.CastleInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CastleInfoRepository extends JpaRepository<CastleInfo, Integer> {
    @Query("SELECT c FROM CastleInfo c WHERE c.name = :name")
    Optional<CastleInfo> findByName(@Param("name") String name);
}
