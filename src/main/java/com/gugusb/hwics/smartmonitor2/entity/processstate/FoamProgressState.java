package com.gugusb.hwics.smartmonitor2.entity.processstate;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_foam_state")
public class FoamProgressState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Integer dataId;          // ID，键值，自增

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateTime;  // 存储时间

    @Column(name = "running")
    private boolean running;

    @Column(name = "last_start_time")
    private LocalDateTime lastStartTime;

    @Column(name = "last_duration")
    private Duration lastDuration;

    public void fillAsEntity(){
        this.updateTime = new Timestamp(System.currentTimeMillis());
    }

    public FoamProgressState() {
        this.running = false;
        this.lastDuration = Duration.ZERO;
    }

    /**
     * 标记工艺开始
     */
    public void start() {
        this.running = true;
        this.lastStartTime = LocalDateTime.now();
    }

    public void start(Duration duration) {
        this.running = true;
        this.lastStartTime = LocalDateTime.now();
        this.lastDuration = duration;
    }

    /**
     * 标记工艺结束
     */
    public void stop() {

        this.running = false;
    }

    /**
     * 获取上次运行持续时间（秒）
     */
    public long getLastDurationSeconds() {
        return lastDuration != null ? lastDuration.getSeconds() : 0L;
    }

    // ========== Getter & Setter ==========

    public boolean isRunning() {
        return running;
    }

    @Override
    public String toString() {
        return "FoamProgressState{" +
                "dataId=" + dataId +
                ", updateTime=" + updateTime +
                ", running=" + running +
                ", lastStartTime=" + lastStartTime +
                ", lastDuration=" + lastDuration +
                '}';
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public LocalDateTime getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(LocalDateTime lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    public Duration getLastDuration() {
        return lastDuration;
    }

    public void setLastDuration(Duration lastDuration) {
        this.lastDuration = lastDuration;
    }
}

