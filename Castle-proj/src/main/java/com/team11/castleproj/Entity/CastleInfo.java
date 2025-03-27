package com.team11.castleproj.Entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
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
    private String name;
    private String description;
    private double entryFee;

}
