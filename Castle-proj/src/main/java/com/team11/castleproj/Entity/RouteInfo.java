/*
 * Creation Date: Mar 29, 2025
 * Class for RouteInfo entity's schema, including its price,
 * availability (e.g. weekday, sunday), whether route is in castle direction,
 * and travel time in minutes
 *
 * @author Samuel Leung
 * @version 1.0
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
@Table(name="RouteInfo")
public class RouteInfo {
    @Id
    private int routeId;
    private int castleId;
    private double price;

    @Column(nullable = false)
    private String availability;
    private boolean castleDirection;
    private int travelTime;
}
