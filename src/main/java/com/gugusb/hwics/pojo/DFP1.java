package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
@Table(name = "tb_dfp1")
public class DFP1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Integer dataId;          // ID，键值，自增

    @Column(name = "update_time")
    private String updateTime;         // 存储时间

    @Column(name = "current_mode")
    private Integer currentMode;      // 当前模式，int

    @Column(name = "left_elec_box_status")
    private Boolean leftElecBoxStatus; // 左电气箱状态，布尔

    @Column(name = "right_elec_box_status")
    private Boolean rightElecBoxStatus; // 右电气箱状态，布尔

    // 1#-6#机组运行状态 (布尔)
    @Column(name = "unit1_run_status")
    private Boolean unit1RunStatus;
    @Column(name = "unit2_run_status")
    private Boolean unit2RunStatus;
    @Column(name = "unit3_run_status")
    private Boolean unit3RunStatus;
    @Column(name = "unit4_run_status")
    private Boolean unit4RunStatus;
    @Column(name = "unit5_run_status")
    private Boolean unit5RunStatus;
    @Column(name = "unit6_run_status")
    private Boolean unit6RunStatus;

    // 1#-5#启泵次数 (int)
    @Column(name = "pump1_start_count")
    private Integer pump1StartCount;
    @Column(name = "pump2_start_count")
    private Integer pump2StartCount;
    @Column(name = "pump3_start_count")
    private Integer pump3StartCount;
    @Column(name = "pump4_start_count")
    private Integer pump4StartCount;
    @Column(name = "pump5_start_count")
    private Integer pump5StartCount;

    // 气举启泵次数 (int)
    @Column(name = "gaslift_pump1_start_count")
    private Integer gasliftPump1StartCount;  // 1#气举启泵次数
    @Column(name = "gaslift_pump2_start_count")
    private Integer gasliftPump2StartCount;  // 2#气举启泵次数

    // 流量参数 (int)
    @Column(name = "total_instant_flow123")
    private Integer totalInstantFlow123;      // 1#2#3#总瞬时流量
    @Column(name = "total_accumulated_flow123")
    private Integer totalAccumulatedFlow123;  // 1#2#3#总累计流量
    @Column(name = "total_instant_flow45")
    private Integer totalInstantFlow45;       // 4#5#总瞬时流量
    @Column(name = "total_accumulated_flow45")
    private Integer totalAccumulatedFlow45;   // 4#5#总累计流量
    @Column(name = "gaslift_instant_flow")
    private Integer gasliftInstantFlow;       // 气举瞬时流量
    @Column(name = "gaslift_accumulated_flow")
    private Integer gasliftAccumulatedFlow;   // 气举累计流量

    // 压力参数 (int)
    @Column(name = "front_pressure1")
    private Integer frontPressure1;          // 1#前端压力
    @Column(name = "front_pressure2")
    private Integer frontPressure2;          // 2#前端压力
    @Column(name = "front_pressure3")
    private Integer frontPressure3;          // 3#前端压力
    @Column(name = "front_pressure45")
    private Integer frontPressure45;         // 4#5#前端压力
    @Column(name = "gaslift_front_pressure5")
    private Integer gasliftFrontPressure5;    // 气举5#前端压力
    @Column(name = "gaslift_front_pressure6")
    private Integer gasliftFrontPressure6;    // 气举6#前端压力
    @Column(name = "back_pressure123")
    private Integer backPressure123;          // 1#2#3#后端压力
    @Column(name = "back_pressure45")
    private Integer backPressure45;           // 4#5#后端压力
    @Column(name = "gaslift_back_pressure6")
    private Integer gasliftBackPressure6;     // 气举6#后端压力

    // 流量计参数 (浮点数)
    @Column(name = "flowmeter_instant_flow")
    private Double flowmeterInstantFlow;       // 流量计瞬时流量
    @Column(name = "flowmeter_accumulated_flow")
    private Double flowmeterAccumulatedFlow;   // 流量计累计流量
    @Column(name = "flowmeter_medium_temp")
    private Double flowmeterMediumTemp;        // 流量计介质温度
    @Column(name = "flowmeter_static_pressure")
    private Double flowmeterStaticPressure;     // 流量计静压值
    @Column(name = "flowmeter_diff_pressure")
    private Double flowmeterDiffPressure;      // 流量计压差值

    @Override
    public String toString() {
        return "DFP1{" +
                "dataId=" + dataId +
                ", updateTime=" + updateTime +
                ", currentMode=" + currentMode +
                ", leftElecBoxStatus=" + leftElecBoxStatus +
                ", rightElecBoxStatus=" + rightElecBoxStatus +
                ", unit1RunStatus=" + unit1RunStatus +
                ", unit2RunStatus=" + unit2RunStatus +
                ", unit3RunStatus=" + unit3RunStatus +
                ", unit4RunStatus=" + unit4RunStatus +
                ", unit5RunStatus=" + unit5RunStatus +
                ", unit6RunStatus=" + unit6RunStatus +
                ", pump1StartCount=" + pump1StartCount +
                ", pump2StartCount=" + pump2StartCount +
                ", pump3StartCount=" + pump3StartCount +
                ", pump4StartCount=" + pump4StartCount +
                ", pump5StartCount=" + pump5StartCount +
                ", gasliftPump1StartCount=" + gasliftPump1StartCount +
                ", gasliftPump2StartCount=" + gasliftPump2StartCount +
                ", totalInstantFlow123=" + totalInstantFlow123 +
                ", totalAccumulatedFlow123=" + totalAccumulatedFlow123 +
                ", totalInstantFlow45=" + totalInstantFlow45 +
                ", totalAccumulatedFlow45=" + totalAccumulatedFlow45 +
                ", gasliftInstantFlow=" + gasliftInstantFlow +
                ", gasliftAccumulatedFlow=" + gasliftAccumulatedFlow +
                ", frontPressure1=" + frontPressure1 +
                ", frontPressure2=" + frontPressure2 +
                ", frontPressure3=" + frontPressure3 +
                ", frontPressure45=" + frontPressure45 +
                ", gasliftFrontPressure5=" + gasliftFrontPressure5 +
                ", gasliftFrontPressure6=" + gasliftFrontPressure6 +
                ", backPressure123=" + backPressure123 +
                ", backPressure45=" + backPressure45 +
                ", gasliftBackPressure6=" + gasliftBackPressure6 +
                ", flowmeterInstantFlow=" + flowmeterInstantFlow +
                ", flowmeterAccumulatedFlow=" + flowmeterAccumulatedFlow +
                ", flowmeterMediumTemp=" + flowmeterMediumTemp +
                ", flowmeterStaticPressure=" + flowmeterStaticPressure +
                ", flowmeterDiffPressure=" + flowmeterDiffPressure +
                '}';
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = String.valueOf(updateTime);
    }

    public Integer getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(Integer currentMode) {
        this.currentMode = currentMode;
    }

    public Boolean getLeftElecBoxStatus() {
        return leftElecBoxStatus;
    }

    public void setLeftElecBoxStatus(Boolean leftElecBoxStatus) {
        this.leftElecBoxStatus = leftElecBoxStatus;
    }

    public Boolean getRightElecBoxStatus() {
        return rightElecBoxStatus;
    }

    public void setRightElecBoxStatus(Boolean rightElecBoxStatus) {
        this.rightElecBoxStatus = rightElecBoxStatus;
    }

    public Boolean getUnit1RunStatus() {
        return unit1RunStatus;
    }

    public void setUnit1RunStatus(Boolean unit1RunStatus) {
        this.unit1RunStatus = unit1RunStatus;
    }

    public Boolean getUnit2RunStatus() {
        return unit2RunStatus;
    }

    public void setUnit2RunStatus(Boolean unit2RunStatus) {
        this.unit2RunStatus = unit2RunStatus;
    }

    public Boolean getUnit3RunStatus() {
        return unit3RunStatus;
    }

    public void setUnit3RunStatus(Boolean unit3RunStatus) {
        this.unit3RunStatus = unit3RunStatus;
    }

    public Boolean getUnit4RunStatus() {
        return unit4RunStatus;
    }

    public void setUnit4RunStatus(Boolean unit4RunStatus) {
        this.unit4RunStatus = unit4RunStatus;
    }

    public Boolean getUnit5RunStatus() {
        return unit5RunStatus;
    }

    public void setUnit5RunStatus(Boolean unit5RunStatus) {
        this.unit5RunStatus = unit5RunStatus;
    }

    public Boolean getUnit6RunStatus() {
        return unit6RunStatus;
    }

    public void setUnit6RunStatus(Boolean unit6RunStatus) {
        this.unit6RunStatus = unit6RunStatus;
    }

    public Integer getPump1StartCount() {
        return pump1StartCount;
    }

    public void setPump1StartCount(Integer pump1StartCount) {
        this.pump1StartCount = pump1StartCount;
    }

    public Integer getPump2StartCount() {
        return pump2StartCount;
    }

    public void setPump2StartCount(Integer pump2StartCount) {
        this.pump2StartCount = pump2StartCount;
    }

    public Integer getPump3StartCount() {
        return pump3StartCount;
    }

    public void setPump3StartCount(Integer pump3StartCount) {
        this.pump3StartCount = pump3StartCount;
    }

    public Integer getPump4StartCount() {
        return pump4StartCount;
    }

    public void setPump4StartCount(Integer pump4StartCount) {
        this.pump4StartCount = pump4StartCount;
    }

    public Integer getPump5StartCount() {
        return pump5StartCount;
    }

    public void setPump5StartCount(Integer pump5StartCount) {
        this.pump5StartCount = pump5StartCount;
    }

    public Integer getGasliftPump1StartCount() {
        return gasliftPump1StartCount;
    }

    public void setGasliftPump1StartCount(Integer gasliftPump1StartCount) {
        this.gasliftPump1StartCount = gasliftPump1StartCount;
    }

    public Integer getGasliftPump2StartCount() {
        return gasliftPump2StartCount;
    }

    public void setGasliftPump2StartCount(Integer gasliftPump2StartCount) {
        this.gasliftPump2StartCount = gasliftPump2StartCount;
    }

    public Integer getTotalInstantFlow123() {
        return totalInstantFlow123;
    }

    public void setTotalInstantFlow123(Integer totalInstantFlow123) {
        this.totalInstantFlow123 = totalInstantFlow123;
    }

    public Integer getTotalAccumulatedFlow123() {
        return totalAccumulatedFlow123;
    }

    public void setTotalAccumulatedFlow123(Integer totalAccumulatedFlow123) {
        this.totalAccumulatedFlow123 = totalAccumulatedFlow123;
    }

    public Integer getTotalInstantFlow45() {
        return totalInstantFlow45;
    }

    public void setTotalInstantFlow45(Integer totalInstantFlow45) {
        this.totalInstantFlow45 = totalInstantFlow45;
    }

    public Integer getTotalAccumulatedFlow45() {
        return totalAccumulatedFlow45;
    }

    public void setTotalAccumulatedFlow45(Integer totalAccumulatedFlow45) {
        this.totalAccumulatedFlow45 = totalAccumulatedFlow45;
    }

    public Integer getGasliftInstantFlow() {
        return gasliftInstantFlow;
    }

    public void setGasliftInstantFlow(Integer gasliftInstantFlow) {
        this.gasliftInstantFlow = gasliftInstantFlow;
    }

    public Integer getGasliftAccumulatedFlow() {
        return gasliftAccumulatedFlow;
    }

    public void setGasliftAccumulatedFlow(Integer gasliftAccumulatedFlow) {
        this.gasliftAccumulatedFlow = gasliftAccumulatedFlow;
    }

    public Integer getFrontPressure1() {
        return frontPressure1;
    }

    public void setFrontPressure1(Integer frontPressure1) {
        this.frontPressure1 = frontPressure1;
    }

    public Integer getFrontPressure2() {
        return frontPressure2;
    }

    public void setFrontPressure2(Integer frontPressure2) {
        this.frontPressure2 = frontPressure2;
    }

    public Integer getFrontPressure3() {
        return frontPressure3;
    }

    public void setFrontPressure3(Integer frontPressure3) {
        this.frontPressure3 = frontPressure3;
    }

    public Integer getFrontPressure45() {
        return frontPressure45;
    }

    public void setFrontPressure45(Integer frontPressure45) {
        this.frontPressure45 = frontPressure45;
    }

    public Integer getGasliftFrontPressure5() {
        return gasliftFrontPressure5;
    }

    public void setGasliftFrontPressure5(Integer gasliftFrontPressure5) {
        this.gasliftFrontPressure5 = gasliftFrontPressure5;
    }

    public Integer getGasliftFrontPressure6() {
        return gasliftFrontPressure6;
    }

    public void setGasliftFrontPressure6(Integer gasliftFrontPressure6) {
        this.gasliftFrontPressure6 = gasliftFrontPressure6;
    }

    public Integer getBackPressure123() {
        return backPressure123;
    }

    public void setBackPressure123(Integer backPressure123) {
        this.backPressure123 = backPressure123;
    }

    public Integer getBackPressure45() {
        return backPressure45;
    }

    public void setBackPressure45(Integer backPressure45) {
        this.backPressure45 = backPressure45;
    }

    public Integer getGasliftBackPressure6() {
        return gasliftBackPressure6;
    }

    public void setGasliftBackPressure6(Integer gasliftBackPressure6) {
        this.gasliftBackPressure6 = gasliftBackPressure6;
    }

    public Double getFlowmeterInstantFlow() {
        return flowmeterInstantFlow;
    }

    public void setFlowmeterInstantFlow(Double flowmeterInstantFlow) {
        this.flowmeterInstantFlow = flowmeterInstantFlow;
    }

    public Double getFlowmeterAccumulatedFlow() {
        return flowmeterAccumulatedFlow;
    }

    public void setFlowmeterAccumulatedFlow(Double flowmeterAccumulatedFlow) {
        this.flowmeterAccumulatedFlow = flowmeterAccumulatedFlow;
    }

    public Double getFlowmeterMediumTemp() {
        return flowmeterMediumTemp;
    }

    public void setFlowmeterMediumTemp(Double flowmeterMediumTemp) {
        this.flowmeterMediumTemp = flowmeterMediumTemp;
    }

    public Double getFlowmeterStaticPressure() {
        return flowmeterStaticPressure;
    }

    public void setFlowmeterStaticPressure(Double flowmeterStaticPressure) {
        this.flowmeterStaticPressure = flowmeterStaticPressure;
    }

    public Double getFlowmeterDiffPressure() {
        return flowmeterDiffPressure;
    }

    public void setFlowmeterDiffPressure(Double flowmeterDiffPressure) {
        this.flowmeterDiffPressure = flowmeterDiffPressure;
    }

    // 生成随机对象的方法
    public static DFP1 createRandomInstance() {
        Random random = new Random();
        DFP1 randomObj = new DFP1();

        // 整型范围 [0, 100]
        randomObj.setCurrentMode(random.nextInt(101));

        // 布尔值随机
        randomObj.setLeftElecBoxStatus(random.nextBoolean());
        randomObj.setRightElecBoxStatus(random.nextBoolean());

        // 机组运行状态随机
        randomObj.setUnit1RunStatus(random.nextBoolean());
        randomObj.setUnit2RunStatus(random.nextBoolean());
        randomObj.setUnit3RunStatus(random.nextBoolean());
        randomObj.setUnit4RunStatus(random.nextBoolean());
        randomObj.setUnit5RunStatus(random.nextBoolean());
        randomObj.setUnit6RunStatus(random.nextBoolean());

        // 启泵次数范围 [0, 1000]
        randomObj.setPump1StartCount(random.nextInt(1001));
        randomObj.setPump2StartCount(random.nextInt(1001));
        randomObj.setPump3StartCount(random.nextInt(1001));
        randomObj.setPump4StartCount(random.nextInt(1001));
        randomObj.setPump5StartCount(random.nextInt(1001));

        // 气举启泵次数范围 [0, 500]
        randomObj.setGasliftPump1StartCount(random.nextInt(501));
        randomObj.setGasliftPump2StartCount(random.nextInt(501));

        // 流量范围 [0, 10000]
        randomObj.setTotalInstantFlow123(random.nextInt(10001));
        randomObj.setTotalAccumulatedFlow123(random.nextInt(10001));
        randomObj.setTotalInstantFlow45(random.nextInt(10001));
        randomObj.setTotalAccumulatedFlow45(random.nextInt(10001));
        randomObj.setGasliftInstantFlow(random.nextInt(10001));
        randomObj.setGasliftAccumulatedFlow(random.nextInt(10001));

        // 压力范围 [0, 2000]
        randomObj.setFrontPressure1(random.nextInt(2001));
        randomObj.setFrontPressure2(random.nextInt(2001));
        randomObj.setFrontPressure3(random.nextInt(2001));
        randomObj.setFrontPressure45(random.nextInt(2001));
        randomObj.setGasliftFrontPressure5(random.nextInt(2001));
        randomObj.setGasliftFrontPressure6(random.nextInt(2001));
        randomObj.setBackPressure123(random.nextInt(2001));
        randomObj.setBackPressure45(random.nextInt(2001));
        randomObj.setGasliftBackPressure6(random.nextInt(2001));

        // 浮点数范围 [0.0, 100.0] 保留两位小数
        randomObj.setFlowmeterInstantFlow(roundToTwoDecimals(random.nextDouble() * 100));
        randomObj.setFlowmeterAccumulatedFlow(roundToTwoDecimals(random.nextDouble() * 100));
        randomObj.setFlowmeterMediumTemp(roundToTwoDecimals(random.nextDouble() * 100));
        randomObj.setFlowmeterStaticPressure(roundToTwoDecimals(random.nextDouble() * 100));
        randomObj.setFlowmeterDiffPressure(roundToTwoDecimals(random.nextDouble() * 100));

        return randomObj;
    }

    // 私有方法：四舍五入保留两位小数
    private static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}