package com.team11.castle.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="CastleInfo")
public class CastleInfo {
    @Id
    private int castleId;
    private String castleName;
    private String castleLocation;
    private double entranceFee;

}
