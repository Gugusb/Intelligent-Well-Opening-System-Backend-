package com.gugusb.hwics.pojo;

public class ProductionParameter {
    private Float gasProduction;
    private Float waterProduction;
    private Float oilPressure;
    private Float casingPressure;

    private Float gasAvgProduction;
    private Float waterAvgProduction;
    private Float oilAvgPressure;
    private Float casingAvgPressure;
    private Integer avgTime;

    public ProductionParameter() {
        gasProduction = 0f;
        waterProduction = 0f;
        oilPressure = 0f;
        casingPressure = 0f;

        gasAvgProduction = 0f;
        waterAvgProduction = 0f;
        oilAvgPressure = 0f;
        casingAvgPressure = 0f;
        avgTime = 1;
    }

    @Override
    public String toString() {
        return "ProductionParameter{" +
                "gasProduction=" + gasProduction +
                ", waterProduction=" + waterProduction +
                ", oilPressure=" + oilPressure +
                ", casingPressure=" + casingPressure +
                ", gasAvgProduction=" + gasAvgProduction +
                ", waterAvgProduction=" + waterAvgProduction +
                ", oilAvgPressure=" + oilAvgPressure +
                ", casingAvgPressure=" + casingAvgPressure +
                ", avgTime=" + avgTime +
                '}';
    }

    public Float getGasProduction() {
        return gasProduction;
    }

    public void setGasProduction(Float gasProduction) {
        this.gasProduction = gasProduction;
    }

    public Float getWaterProduction() {
        return waterProduction;
    }

    public void setWaterProduction(Float waterProduction) {
        this.waterProduction = waterProduction;
    }

    public Float getOilPressure() {
        return oilPressure;
    }

    public void setOilPressure(Float oilPressure) {
        this.oilPressure = oilPressure;
    }

    public Float getCasingPressure() {
        return casingPressure;
    }

    public void setCasingPressure(Float casingPressure) {
        this.casingPressure = casingPressure;
    }

    public Float getGasAvgProduction() {
        return gasAvgProduction;
    }

    public void setGasAvgProduction(Float gasAvgProduction) {
        this.gasAvgProduction = gasAvgProduction;
    }

    public Float getWaterAvgProduction() {
        return waterAvgProduction;
    }

    public void setWaterAvgProduction(Float waterAvgProduction) {
        this.waterAvgProduction = waterAvgProduction;
    }

    public Float getOilAvgPressure() {
        return oilAvgPressure;
    }

    public void setOilAvgPressure(Float oilAvgPressure) {
        this.oilAvgPressure = oilAvgPressure;
    }

    public Float getCasingAvgPressure() {
        return casingAvgPressure;
    }

    public void setCasingAvgPressure(Float casingAvgPressure) {
        this.casingAvgPressure = casingAvgPressure;
    }

    public Integer getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Integer avgTime) {
        this.avgTime = avgTime;
    }
}
