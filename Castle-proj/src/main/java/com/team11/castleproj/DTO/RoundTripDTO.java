package com.team11.castleproj.DTO;

import com.team11.castleproj.Entity.RouteStop;
import com.team11.castleproj.Entity.ScheduleInfo;
import lombok.Getter;

import java.util.List;

public class RoundTripDTO {
    private final ScheduleInfo outboundSchedule;
    private final ScheduleInfo returnSchedule;
    private double totalPrice;
    private List<RouteStop> outboundStops, returnStops;

    public RoundTripDTO(ScheduleInfo outboundSchedule, ScheduleInfo returnSchedule) {
        this.outboundSchedule = outboundSchedule;
        this.returnSchedule = returnSchedule;
        this.totalPrice = 0.0;
    }

    public RoundTripDTO(ScheduleInfo outboundSchedule, ScheduleInfo returnSchedule, List<RouteStop> outboundStops, List<RouteStop> returnStops) {
        this(outboundSchedule, returnSchedule);
        this.outboundStops = outboundStops;
        this.returnStops = returnStops;
    }

    public void calcTotalPrice(double entryFee, int noOfVisitors) {
        double outboundPrice = outboundSchedule.getRoute().getPrice();
        double returnPrice = returnSchedule.getRoute().getPrice();
        totalPrice = (outboundPrice + returnPrice + entryFee) * noOfVisitors;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
