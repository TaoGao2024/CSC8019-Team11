/*
 * Creation Date: Apr 4, 2025
 * Repository for fetching stop-related information
 *
 * @author Samuel Leung
 * @version 1.1
 *
 * Modification history:
 * 4/5 Samuel Leung - Add query for stop by stopId
 */

package com.team11.castleproj.Repository;

import com.team11.castleproj.Entity.StopInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StopInfoRepository extends JpaRepository<StopInfo, Integer> {
    @Query("SELECT s FROM StopInfo s WHERE s.stopId = :stopId")
    StopInfo findStopById(int stopId);
}

