package com.gugusb.hwics.smartmonitor2.entity;

import jakarta.persistence.Embeddable;

import java.util.HashMap;
import java.util.Map;

@Embeddable
public class PumpParams {
    private double machineCount;
    private double machinePower;

    // 启动条件
    private double startPressureDiff;

    // 默认参数
    private double machineCountDef;
    private double machinePowerDef;

    // 构造方法
    public PumpParams(double machineCount, double machinePower, double startPressureDiff, double machineCountDef, double machinePowerDef) {
        this.machineCount = machineCount;
        this.machinePower = machinePower;
        this.startPressureDiff = startPressureDiff;
        this.machineCountDef = machineCountDef;
        this.machinePowerDef = machinePowerDef;
    }

    public PumpParams(){

    }

    public Map<String, Object> transToMap(){
        Map<String, Object> foamingDetails = new HashMap<>();
        foamingDetails.put("machineCount", machineCount);
        foamingDetails.put("machinePower", machinePower);
        foamingDetails.put("startPressureDiff", startPressureDiff);
        foamingDetails.put("machineCountDef", machineCountDef);
        foamingDetails.put("machinePowerDef", machinePowerDef);
        return foamingDetails;
    }

    @Override
    public String toString() {
        return "PumpParams{" +
                "machineCount=" + machineCount +
                ", machinePower=" + machinePower +
                ", startPressureDiff=" + startPressureDiff +
                ", machineCountDef=" + machineCountDef +
                ", machinePowerDef=" + machinePowerDef +
                '}';
    }

    public double getMachineCount() {
        return machineCount;
    }

    public void setMachineCount(double machineCount) {
        this.machineCount = machineCount;
    }

    public double getMachinePower() {
        return machinePower;
    }

    public void setMachinePower(double machinePower) {
        this.machinePower = machinePower;
    }

    public double getStartPressureDiff() {
        return startPressureDiff;
    }

    public void setStartPressureDiff(double startPressureDiff) {
        this.startPressureDiff = startPressureDiff;
    }

    public double getMachineCountDef() {
        return machineCountDef;
    }

    public void setMachineCountDef(double machineCountDef) {
        this.machineCountDef = machineCountDef;
    }

    public double getMachinePowerDef() {
        return machinePowerDef;
    }

    public void setMachinePowerDef(double machinePowerDef) {
        this.machinePowerDef = machinePowerDef;
    }
}
