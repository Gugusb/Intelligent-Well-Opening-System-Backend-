package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;

@Entity
@Table(name = "tb_dfp2")
public class DFP2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dataId;  // 主键ID，自增

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateTime;  // 存储时间

    // 1# 机组参数
    @Column(name = "unit1_start_hour")
    private Integer unit1StartHour;
    @Column(name = "unit1_start_minute")
    private Integer unit1StartMinute;
    @Column(name = "unit1_stop_hour")
    private Integer unit1StopHour;
    @Column(name = "unit1_stop_minute")
    private Integer unit1StopMinute;
    @Column(name = "unit1_start_pressure")
    private Integer unit1StartPressure;
    @Column(name = "unit1_stop_pressure")
    private Integer unit1StopPressure;
    @Column(name = "unit1_left_valve_time")
    private Integer unit1LeftValveTime;
    @Column(name = "unit1_right_valve_time")
    private Integer unit1RightValveTime;

    // 2# 机组参数
    @Column(name = "unit2_start_hour")
    private Integer unit2StartHour;
    @Column(name = "unit2_start_minute")
    private Integer unit2StartMinute;
    @Column(name = "unit2_stop_hour")
    private Integer unit2StopHour;
    @Column(name = "unit2_stop_minute")
    private Integer unit2StopMinute;
    @Column(name = "unit2_start_pressure")
    private Integer unit2StartPressure;
    @Column(name = "unit2_stop_pressure")
    private Integer unit2StopPressure;
    @Column(name = "unit2_left_valve_time")
    private Integer unit2LeftValveTime;
    @Column(name = "unit2_right_valve_time")
    private Integer unit2RightValveTime;

    // 3# 机组参数
    @Column(name = "unit3_start_hour")
    private Integer unit3StartHour;
    @Column(name = "unit3_start_minute")
    private Integer unit3StartMinute;
    @Column(name = "unit3_stop_hour")
    private Integer unit3StopHour;
    @Column(name = "unit3_stop_minute")
    private Integer unit3StopMinute;
    @Column(name = "unit3_start_pressure")
    private Integer unit3StartPressure;
    @Column(name = "unit3_stop_pressure")
    private Integer unit3StopPressure;
    @Column(name = "unit3_left_valve_time")
    private Integer unit3LeftValveTime;
    @Column(name = "unit3_right_valve_time")
    private Integer unit3RightValveTime;

    // 4# 机组参数
    @Column(name = "unit4_start_hour")
    private Integer unit4StartHour;
    @Column(name = "unit4_start_minute")
    private Integer unit4StartMinute;
    @Column(name = "unit4_stop_hour")
    private Integer unit4StopHour;
    @Column(name = "unit4_stop_minute")
    private Integer unit4StopMinute;
    @Column(name = "unit4_start_pressure")
    private Integer unit4StartPressure;
    @Column(name = "unit4_stop_pressure")
    private Integer unit4StopPressure;
    @Column(name = "unit4_left_valve_time")
    private Integer unit4LeftValveTime;
    @Column(name = "unit4_right_valve_time")
    private Integer unit4RightValveTime;

    // 5# 机组参数
    @Column(name = "unit5_start_hour")
    private Integer unit5StartHour;
    @Column(name = "unit5_start_minute")
    private Integer unit5StartMinute;
    @Column(name = "unit5_stop_hour")
    private Integer unit5StopHour;
    @Column(name = "unit5_stop_minute")
    private Integer unit5StopMinute;
    @Column(name = "unit5_start_pressure")
    private Integer unit5StartPressure;
    @Column(name = "unit5_stop_pressure")
    private Integer unit5StopPressure;
    @Column(name = "unit5_left_valve_time")
    private Integer unit5LeftValveTime;
    @Column(name = "unit5_right_valve_time")
    private Integer unit5RightValveTime;

    // 气举参数
    @Column(name = "gaslift1_left_valve_time")
    private Integer gaslift1LeftValveTime;
    @Column(name = "gaslift1_right_valve_time")
    private Integer gaslift1RightValveTime;
    @Column(name = "gaslift2_left_valve_time")
    private Integer gaslift2LeftValveTime;
    @Column(name = "gaslift2_right_valve_time")
    private Integer gaslift2RightValveTime;

    @Override
    public String toString() {
        return "DFP2{" +
                "dataId=" + dataId +
                ", updateTime=" + updateTime +
                ", unit1StartHour=" + unit1StartHour +
                ", unit1StartMinute=" + unit1StartMinute +
                ", unit1StopHour=" + unit1StopHour +
                ", unit1StopMinute=" + unit1StopMinute +
                ", unit1StartPressure=" + unit1StartPressure +
                ", unit1StopPressure=" + unit1StopPressure +
                ", unit1LeftValveTime=" + unit1LeftValveTime +
                ", unit1RightValveTime=" + unit1RightValveTime +
                ", unit2StartHour=" + unit2StartHour +
                ", unit2StartMinute=" + unit2StartMinute +
                ", unit2StopHour=" + unit2StopHour +
                ", unit2StopMinute=" + unit2StopMinute +
                ", unit2StartPressure=" + unit2StartPressure +
                ", unit2StopPressure=" + unit2StopPressure +
                ", unit2LeftValveTime=" + unit2LeftValveTime +
                ", unit2RightValveTime=" + unit2RightValveTime +
                ", unit3StartHour=" + unit3StartHour +
                ", unit3StartMinute=" + unit3StartMinute +
                ", unit3StopHour=" + unit3StopHour +
                ", unit3StopMinute=" + unit3StopMinute +
                ", unit3StartPressure=" + unit3StartPressure +
                ", unit3StopPressure=" + unit3StopPressure +
                ", unit3LeftValveTime=" + unit3LeftValveTime +
                ", unit3RightValveTime=" + unit3RightValveTime +
                ", unit4StartHour=" + unit4StartHour +
                ", unit4StartMinute=" + unit4StartMinute +
                ", unit4StopHour=" + unit4StopHour +
                ", unit4StopMinute=" + unit4StopMinute +
                ", unit4StartPressure=" + unit4StartPressure +
                ", unit4StopPressure=" + unit4StopPressure +
                ", unit4LeftValveTime=" + unit4LeftValveTime +
                ", unit4RightValveTime=" + unit4RightValveTime +
                ", unit5StartHour=" + unit5StartHour +
                ", unit5StartMinute=" + unit5StartMinute +
                ", unit5StopHour=" + unit5StopHour +
                ", unit5StopMinute=" + unit5StopMinute +
                ", unit5StartPressure=" + unit5StartPressure +
                ", unit5StopPressure=" + unit5StopPressure +
                ", unit5LeftValveTime=" + unit5LeftValveTime +
                ", unit5RightValveTime=" + unit5RightValveTime +
                ", gaslift1LeftValveTime=" + gaslift1LeftValveTime +
                ", gaslift1RightValveTime=" + gaslift1RightValveTime +
                ", gaslift2LeftValveTime=" + gaslift2LeftValveTime +
                ", gaslift2RightValveTime=" + gaslift2RightValveTime +
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

    public Integer getUnit1StartHour() {
        return unit1StartHour;
    }

    public void setUnit1StartHour(Integer unit1StartHour) {
        this.unit1StartHour = unit1StartHour;
    }

    public Integer getUnit1StartMinute() {
        return unit1StartMinute;
    }

    public void setUnit1StartMinute(Integer unit1StartMinute) {
        this.unit1StartMinute = unit1StartMinute;
    }

    public Integer getUnit1StopHour() {
        return unit1StopHour;
    }

    public void setUnit1StopHour(Integer unit1StopHour) {
        this.unit1StopHour = unit1StopHour;
    }

    public Integer getUnit1StopMinute() {
        return unit1StopMinute;
    }

    public void setUnit1StopMinute(Integer unit1StopMinute) {
        this.unit1StopMinute = unit1StopMinute;
    }

    public Integer getUnit1StartPressure() {
        return unit1StartPressure;
    }

    public void setUnit1StartPressure(Integer unit1StartPressure) {
        this.unit1StartPressure = unit1StartPressure;
    }

    public Integer getUnit1StopPressure() {
        return unit1StopPressure;
    }

    public void setUnit1StopPressure(Integer unit1StopPressure) {
        this.unit1StopPressure = unit1StopPressure;
    }

    public Integer getUnit1LeftValveTime() {
        return unit1LeftValveTime;
    }

    public void setUnit1LeftValveTime(Integer unit1LeftValveTime) {
        this.unit1LeftValveTime = unit1LeftValveTime;
    }

    public Integer getUnit1RightValveTime() {
        return unit1RightValveTime;
    }

    public void setUnit1RightValveTime(Integer unit1RightValveTime) {
        this.unit1RightValveTime = unit1RightValveTime;
    }

    public Integer getUnit2StartHour() {
        return unit2StartHour;
    }

    public void setUnit2StartHour(Integer unit2StartHour) {
        this.unit2StartHour = unit2StartHour;
    }

    public Integer getUnit2StartMinute() {
        return unit2StartMinute;
    }

    public void setUnit2StartMinute(Integer unit2StartMinute) {
        this.unit2StartMinute = unit2StartMinute;
    }

    public Integer getUnit2StopHour() {
        return unit2StopHour;
    }

    public void setUnit2StopHour(Integer unit2StopHour) {
        this.unit2StopHour = unit2StopHour;
    }

    public Integer getUnit2StopMinute() {
        return unit2StopMinute;
    }

    public void setUnit2StopMinute(Integer unit2StopMinute) {
        this.unit2StopMinute = unit2StopMinute;
    }

    public Integer getUnit2StartPressure() {
        return unit2StartPressure;
    }

    public void setUnit2StartPressure(Integer unit2StartPressure) {
        this.unit2StartPressure = unit2StartPressure;
    }

    public Integer getUnit2StopPressure() {
        return unit2StopPressure;
    }

    public void setUnit2StopPressure(Integer unit2StopPressure) {
        this.unit2StopPressure = unit2StopPressure;
    }

    public Integer getUnit2LeftValveTime() {
        return unit2LeftValveTime;
    }

    public void setUnit2LeftValveTime(Integer unit2LeftValveTime) {
        this.unit2LeftValveTime = unit2LeftValveTime;
    }

    public Integer getUnit2RightValveTime() {
        return unit2RightValveTime;
    }

    public void setUnit2RightValveTime(Integer unit2RightValveTime) {
        this.unit2RightValveTime = unit2RightValveTime;
    }

    public Integer getUnit3StartHour() {
        return unit3StartHour;
    }

    public void setUnit3StartHour(Integer unit3StartHour) {
        this.unit3StartHour = unit3StartHour;
    }

    public Integer getUnit3StartMinute() {
        return unit3StartMinute;
    }

    public void setUnit3StartMinute(Integer unit3StartMinute) {
        this.unit3StartMinute = unit3StartMinute;
    }

    public Integer getUnit3StopHour() {
        return unit3StopHour;
    }

    public void setUnit3StopHour(Integer unit3StopHour) {
        this.unit3StopHour = unit3StopHour;
    }

    public Integer getUnit3StopMinute() {
        return unit3StopMinute;
    }

    public void setUnit3StopMinute(Integer unit3StopMinute) {
        this.unit3StopMinute = unit3StopMinute;
    }

    public Integer getUnit3StartPressure() {
        return unit3StartPressure;
    }

    public void setUnit3StartPressure(Integer unit3StartPressure) {
        this.unit3StartPressure = unit3StartPressure;
    }

    public Integer getUnit3StopPressure() {
        return unit3StopPressure;
    }

    public void setUnit3StopPressure(Integer unit3StopPressure) {
        this.unit3StopPressure = unit3StopPressure;
    }

    public Integer getUnit3LeftValveTime() {
        return unit3LeftValveTime;
    }

    public void setUnit3LeftValveTime(Integer unit3LeftValveTime) {
        this.unit3LeftValveTime = unit3LeftValveTime;
    }

    public Integer getUnit3RightValveTime() {
        return unit3RightValveTime;
    }

    public void setUnit3RightValveTime(Integer unit3RightValveTime) {
        this.unit3RightValveTime = unit3RightValveTime;
    }

    public Integer getUnit4StartHour() {
        return unit4StartHour;
    }

    public void setUnit4StartHour(Integer unit4StartHour) {
        this.unit4StartHour = unit4StartHour;
    }

    public Integer getUnit4StartMinute() {
        return unit4StartMinute;
    }

    public void setUnit4StartMinute(Integer unit4StartMinute) {
        this.unit4StartMinute = unit4StartMinute;
    }

    public Integer getUnit4StopHour() {
        return unit4StopHour;
    }

    public void setUnit4StopHour(Integer unit4StopHour) {
        this.unit4StopHour = unit4StopHour;
    }

    public Integer getUnit4StopMinute() {
        return unit4StopMinute;
    }

    public void setUnit4StopMinute(Integer unit4StopMinute) {
        this.unit4StopMinute = unit4StopMinute;
    }

    public Integer getUnit4StartPressure() {
        return unit4StartPressure;
    }

    public void setUnit4StartPressure(Integer unit4StartPressure) {
        this.unit4StartPressure = unit4StartPressure;
    }

    public Integer getUnit4StopPressure() {
        return unit4StopPressure;
    }

    public void setUnit4StopPressure(Integer unit4StopPressure) {
        this.unit4StopPressure = unit4StopPressure;
    }

    public Integer getUnit4LeftValveTime() {
        return unit4LeftValveTime;
    }

    public void setUnit4LeftValveTime(Integer unit4LeftValveTime) {
        this.unit4LeftValveTime = unit4LeftValveTime;
    }

    public Integer getUnit4RightValveTime() {
        return unit4RightValveTime;
    }

    public void setUnit4RightValveTime(Integer unit4RightValveTime) {
        this.unit4RightValveTime = unit4RightValveTime;
    }

    public Integer getUnit5StartHour() {
        return unit5StartHour;
    }

    public void setUnit5StartHour(Integer unit5StartHour) {
        this.unit5StartHour = unit5StartHour;
    }

    public Integer getUnit5StartMinute() {
        return unit5StartMinute;
    }

    public void setUnit5StartMinute(Integer unit5StartMinute) {
        this.unit5StartMinute = unit5StartMinute;
    }

    public Integer getUnit5StopHour() {
        return unit5StopHour;
    }

    public void setUnit5StopHour(Integer unit5StopHour) {
        this.unit5StopHour = unit5StopHour;
    }

    public Integer getUnit5StopMinute() {
        return unit5StopMinute;
    }

    public void setUnit5StopMinute(Integer unit5StopMinute) {
        this.unit5StopMinute = unit5StopMinute;
    }

    public Integer getUnit5StartPressure() {
        return unit5StartPressure;
    }

    public void setUnit5StartPressure(Integer unit5StartPressure) {
        this.unit5StartPressure = unit5StartPressure;
    }

    public Integer getUnit5StopPressure() {
        return unit5StopPressure;
    }

    public void setUnit5StopPressure(Integer unit5StopPressure) {
        this.unit5StopPressure = unit5StopPressure;
    }

    public Integer getUnit5LeftValveTime() {
        return unit5LeftValveTime;
    }

    public void setUnit5LeftValveTime(Integer unit5LeftValveTime) {
        this.unit5LeftValveTime = unit5LeftValveTime;
    }

    public Integer getUnit5RightValveTime() {
        return unit5RightValveTime;
    }

    public void setUnit5RightValveTime(Integer unit5RightValveTime) {
        this.unit5RightValveTime = unit5RightValveTime;
    }

    public Integer getGaslift1LeftValveTime() {
        return gaslift1LeftValveTime;
    }

    public void setGaslift1LeftValveTime(Integer gaslift1LeftValveTime) {
        this.gaslift1LeftValveTime = gaslift1LeftValveTime;
    }

    public Integer getGaslift1RightValveTime() {
        return gaslift1RightValveTime;
    }

    public void setGaslift1RightValveTime(Integer gaslift1RightValveTime) {
        this.gaslift1RightValveTime = gaslift1RightValveTime;
    }

    public Integer getGaslift2LeftValveTime() {
        return gaslift2LeftValveTime;
    }

    public void setGaslift2LeftValveTime(Integer gaslift2LeftValveTime) {
        this.gaslift2LeftValveTime = gaslift2LeftValveTime;
    }

    public Integer getGaslift2RightValveTime() {
        return gaslift2RightValveTime;
    }

    public void setGaslift2RightValveTime(Integer gaslift2RightValveTime) {
        this.gaslift2RightValveTime = gaslift2RightValveTime;
    }

    public static DFP2 createRandomInstance() {
        Random random = new Random();
        DFP2 randomObj = new DFP2();

        // 设置当前时间作为存储时间
        randomObj.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        // 1# 机组参数
        setUnitParameters(random, randomObj, "1", 0, 2000);
        // 2# 机组参数
        setUnitParameters(random, randomObj, "2", 0, 2000);
        // 3# 机组参数
        setUnitParameters(random, randomObj, "3", 0, 2000);
        // 4# 机组参数
        setUnitParameters(random, randomObj, "4", 0, 2000);
        // 5# 机组参数
        setUnitParameters(random, randomObj, "5", 0, 2000);

        // 气举参数 (时间范围: 0-3600秒/小时)
        randomObj.setGaslift1LeftValveTime(random.nextInt(3601));
        randomObj.setGaslift1RightValveTime(random.nextInt(3601));
        randomObj.setGaslift2LeftValveTime(random.nextInt(3601));
        randomObj.setGaslift2RightValveTime(random.nextInt(3601));

        return randomObj;
    }

    // 辅助方法：设置单个机组的参数
    private static void setUnitParameters(Random random, DFP2 obj, String unitId, int minPressure, int maxPressure) {
        try {
            // 获取类的setter方法
            Class<?> cls = obj.getClass();

            // 设置时间参数 (小时0-23, 分钟0-59)
            Method startHourSetter = cls.getMethod("setUnit" + unitId + "StartHour", Integer.class);
            startHourSetter.invoke(obj, random.nextInt(24));

            Method startMinuteSetter = cls.getMethod("setUnit" + unitId + "StartMinute", Integer.class);
            startMinuteSetter.invoke(obj, random.nextInt(60));

            Method stopHourSetter = cls.getMethod("setUnit" + unitId + "StopHour", Integer.class);
            stopHourSetter.invoke(obj, random.nextInt(24));

            Method stopMinuteSetter = cls.getMethod("setUnit" + unitId + "StopMinute", Integer.class);
            stopMinuteSetter.invoke(obj, random.nextInt(60));

            // 设置压力参数 (自定义范围)
            int pressureRange = maxPressure - minPressure;
            Method startPressureSetter = cls.getMethod("setUnit" + unitId + "StartPressure", Integer.class);
            startPressureSetter.invoke(obj, minPressure + random.nextInt(pressureRange + 1));

            Method stopPressureSetter = cls.getMethod("setUnit" + unitId + "StopPressure", Integer.class);
            stopPressureSetter.invoke(obj, minPressure + random.nextInt(pressureRange + 1));

            // 设置阀门时间 (0-3600秒)
            Method leftValveSetter = cls.getMethod("setUnit" + unitId + "LeftValveTime", Integer.class);
            leftValveSetter.invoke(obj, random.nextInt(3601));

            Method rightValveSetter = cls.getMethod("setUnit" + unitId + "RightValveTime", Integer.class);
            rightValveSetter.invoke(obj, random.nextInt(3601));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
