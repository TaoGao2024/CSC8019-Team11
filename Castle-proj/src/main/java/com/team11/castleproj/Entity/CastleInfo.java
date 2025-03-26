package com.team11.castleproj.Entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.Data;

@Data
public class CastleInfo {
    private int castleId;
    private String castleName;
    private String castleLocation;
    private double entranceFee;

}
