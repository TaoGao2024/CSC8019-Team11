package com.team11.castleproj.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ScheduleInfo")
public class ScheduleInfo {
    @Id
    private int scheduleId;
    private int routeId;
    private String departTime;
    private String arriveTime;
}
