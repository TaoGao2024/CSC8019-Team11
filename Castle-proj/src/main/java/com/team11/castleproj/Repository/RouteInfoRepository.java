package com.team11.castleproj.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team11.castleproj.Entity.RouteInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RouteInfoRepository extends JpaRepository<RouteInfo, String> {
    @Query("SELECT r FROM RouteInfo r WHERE r.castleId=:castleId AND r.castleDirection = true")
    List<RouteInfo> findDepartureRoutesByCastle(@Param("castleId") String castleId);

    @Query("SELECT r FROM RouteInfo r WHERE r.castleId = :castleId AND r.castleDirection = false")
    List<RouteInfo> findReturnRoutesByCastle(@Param("castleId") String castleId);

    @Query("SELECT r FROM RouteInfo r WHERE r.castleId = :castleId")
    List<RouteInfo> findAllRoutesByCastle(@Param("castleId") String castleId);

    @Query("SELECT r FROM RouteInfo r WHERE r.availability = :availability")
    List<RouteInfo> findByAvailability(@Param("availability") String availability);

    @Query("SELECT r FROM RouteInfo r WHERE r.castleId = :castleId AND r.availability = :availability")
    List<RouteInfo> findByCastleAndAvailability(
            @Param("castleId") String castleId,
            @Param("availability") String availability);

}