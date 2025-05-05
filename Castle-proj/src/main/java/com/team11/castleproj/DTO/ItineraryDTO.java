package com.team11.castleproj.DTO;

import com.team11.castleproj.Entity.CastleInfo;

public class ItineraryDTO {
    private RoundTripDTO roundTripDTO;
    private CastleInfo castleInfo;
    private int numberOfVisitors;
    public ItineraryDTO(RoundTripDTO roundTripDTO, CastleInfo castleInfo, int numberOfVisitors) {
        this.roundTripDTO = roundTripDTO;
        this.castleInfo = castleInfo;
        this.numberOfVisitors = numberOfVisitors;
    }

    public RoundTripDTO getRoundTripDTO() {
        return roundTripDTO;
    }

    public CastleInfo getCastleInfo() {
        return castleInfo;
    }

    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public double getTotalPrice(){
        return roundTripDTO.getTotalPrice();
    }
}
