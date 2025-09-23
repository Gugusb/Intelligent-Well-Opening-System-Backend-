package com.gugusb.hwics.smartmonitor2.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

public class ProductionSnapshot {
    private LocalDateTime timestamp; // 快照时间
    private double gasRate;          // 日产气量 (m³/d)
    private double liquidRate;       // 日产液量 (m³/d)
    private double wellheadPressure; // 井口油压 (MPa)
    private double casingPressure;   // 井口套压 (MPa)

    private boolean[] unitStatus;    // 1#~6#机组状态，true=运行，false=停止

    // 构造方法
    public ProductionSnapshot(LocalDateTime timestamp,
                              double gasRate,
                              double liquidRate,
                              double wellheadPressure,
                              double casingPressure,
                              boolean[] unitStatus) {
        this.timestamp = timestamp;
        this.gasRate = gasRate;
        this.liquidRate = liquidRate;
        this.wellheadPressure = wellheadPressure;
        this.casingPressure = casingPressure;
        this.unitStatus = unitStatus;
    }

    @Override
    public String toString() {
        return "ProductionSnapshot{" +
                "timestamp=" + timestamp +
                ", gasRate=" + gasRate +
                ", liquidRate=" + liquidRate +
                ", wellheadPressure=" + wellheadPressure +
                ", casingPressure=" + casingPressure +
                ", unitStatus=" + Arrays.toString(unitStatus) +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getGasRate() {
        return gasRate;
    }

    public void setGasRate(double gasRate) {
        this.gasRate = gasRate;
    }

    public double getLiquidRate() {
        return liquidRate;
    }

    public void setLiquidRate(double liquidRate) {
        this.liquidRate = liquidRate;
    }

    public double getWellheadPressure() {
        return wellheadPressure;
    }

    public void setWellheadPressure(double wellheadPressure) {
        this.wellheadPressure = wellheadPressure;
    }

    public double getCasingPressure() {
        return casingPressure;
    }

    public void setCasingPressure(double casingPressure) {
        this.casingPressure = casingPressure;
    }

    public boolean[] getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(boolean[] unitStatus) {
        this.unitStatus = unitStatus;
    }
}
