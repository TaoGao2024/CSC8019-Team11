/*
 * Creation Date: Mar 29, 2025
 * Class for RouteStop entity's schema, including its
 * sequence number (denoting the ordering of a stop in a
 * given route) and the service of the departing station (e.g. X15)
 *
 * @author Samuel Leung
 * @version 1.0
 *
 * Modification history:
 * 4/5 Samuel Leung - Added joined columns for stop information
 */
package com.team11.castleproj.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="RouteStop")
public class RouteStop {
    @Id
    private RouteStopId routeStopId;
    private int sequenceNum;

    @Column(nullable = true)
    private String busService;

    @ManyToOne
    @JoinColumn(name = "stopId", referencedColumnName = "stopId", insertable = false, updatable = false)
    private StopInfo stopInfo;

}
