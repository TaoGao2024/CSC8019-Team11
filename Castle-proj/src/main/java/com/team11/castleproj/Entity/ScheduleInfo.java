/*
 * Creation Date: Mar 29, 2025
 * Class for ScheduleInfo entity's schema, including an existing
 * departure and arrival time for a given route
 *
 * @author Samuel Leung
 * @version 1.0
 *
 *
 */
package com.team11.castleproj.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ScheduleInfo")
public class ScheduleInfo {
    @Id
    private int scheduleId;
    private int routeId;
    private String departTime;
    private String arriveTime;
}
