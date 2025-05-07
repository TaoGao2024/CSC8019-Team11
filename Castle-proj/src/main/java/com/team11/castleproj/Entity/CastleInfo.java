/*
 * Creation Date: Mar 27, 2025
 * Class for CastleInfo entity's schema,
 * including its name, description, and entry fee
 *
 * @author Tao Gao
 * @version 1.1
 *
 *
 * Modification History:
 * 29/3 Samuel Leung - Renamed columns and added description and entry fee
 *
 */
package com.team11.castleproj.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name="CastleInfo")
public class CastleInfo {
    @Id
    @Column(name = "CASTLEID")
    private String castleId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ENTRYFEE")
    private double entryFee;
    @Column(name = "OPENTIME")
    private LocalTime openTime;
    @Column(name = "ClOSETIME")
    private LocalTime closeTime;
    @Column(name = "WEBSITE")
    private String website;
}
