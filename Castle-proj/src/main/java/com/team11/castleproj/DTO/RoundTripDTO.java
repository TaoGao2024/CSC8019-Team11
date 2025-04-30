package com.team11.castleproj.DTO;

import com.team11.castleproj.Entity.ScheduleInfo;

public class RoundTripDTO {
    private ScheduleInfo outboundSchedule;
    private ScheduleInfo returnSchedule;

    public RoundTripDTO(){
    }

    public RoundTripDTO(ScheduleInfo outboundSchedule, ScheduleInfo returnSchedule) {
        this.outboundSchedule = outboundSchedule;
        this.returnSchedule = returnSchedule;
    }

    public ScheduleInfo getOutboundSchedule() {
        return outboundSchedule;
    }
    public void setOutboundSchedule(ScheduleInfo outboundSchedule) {
        this.outboundSchedule = outboundSchedule;
    }

    public ScheduleInfo getReturnSchedule() {
        return returnSchedule;
    }

    public void setReturnSchedule(ScheduleInfo returnSchedule) {
        this.returnSchedule = returnSchedule;
    }

    public double getTotalPrice() {
        double outboundPrice = outboundSchedule.getRoute().getPrice();
        double returnPrice = returnSchedule.getRoute().getPrice();
        return outboundPrice + returnPrice;
    }
}
