package com.team11.castleproj.Repository;

import com.team11.castleproj.Entity.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteStopRepository extends JpaRepository<RouteStop, Integer> {
    @Query("SELECT rs FROM RouteStop rs WHERE rs.routeStopId.routeId=:routeId ORDER BY rs.sequenceNum")
    List<RouteStop> findRouteStopsByRouteId(String routeId);
}

