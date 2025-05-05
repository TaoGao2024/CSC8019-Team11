package com.team11.castleproj.DTO;

import com.team11.castleproj.Entity.RouteStop;
import com.team11.castleproj.Entity.ScheduleInfo;

import java.util.List;

public class RoundTripDTO {
    private ScheduleInfo outboundSchedule;
    private ScheduleInfo returnSchedule;
    private double totalPrice;
    private List<RouteStop> outboundStops, returnStops;

    public RoundTripDTO(ScheduleInfo outboundSchedule, ScheduleInfo returnSchedule, List<RouteStop> outboundStops, List<RouteStop> returnStops) {
        this.outboundSchedule = outboundSchedule;
        this.returnSchedule = returnSchedule;
        this.totalPrice = 0.0;
        this.outboundStops = outboundStops;
        this.returnStops = returnStops;
    }

    public ScheduleInfo getOutboundSchedule() {
        return outboundSchedule;
    }

    public ScheduleInfo getReturnSchedule() {
        return returnSchedule;
    }

    public List<RouteStop> getOutboundStops() {
        return outboundStops;
    }

    public List<RouteStop> getReturnStops(){
        return returnStops;
    }

    public void calcTotalPrice(double entryFee, int noOfVisitors) {
        double outboundPrice = outboundSchedule.getRoute().getPrice();
        double returnPrice = returnSchedule.getRoute().getPrice();
        totalPrice = (outboundPrice + returnPrice + entryFee) * noOfVisitors;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
