package com.team11.castleproj.Entity;

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
    private String availability;
    private boolean castleDirection;
    private int travelTime;

}
