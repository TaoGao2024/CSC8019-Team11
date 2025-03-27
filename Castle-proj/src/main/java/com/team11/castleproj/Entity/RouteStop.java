package com.team11.castleproj.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="RouteStop")
public class RouteStop {
    @Id
    private RouteStopId routeStopId;
    private int sequenceNum; // sequence pos of stop in route

    @Column(nullable = true)
    private String busService; // e.g. X15

}
