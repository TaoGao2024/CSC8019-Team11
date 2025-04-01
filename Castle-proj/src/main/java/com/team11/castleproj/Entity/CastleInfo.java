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
