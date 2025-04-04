package com.team11.castleproj.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team11.castleproj.Entity.RouteInfo;

public interface RouteInfoRepository extends JpaRepository<RouteInfo, Integer> {
}