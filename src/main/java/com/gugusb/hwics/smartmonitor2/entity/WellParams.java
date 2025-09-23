package com.gugusb.hwics.smartmonitor2.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class WellParams {
    private double verticalDepth;   // 气井垂深 (m)
    private double horizontalLength; // 水平段长度 (m)
    private double tubingDiameter;  // 油管管径 (m)
    private double casingDiameter;  // 套管管径 (m)

    // 构造方法
    public WellParams(double verticalDepth, double horizontalLength, double tubingDiameter, double casingDiameter) {
        this.verticalDepth = verticalDepth;
        this.horizontalLength = horizontalLength;
        this.tubingDiameter = tubingDiameter;
        this.casingDiameter = casingDiameter;
    }

    protected WellParams() {
    }

    @Override
    public String toString() {
        return "WellParams{" +
                "verticalDepth=" + verticalDepth +
                ", horizontalLength=" + horizontalLength +
                ", tubingDiameter=" + tubingDiameter +
                ", casingDiameter=" + casingDiameter +
                '}';
    }

    public double getVerticalDepth() {
        return verticalDepth;
    }

    public void setVerticalDepth(double verticalDepth) {
        this.verticalDepth = verticalDepth;
    }

    public double getHorizontalLength() {
        return horizontalLength;
    }

    public void setHorizontalLength(double horizontalLength) {
        this.horizontalLength = horizontalLength;
    }

    public double getTubingDiameter() {
        return tubingDiameter;
    }

    public void setTubingDiameter(double tubingDiameter) {
        this.tubingDiameter = tubingDiameter;
    }

    public double getCasingDiameter() {
        return casingDiameter;
    }

    public void setCasingDiameter(double casingDiameter) {
        this.casingDiameter = casingDiameter;
    }
}
