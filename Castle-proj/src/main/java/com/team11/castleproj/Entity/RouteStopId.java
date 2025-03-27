package com.team11.castleproj.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RouteStopId implements Serializable {
    private int routeId;
    private int stopId;

    public RouteStopId() {}

    public RouteStopId(int routeId, int stopId) {
        this.routeId = routeId;
        this.stopId = stopId;
    }

}
