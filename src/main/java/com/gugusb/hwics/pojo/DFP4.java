package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;

@Entity
@Table(name = "tb_dfp4")
public class DFP4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dataId;  // 主键ID，自增

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateTime;  // 存储时间

    @Column(name = "dosing_equipment_status")
    private Boolean dosingEquipmentStatus;  // 加药设备状态，布尔值

    @Column(name = "current_mode")
    private Integer currentMode;  // 当前模式，整型

    @Column(name = "dosing_run_status")
    private Boolean dosingRunStatus;  // 加药运行状态，布尔值

    // 1#阀门状态
    @Column(name = "valve1_open_indicator")
    private Boolean valve1OpenIndicator;  // 1#开阀指示，布尔值

    @Column(name = "valve1_open_status")
    private Boolean valve1OpenStatus;     // 1#阀开到位，布尔值

    // 2#阀门状态
    @Column(name = "valve2_open_indicator")
    private Boolean valve2OpenIndicator;  // 2#开阀指示，布尔值

    @Column(name = "valve2_open_status")
    private Boolean valve2OpenStatus;     // 2#阀开到位，布尔值

    @Column(name = "dosing_pressure")
    private Integer dosingPressure;  // 加药压力，整型

    @Column(name = "dosing_liquid_level")
    private Integer dosingLiquidLevel;  // 加药液位，整型

    @Override
    public String toString() {
        return "DFP4{" +
                "dataId=" + dataId +
                ", updateTime=" + updateTime +
                ", dosingEquipmentStatus=" + dosingEquipmentStatus +
                ", currentMode=" + currentMode +
                ", dosingRunStatus=" + dosingRunStatus +
                ", valve1OpenIndicator=" + valve1OpenIndicator +
                ", valve1OpenStatus=" + valve1OpenStatus +
                ", valve2OpenIndicator=" + valve2OpenIndicator +
                ", valve2OpenStatus=" + valve2OpenStatus +
                ", dosingPressure=" + dosingPressure +
                ", dosingLiquidLevel=" + dosingLiquidLevel +
                '}';
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDosingEquipmentStatus() {
        return dosingEquipmentStatus;
    }

    public void setDosingEquipmentStatus(Boolean dosingEquipmentStatus) {
        this.dosingEquipmentStatus = dosingEquipmentStatus;
    }

    public Integer getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(Integer currentMode) {
        this.currentMode = currentMode;
    }

    public Boolean getDosingRunStatus() {
        return dosingRunStatus;
    }

    public void setDosingRunStatus(Boolean dosingRunStatus) {
        this.dosingRunStatus = dosingRunStatus;
    }

    public Boolean getValve1OpenIndicator() {
        return valve1OpenIndicator;
    }

    public void setValve1OpenIndicator(Boolean valve1OpenIndicator) {
        this.valve1OpenIndicator = valve1OpenIndicator;
    }

    public Boolean getValve1OpenStatus() {
        return valve1OpenStatus;
    }

    public void setValve1OpenStatus(Boolean valve1OpenStatus) {
        this.valve1OpenStatus = valve1OpenStatus;
    }

    public Boolean getValve2OpenIndicator() {
        return valve2OpenIndicator;
    }

    public void setValve2OpenIndicator(Boolean valve2OpenIndicator) {
        this.valve2OpenIndicator = valve2OpenIndicator;
    }

    public Boolean getValve2OpenStatus() {
        return valve2OpenStatus;
    }

    public void setValve2OpenStatus(Boolean valve2OpenStatus) {
        this.valve2OpenStatus = valve2OpenStatus;
    }

    public Integer getDosingPressure() {
        return dosingPressure;
    }

    public void setDosingPressure(Integer dosingPressure) {
        this.dosingPressure = dosingPressure;
    }

    public Integer getDosingLiquidLevel() {
        return dosingLiquidLevel;
    }

    public void setDosingLiquidLevel(Integer dosingLiquidLevel) {
        this.dosingLiquidLevel = dosingLiquidLevel;
    }

    // ======= 随机实例生成方法 =======
    public static DFP4 createRandomInstance() {
        Random random = new Random();
        DFP4 randomObj = new DFP4();

        // 设置当前时间
        randomObj.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        // 布尔值参数随机
        randomObj.setDosingEquipmentStatus(random.nextBoolean());
        randomObj.setDosingRunStatus(random.nextBoolean());
        randomObj.setValve1OpenIndicator(random.nextBoolean());
        randomObj.setValve1OpenStatus(random.nextBoolean());
        randomObj.setValve2OpenIndicator(random.nextBoolean());
        randomObj.setValve2OpenStatus(random.nextBoolean());

        // 整型参数范围 [0, 100]
        randomObj.setCurrentMode(random.nextInt(101));

        // 加药压力范围 [0, 1000]
        randomObj.setDosingPressure(random.nextInt(1001));

        // 加药液位范围 [0, 100]
        randomObj.setDosingLiquidLevel(random.nextInt(101));

        return randomObj;
    }

    public void fixData(){
        // 设置当前时间
        setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }
}
