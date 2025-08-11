package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;


@Entity
@Table(name = "tb_dfp3")
public class DFP3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dataId;  // 主键ID，自增

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateTime;  // 存储时间

    // 1#2#3#后端压力参数
    @Column(name = "backend_alarm_pressure123")
    private Integer backendAlarmPressure123;      // 1#2#3#后端报警压力

    @Column(name = "backend_recovery_pressure123")
    private Integer backendRecoveryPressure123;   // 1#2#3#后端恢复压力

    // 4#5#后端压力参数
    @Column(name = "backend_alarm_pressure45")
    private Integer backendAlarmPressure45;        // 4#5#后端报警压力

    @Column(name = "backend_recovery_pressure45")
    private Integer backendRecoveryPressure45;     // 4#5#后端恢复压力

    // 气举1#后端压力参数
    @Column(name = "gaslift1_backend_alarm_pressure")
    private Integer gaslift1BackendAlarmPressure;  // 气举1#后端报警压力

    @Column(name = "gaslift1_backend_recovery_pressure")
    private Integer gaslift1BackendRecoveryPressure; // 气举1#后端恢复压力

    // 气举2#后端压力参数
    @Column(name = "gaslift2_backend_alarm_pressure")
    private Integer gaslift2BackendAlarmPressure;  // 气举2#后端报警压力

    // 机组启用状态
    @Column(name = "unit1_enabled")
    private Boolean unit1Enabled;  // 1#机组启用状态

    @Column(name = "unit2_enabled")
    private Boolean unit2Enabled;  // 2#机组启用状态

    @Column(name = "unit3_enabled")
    private Boolean unit3Enabled;  // 3#机组启用状态

    @Column(name = "unit4_enabled")
    private Boolean unit4Enabled;  // 4#机组启用状态

    @Column(name = "unit5_enabled")
    private Boolean unit5Enabled;  // 5#机组启用状态

    @Override
    public String toString() {
        return "DFP3{" +
                "dataId=" + dataId +
                ", updateTime=" + updateTime +
                ", backendAlarmPressure123=" + backendAlarmPressure123 +
                ", backendRecoveryPressure123=" + backendRecoveryPressure123 +
                ", backendAlarmPressure45=" + backendAlarmPressure45 +
                ", backendRecoveryPressure45=" + backendRecoveryPressure45 +
                ", gaslift1BackendAlarmPressure=" + gaslift1BackendAlarmPressure +
                ", gaslift1BackendRecoveryPressure=" + gaslift1BackendRecoveryPressure +
                ", gaslift2BackendAlarmPressure=" + gaslift2BackendAlarmPressure +
                ", unit1Enabled=" + unit1Enabled +
                ", unit2Enabled=" + unit2Enabled +
                ", unit3Enabled=" + unit3Enabled +
                ", unit4Enabled=" + unit4Enabled +
                ", unit5Enabled=" + unit5Enabled +
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

    public Integer getBackendAlarmPressure123() {
        return backendAlarmPressure123;
    }

    public void setBackendAlarmPressure123(Integer backendAlarmPressure123) {
        this.backendAlarmPressure123 = backendAlarmPressure123;
    }

    public Integer getBackendRecoveryPressure123() {
        return backendRecoveryPressure123;
    }

    public void setBackendRecoveryPressure123(Integer backendRecoveryPressure123) {
        this.backendRecoveryPressure123 = backendRecoveryPressure123;
    }

    public Integer getBackendAlarmPressure45() {
        return backendAlarmPressure45;
    }

    public void setBackendAlarmPressure45(Integer backendAlarmPressure45) {
        this.backendAlarmPressure45 = backendAlarmPressure45;
    }

    public Integer getBackendRecoveryPressure45() {
        return backendRecoveryPressure45;
    }

    public void setBackendRecoveryPressure45(Integer backendRecoveryPressure45) {
        this.backendRecoveryPressure45 = backendRecoveryPressure45;
    }

    public Integer getGaslift1BackendAlarmPressure() {
        return gaslift1BackendAlarmPressure;
    }

    public void setGaslift1BackendAlarmPressure(Integer gaslift1BackendAlarmPressure) {
        this.gaslift1BackendAlarmPressure = gaslift1BackendAlarmPressure;
    }

    public Integer getGaslift1BackendRecoveryPressure() {
        return gaslift1BackendRecoveryPressure;
    }

    public void setGaslift1BackendRecoveryPressure(Integer gaslift1BackendRecoveryPressure) {
        this.gaslift1BackendRecoveryPressure = gaslift1BackendRecoveryPressure;
    }

    public Integer getGaslift2BackendAlarmPressure() {
        return gaslift2BackendAlarmPressure;
    }

    public void setGaslift2BackendAlarmPressure(Integer gaslift2BackendAlarmPressure) {
        this.gaslift2BackendAlarmPressure = gaslift2BackendAlarmPressure;
    }

    public Boolean getUnit1Enabled() {
        return unit1Enabled;
    }

    public void setUnit1Enabled(Boolean unit1Enabled) {
        this.unit1Enabled = unit1Enabled;
    }

    public Boolean getUnit2Enabled() {
        return unit2Enabled;
    }

    public void setUnit2Enabled(Boolean unit2Enabled) {
        this.unit2Enabled = unit2Enabled;
    }

    public Boolean getUnit3Enabled() {
        return unit3Enabled;
    }

    public void setUnit3Enabled(Boolean unit3Enabled) {
        this.unit3Enabled = unit3Enabled;
    }

    public Boolean getUnit4Enabled() {
        return unit4Enabled;
    }

    public void setUnit4Enabled(Boolean unit4Enabled) {
        this.unit4Enabled = unit4Enabled;
    }

    public Boolean getUnit5Enabled() {
        return unit5Enabled;
    }

    public void setUnit5Enabled(Boolean unit5Enabled) {
        this.unit5Enabled = unit5Enabled;
    }

    public static DFP3 createRandomInstance() {
        Random random = new Random();
        DFP3 randomObj = new DFP3();

        // 设置当前时间
        randomObj.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        // 压力参数范围 [0, 1000]
        randomObj.setBackendAlarmPressure123(random.nextInt(1001));
        randomObj.setBackendRecoveryPressure123(random.nextInt(1001));
        randomObj.setBackendAlarmPressure45(random.nextInt(1001));
        randomObj.setBackendRecoveryPressure45(random.nextInt(1001));
        randomObj.setGaslift1BackendAlarmPressure(random.nextInt(1001));
        randomObj.setGaslift1BackendRecoveryPressure(random.nextInt(1001));
        randomObj.setGaslift2BackendAlarmPressure(random.nextInt(1001));

        // 机组启用状态随机布尔值
        randomObj.setUnit1Enabled(random.nextBoolean());
        randomObj.setUnit2Enabled(random.nextBoolean());
        randomObj.setUnit3Enabled(random.nextBoolean());
        randomObj.setUnit4Enabled(random.nextBoolean());
        randomObj.setUnit5Enabled(random.nextBoolean());

        return randomObj;
    }

    public void fixData(){
        // 设置当前时间
        setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }
}
