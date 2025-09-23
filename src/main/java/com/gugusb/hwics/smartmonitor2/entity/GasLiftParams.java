package com.gugusb.hwics.smartmonitor2.entity;

import java.util.HashMap;
import java.util.Map;

public class GasLiftParams {
    private double gasInjectionVolume; // 注气量 (m³)
    private double gasInjectionRate;   // 注气速率 (m³/min)
    private double gasInjectionTime;   // 注气时间 (min)

    // 构造方法
    public GasLiftParams(double gasInjectionVolume, double gasInjectionRate, double gasInjectionTime) {
        this.gasInjectionVolume = gasInjectionVolume;
        this.gasInjectionRate = gasInjectionRate;
        this.gasInjectionTime = gasInjectionTime;
    }

    public Map<String, Object> transToMap(){
        Map<String, Object> foamingDetails = new HashMap<>();
        foamingDetails.put("gasInjectionVolume", gasInjectionVolume);
        foamingDetails.put("gasInjectionRate", gasInjectionRate);
        foamingDetails.put("gasInjectionTime", gasInjectionTime);
        return foamingDetails;
    }

    @Override
    public String toString() {
        return "GasLiftParams{" +
                "gasInjectionVolume=" + gasInjectionVolume +
                ", gasInjectionRate=" + gasInjectionRate +
                ", gasInjectionTime=" + gasInjectionTime +
                '}';
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
