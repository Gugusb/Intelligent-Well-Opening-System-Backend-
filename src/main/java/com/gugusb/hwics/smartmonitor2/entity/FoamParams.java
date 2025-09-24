package com.gugusb.hwics.smartmonitor2.entity;

import jakarta.persistence.Embeddable;

import java.util.HashMap;
import java.util.Map;

@Embeddable
public class FoamParams {
    private double liquidVolume;   // 积液量 (m³)
    private double injectionVolume; // 加注量 (m³)
    private double injectionRate;   // 加注速率 (m³/min)
    private double injectionTime;   // 加注时间 (s)

    // 启动条件
    private double startPressureDiff;

    // 默认参数
    private double injectionRateDef;   // 加注速率 (m³/min)
    private double injectionTimeDef;   // 加注时间 (s)

    // 构造方法
    public FoamParams(double liquidVolume, double injectionVolume, double injectionRate, double injectionTime
            , double startPressureDiff, double injectionRateDef, double injectionTimeDef) {
        this.liquidVolume = liquidVolume;
        this.injectionVolume = injectionVolume;
        this.injectionRate = injectionRate;
        this.injectionTime = injectionTime;
        this.startPressureDiff = startPressureDiff;
        this.injectionRateDef = injectionRateDef;
        this.injectionTimeDef = injectionTimeDef;
    }

    public FoamParams(){

    }

    public Map<String, Object> transToMap(){
        Map<String, Object> foamingDetails = new HashMap<>();
        foamingDetails.put("liquidVolume", liquidVolume);
        foamingDetails.put("injectionVolume", injectionVolume);
        foamingDetails.put("injectionRate", injectionRate);
        foamingDetails.put("injectionTime", injectionTime);
        foamingDetails.put("startPressureDiff", startPressureDiff);
        foamingDetails.put("injectionRateDef", injectionRateDef);
        foamingDetails.put("injectionTimeDef", injectionTimeDef);
        return foamingDetails;
    }

    @Override
    public String toString() {
        return "FoamParams{" +
                "liquidVolume=" + liquidVolume +
                ", injectionVolume=" + injectionVolume +
                ", injectionRate=" + injectionRate +
                ", injectionTime=" + injectionTime +
                ", startPressureDiff=" + startPressureDiff +
                ", injectionRateDef=" + injectionRateDef +
                ", injectionTimeDef=" + injectionTimeDef +
                '}';
    }

    public double getStartPressureDiff() {
        return startPressureDiff;
    }

    public void setStartPressureDiff(double startPressureDiff) {
        this.startPressureDiff = startPressureDiff;
    }

    public double getInjectionRateDef() {
        return injectionRateDef;
    }

    public void setInjectionRateDef(double injectionRateDef) {
        this.injectionRateDef = injectionRateDef;
    }

    public double getInjectionTimeDef() {
        return injectionTimeDef;
    }

    public void setInjectionTimeDef(double injectionTimeDef) {
        this.injectionTimeDef = injectionTimeDef;
    }

    public double getLiquidVolume() {
        return liquidVolume;
    }

    public void setLiquidVolume(double liquidVolume) {
        this.liquidVolume = liquidVolume;
    }

    public double getInjectionVolume() {
        return injectionVolume;
    }

    public void setInjectionVolume(double injectionVolume) {
        this.injectionVolume = injectionVolume;
    }

    public double getInjectionRate() {
        return injectionRate;
    }

    public void setInjectionRate(double injectionRate) {
        this.injectionRate = injectionRate;
    }

    public double getInjectionTime() {
        return injectionTime;
    }

    public void setInjectionTime(double injectionTime) {
        this.injectionTime = injectionTime;
    }
}
