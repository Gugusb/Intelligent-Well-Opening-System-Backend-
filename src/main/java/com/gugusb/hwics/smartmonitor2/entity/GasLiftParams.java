package com.gugusb.hwics.smartmonitor2.entity;

import jakarta.persistence.Embeddable;

import java.util.HashMap;
import java.util.Map;

@Embeddable
public class GasLiftParams {
    private double gasInjectionVolume; // 注气量 (m³)
    private double gasInjectionRate;   // 注气速率 (m³/min)
    private double gasInjectionTime;   // 注气时间 (h)

    // 启动条件
    private double startPressureDiff;

    // 默认参数
    private double gasInjectionRateDef;   // 加注速率 (m³/min)
    private double gasInjectionTimeDef;   // 加注时间 (h)

    // 构造方法
    public GasLiftParams(double gasInjectionVolume, double gasInjectionRate, double gasInjectionTime, double startPressureDiff, double gasInjectionRateDef, double gasInjectionTimeDef) {
        this.gasInjectionVolume = gasInjectionVolume;
        this.gasInjectionRate = gasInjectionRate;
        this.gasInjectionTime = gasInjectionTime;
        this.startPressureDiff = startPressureDiff;
        this.gasInjectionTimeDef = gasInjectionTimeDef;
        this.gasInjectionRateDef = gasInjectionRateDef;
    }

    public GasLiftParams(){

    }

    public Map<String, Object> transToMap(){
        Map<String, Object> foamingDetails = new HashMap<>();
        foamingDetails.put("gasInjectionVolume", gasInjectionVolume);
        foamingDetails.put("gasInjectionRate", gasInjectionRate);
        foamingDetails.put("gasInjectionTime", gasInjectionTime);
        foamingDetails.put("startPressureDiff", startPressureDiff);
        foamingDetails.put("gasInjectionRateDef", gasInjectionRateDef);
        foamingDetails.put("gasInjectionTimeDef", gasInjectionTimeDef);
        return foamingDetails;
    }

    @Override
    public String toString() {
        return "GasLiftParams{" +
                "gasInjectionVolume=" + gasInjectionVolume +
                ", gasInjectionRate=" + gasInjectionRate +
                ", gasInjectionTime=" + gasInjectionTime +
                ", startPressureDiff=" + startPressureDiff +
                ", gasInjectionRateDef=" + gasInjectionRateDef +
                ", gasInjectionTimeDef=" + gasInjectionTimeDef +
                '}';
    }

    public double getStartPressureDiff() {
        return startPressureDiff;
    }

    public void setStartPressureDiff(double startPressureDiff) {
        this.startPressureDiff = startPressureDiff;
    }

    public double getGasInjectionRateDef() {
        return gasInjectionRateDef;
    }

    public void setGasInjectionRateDef(double gasInjectionRateDef) {
        this.gasInjectionRateDef = gasInjectionRateDef;
    }

    public double getGasInjectionTimeDef() {
        return gasInjectionTimeDef;
    }

    public void setGasInjectionTimeDef(double gasInjectionTimeDef) {
        this.gasInjectionTimeDef = gasInjectionTimeDef;
    }

    public double getGasInjectionVolume() {
        return gasInjectionVolume;
    }

    public void setGasInjectionVolume(double gasInjectionVolume) {
        this.gasInjectionVolume = gasInjectionVolume;
    }

    public double getGasInjectionRate() {
        return gasInjectionRate;
    }

    public void setGasInjectionRate(double gasInjectionRate) {
        this.gasInjectionRate = gasInjectionRate;
    }

    public double getGasInjectionTime() {
        return gasInjectionTime;
    }

    public void setGasInjectionTime(double gasInjectionTime) {
        this.gasInjectionTime = gasInjectionTime;
    }
}
