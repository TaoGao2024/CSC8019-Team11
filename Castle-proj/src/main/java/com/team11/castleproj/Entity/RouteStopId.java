package com.team11.castleproj.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RouteStopId implements Serializable {
    private int routeId;
    private int stopId;

    public RouteStopId() {}

    public RouteStopId(int routeId, int stopId) {
        this.routeId = routeId;
        this.stopId = stopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteStopId that = (RouteStopId) o;
        return routeId == that.routeId && stopId == that.stopId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, stopId);
    }

}
