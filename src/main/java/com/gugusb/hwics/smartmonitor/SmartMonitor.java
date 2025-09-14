package com.gugusb.hwics.smartmonitor;

import com.gugusb.hwics.pojo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmartMonitor{
    private Boolean SMFState;   //抽吸混输
    private Boolean PDState;    //泡排
    private Boolean GLState;    //气举

    private Float gasProduction;
    private Float waterProduction;
    private Float oilPressure;
    private Float casingPressure;

    private List<ProductionLog> productionLogs;

    public void initSystem(ProductionParameter parameter) {
        SMFState = Boolean.FALSE;
        PDState = Boolean.FALSE;
        GLState = Boolean.FALSE;

        updateProductionData(parameter);

        productionLogs = new ArrayList<ProductionLog>();

        spawnLogQuickly("系统启动！");
    }

    // 生产并打印日志
    public ProductionLog spawnLog(){
        ProductionLog productionLog = new ProductionLog();
        return productionLog;
    }

    public ProductionLog spawnLogQuickly(String logDescription){
        ProductionLog productionLog = new ProductionLog();
        productionLog.setDiscription(logDescription);
        return productionLog;
    }

    // 更新生产参数
    public void updateProductionData(ProductionParameter parameter){
        this.gasProduction = parameter.getGasProduction();
        this.waterProduction = parameter.getWaterProduction();
        this.oilPressure = parameter.getOilPressure();
        this.casingPressure = parameter.getCasingPressure();
    }

    // 持久方法
    public void run(){
        // 阶段转换执行方法
    }

    @Override
    public String toString() {
        return "SmartMonitor{" +
                "SMFState=" + SMFState +
                ", PDState=" + PDState +
                ", GLState=" + GLState +
                ", gasProduction=" + gasProduction +
                ", waterProduction=" + waterProduction +
                ", oilPressure=" + oilPressure +
                ", casingPressure=" + casingPressure +
                ", productionLogs=" + productionLogs +
                '}';
    }

    public Boolean getSMFState() {
        return SMFState;
    }

    public void setSMFState(Boolean SMFState) {
        this.SMFState = SMFState;
    }

    public Boolean getPDState() {
        return PDState;
    }

    public void setPDState(Boolean PDState) {
        this.PDState = PDState;
    }

    public Boolean getGLState() {
        return GLState;
    }

    public void setGLState(Boolean GLState) {
        this.GLState = GLState;
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

    public List<ProductionLog> getProductionLogs() {
        return productionLogs;
    }

    public void setProductionLogs(List<ProductionLog> productionLogs) {
        this.productionLogs = productionLogs;
    }
}
