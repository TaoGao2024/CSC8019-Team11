package com.team11.castleproj.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="BusStops")
public class BusStops {
    @Id
    private int id;
    private String stopName;
    private String location;


}
