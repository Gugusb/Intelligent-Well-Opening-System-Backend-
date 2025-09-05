package com.gugusb.hwics.smartmonitor;

import com.gugusb.hwics.pojo.FoamDrainageSystem;
import com.gugusb.hwics.pojo.GasLiftSystem;
import com.gugusb.hwics.pojo.ProductionLog;
import com.gugusb.hwics.pojo.SuctionMixedFlowSystem;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

public class SmartMonitor {
    private Boolean SMFState;   //抽吸混输
    private Boolean PDState;    //泡排
    private Boolean GLState;    //气举

    private Integer gasProduction;
    private Integer waterProduction;
    private Integer oilPressure;
    private Integer casingPressure;

    private List<ProductionLog> productionLogs;

    private FoamDrainageSystem foamDrainageSystem;
    private GasLiftSystem gasLiftSystem;
    private SuctionMixedFlowSystem suctionMixedFlowSystem;

    public SmartMonitor(Integer gasProduction, Integer waterProduction, Integer oilPressure, Integer casingPressure) {
        SMFState = Boolean.FALSE;
        PDState = Boolean.FALSE;
        GLState = Boolean.FALSE;

        updateProductionData(gasProduction, waterProduction, oilPressure, casingPressure);

        productionLogs = new ArrayList<ProductionLog>();

        foamDrainageSystem = new FoamDrainageSystem();
        gasLiftSystem = new GasLiftSystem();
        suctionMixedFlowSystem = new SuctionMixedFlowSystem();
    }

    // 系统初始化
    public void initSystem(){
        // 打印系统初始化的log
        spawnLogQuickly("系统启动！");
        // 根据生产参数确定机器的开启情况

        // 打印机器开启log
        spawnLogQuickly("系统启动！");
    }

    //生产并打印日志
    public ProductionLog spawnLog(){
        ProductionLog productionLog = new ProductionLog();
        return productionLog;
    }

    public ProductionLog spawnLogQuickly(String logDescription){
        ProductionLog productionLog = new ProductionLog();
        productionLog.setDiscription(logDescription);
        return productionLog;
    }

    // 更细生产参数
    public void updateProductionData(Integer gasProduction, Integer waterProduction, Integer oilPressure, Integer casingPressure){
        this.gasProduction = gasProduction;
        this.waterProduction = waterProduction;
        this.oilPressure = oilPressure;
        this.casingPressure = casingPressure;
    }

    //持久方法
    public void run(){

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
                ", foamDrainageSystem=" + foamDrainageSystem +
                ", gasLiftSystem=" + gasLiftSystem +
                ", suctionMixedFlowSystem=" + suctionMixedFlowSystem +
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

    public List<ProductionLog> getProductionLogs() {
        return productionLogs;
    }

    public void setProductionLogs(List<ProductionLog> productionLogs) {
        this.productionLogs = productionLogs;
    }

    public FoamDrainageSystem getFoamDrainageSystem() {
        return foamDrainageSystem;
    }

    public void setFoamDrainageSystem(FoamDrainageSystem foamDrainageSystem) {
        this.foamDrainageSystem = foamDrainageSystem;
    }

    public GasLiftSystem getGasLiftSystem() {
        return gasLiftSystem;
    }

    public void setGasLiftSystem(GasLiftSystem gasLiftSystem) {
        this.gasLiftSystem = gasLiftSystem;
    }

    public SuctionMixedFlowSystem getSuctionMixedFlowSystem() {
        return suctionMixedFlowSystem;
    }

    public void setSuctionMixedFlowSystem(SuctionMixedFlowSystem suctionMixedFlowSystem) {
        this.suctionMixedFlowSystem = suctionMixedFlowSystem;
    }
}
