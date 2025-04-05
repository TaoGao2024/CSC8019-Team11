/*
 * Creation Date: Mar 29, 2025
 * Class for RouteStop entity's ID, establishing a compound
 * primary key with the route and stop ids
 *
 * @author Samuel Leung
 * @version 1.0
 *
 *
 */
package com.team11.castleproj.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RouteStopId implements Serializable {
    private String routeId;
    private String stopId;

    public RouteStopId() {}

    public RouteStopId(String routeId, String stopId) {
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
