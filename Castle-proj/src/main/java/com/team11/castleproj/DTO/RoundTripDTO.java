package com.team11.castleproj.DTO;

import com.team11.castleproj.Entity.ScheduleInfo;

public class RoundTripDTO {
    private ScheduleInfo outboundSchedule;
    private ScheduleInfo returnSchedule;
    private double totalPrice;

    public RoundTripDTO(ScheduleInfo outboundSchedule, ScheduleInfo returnSchedule) {
        this.outboundSchedule = outboundSchedule;
        this.returnSchedule = returnSchedule;
        this.totalPrice = 0.0;
    }

    public ScheduleInfo getOutboundSchedule() {
        return outboundSchedule;
    }

    public ScheduleInfo getReturnSchedule() {
        return returnSchedule;
    }

    public void setTotalPrice(double entryFee, int noOfVisitors) {
        double outboundPrice = outboundSchedule.getRoute().getPrice();
        double returnPrice = returnSchedule.getRoute().getPrice();
        totalPrice = (outboundPrice + returnPrice + entryFee) * noOfVisitors;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
