package com.gugusb.hwics.pojo;

public class ProductionParameter {
    private Integer gasProduction;
    private Integer waterProduction;
    private Integer oilPressure;
    private Integer casingPressure;

    public ProductionParameter() {
        gasProduction = 0;
        waterProduction = 0;
        oilPressure = 0;
        casingPressure = 0;
    }

    @Override
    public String toString() {
        return "ProductionParameter{" +
                "gasProduction=" + gasProduction +
                ", waterProduction=" + waterProduction +
                ", oilPressure=" + oilPressure +
                ", casingPressure=" + casingPressure +
                '}';
    }

    public Integer getPressureDifference() {
        return casingPressure - oilPressure;
    }

    public Integer getGasProduction() {
        return gasProduction;
    }

    public void setGasProduction(Integer gasProduction) {
        this.gasProduction = gasProduction;
    }

    public Integer getWaterProduction() {
        return waterProduction;
    }

    public void setWaterProduction(Integer waterProduction) {
        this.waterProduction = waterProduction;
    }

    public Integer getOilPressure() {
        return oilPressure;
    }

    public void setOilPressure(Integer oilPressure) {
        this.oilPressure = oilPressure;
    }

    public Integer getCasingPressure() {
        return casingPressure;
    }

    public void setCasingPressure(Integer casingPressure) {
        this.casingPressure = casingPressure;
    }
}
