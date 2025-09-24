package com.gugusb.hwics.smartmonitor2.entity;

import com.gugusb.hwics.utils.DateSpawner;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tb_system_state")
public class SystemState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Integer dataId;          // ID，键值，自增

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateTime;  // 存储时间

    @Column
    @Embedded
    private WellParams wellParams;

    @Column(name = "first_gas_lift")
    private boolean firstGasLiftStarted;

    @Column(name = "running")
    private boolean systemRunning;

    @Column(name = "stage")
    private Integer systemStage;

    @Column(name = "safe_tag")
    private Integer safeColseTag;

    public void fillAsEntity(){
        this.updateTime = DateSpawner.getLocalTimestamp();
    }

    public void initState(){
        this.firstGasLiftStarted = false;
        this.systemRunning = false;
        this.systemStage = 0;
        this.safeColseTag = 0;
    }

    @Override
    public String toString() {
        return "SystemState{" +
                "dataId=" + dataId +
                ", updateTime=" + updateTime +
                ", wellParams=" + wellParams +
                ", firstGasLiftStarted=" + firstGasLiftStarted +
                ", systemRunning=" + systemRunning +
                ", systemStage=" + systemStage +
                ", safeColseTag=" + safeColseTag +
                '}';
    }

    public Integer getSafeColseTag() {
        return safeColseTag;
    }

    public void setSafeColseTag(Integer safeColseTag) {
        this.safeColseTag = safeColseTag;
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

    public WellParams getWellParams() {
        return wellParams;
    }

    public void setWellParams(WellParams wellParams) {
        this.wellParams = wellParams;
    }

    public boolean isFirstGasLiftStarted() {
        return firstGasLiftStarted;
    }

    public void setFirstGasLiftStarted(boolean firstGasLiftStarted) {
        this.firstGasLiftStarted = firstGasLiftStarted;
    }

    public boolean isSystemRunning() {
        return systemRunning;
    }

    public void setSystemRunning(boolean systemRunning) {
        this.systemRunning = systemRunning;
    }

    public Integer getSystemStage() {
        return systemStage;
    }

    public void setSystemStage(Integer systemStage) {
        this.systemStage = systemStage;
    }
}
