/*
 * Creation Date: Mar 29, 2025
 * Class for StopInfo entity's schema,
 * including its name and instructions
 *
 * @author Samuel Leung
 * @version 1.0
 *
 * Additional info: instructions indicates how to walk to castle
 * from a given stop
 *
 */
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
    private String instructions;
}