package com.team11.castleproj.Repository;

import com.team11.castleproj.Entity.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface ScheduleInfoRepository extends JpaRepository<ScheduleInfo, Integer> {
    @Query("SELECT s FROM ScheduleInfo s WHERE s.routeId = :routeId")
    List<ScheduleInfo> findByRouteId(@Param("routeId") String routeId);

    @Query("SELECT s FROM ScheduleInfo s " +
            "WHERE s.route.castleDirection = true " +
            "AND s.route.castleId = :castleId " +
            "AND s.departTime BETWEEN :lowerBound AND :upperBound")
    List<ScheduleInfo> findOutboundSchedules(@Param("lowerBound") LocalTime lowerBound, @Param("upperBound") LocalTime upperBound,@Param("castleId")String castleId);

    @Query("SELECT s FROM ScheduleInfo s " +
            "WHERE s.route.castleId = :castleId " +
            "AND s.route.castleDirection = false " +
            "AND s.departTime BETWEEN :lowerBound AND :upperBound")
    List<ScheduleInfo> findReturnSchedules(@Param("lowerBound") LocalTime lowerBound, @Param("upperBound") LocalTime upperBound,@Param("castleId")String castleId);


}


