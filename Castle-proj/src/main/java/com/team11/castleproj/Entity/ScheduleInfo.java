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

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name="ScheduleInfo")
public class ScheduleInfo {
    @Id
    private String scheduleId;
    @Column(nullable = false)
    private String routeId;
    @Column(nullable = false)
    private LocalTime departTime;
    @Column(nullable = false)
    private LocalTime arriveTime;

    @ManyToOne
    @JoinColumn(name = "routeId", referencedColumnName = "routeId", insertable = false, updatable = false)
    private RouteInfo route;
}
