package com.gugusb.hwics.smartmonitor2.entity;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_gas_lift_timer")
public class GasLiftTimerPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Integer dataId;          // ID，键值，自增
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "duration")
    private Duration duration;

    @Override
    public String toString() {
        return "GasLiftTimerPojo{" +
                "dataId=" + dataId +
                ", startTime=" + startTime +
                ", duration=" + duration +
                '}';
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
