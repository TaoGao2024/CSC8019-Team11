package com.team11.castleproj.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="StopInfo")
public class StopInfo {
    @Id
    private int stopId;
    private String name;

    @Column(nullable = true)
    private String instructions; // instructions to walk to castle from stop
}