package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Random;


@Entity
@Table(name = "tb_dfp5")
public class DFP5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dataId;  // 主键ID，自增

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updateTime;  // 存储时间

    // 压力参数
    @Column(name = "pressure_alarm")
    private Integer pressureAlarm;  // 压力报警值

    @Column(name = "pressure_recovery")
    private Integer pressureRecovery;  // 压力恢复值

    // 液位参数
    @Column(name = "low_level_warning")
    private Integer lowLevelWarning;  // 低液位警告值

    @Column(name = "high_level_warning")
    private Integer highLevelWarning;  // 高液位警告值

    @Column(name = "low_level_alarm")
    private Integer lowLevelAlarm;  // 低液位报警值

    @Column(name = "high_level_alarm")
    private Integer highLevelAlarm;  // 高液位报警值

    // 第一次加药时间
    @Column(name = "first_dosing_start_hour")
    private Integer firstDosingStartHour;

    @Column(name = "first_dosing_start_minute")
    private Integer firstDosingStartMinute;

    @Column(name = "first_dosing_stop_hour")
    private Integer firstDosingStopHour;

    @Column(name = "first_dosing_stop_minute")
    private Integer firstDosingStopMinute;

    // 第二次加药时间
    @Column(name = "second_dosing_start_hour")
    private Integer secondDosingStartHour;

    @Column(name = "second_dosing_start_minute")
    private Integer secondDosingStartMinute;

    @Column(name = "second_dosing_stop_hour")
    private Integer secondDosingStopHour;

    @Column(name = "second_dosing_stop_minute")
    private Integer secondDosingStopMinute;

    // 第三次加药时间
    @Column(name = "third_dosing_start_hour")
    private Integer thirdDosingStartHour;

    @Column(name = "third_dosing_start_minute")
    private Integer thirdDosingStartMinute;

    @Column(name = "third_dosing_stop_hour")
    private Integer thirdDosingStopHour;

    @Column(name = "third_dosing_stop_minute")
    private Integer thirdDosingStopMinute;

    @Override
    public String toString() {
        return "DFP5{" +
                "dataId=" + dataId +
                ", updateTime=" + updateTime +
                ", pressureAlarm=" + pressureAlarm +
                ", pressureRecovery=" + pressureRecovery +
                ", lowLevelWarning=" + lowLevelWarning +
                ", highLevelWarning=" + highLevelWarning +
                ", lowLevelAlarm=" + lowLevelAlarm +
                ", highLevelAlarm=" + highLevelAlarm +
                ", firstDosingStartHour=" + firstDosingStartHour +
                ", firstDosingStartMinute=" + firstDosingStartMinute +
                ", firstDosingStopHour=" + firstDosingStopHour +
                ", firstDosingStopMinute=" + firstDosingStopMinute +
                ", secondDosingStartHour=" + secondDosingStartHour +
                ", secondDosingStartMinute=" + secondDosingStartMinute +
                ", secondDosingStopHour=" + secondDosingStopHour +
                ", secondDosingStopMinute=" + secondDosingStopMinute +
                ", thirdDosingStartHour=" + thirdDosingStartHour +
                ", thirdDosingStartMinute=" + thirdDosingStartMinute +
                ", thirdDosingStopHour=" + thirdDosingStopHour +
                ", thirdDosingStopMinute=" + thirdDosingStopMinute +
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

    public Integer getPressureAlarm() {
        return pressureAlarm;
    }

    public void setPressureAlarm(Integer pressureAlarm) {
        this.pressureAlarm = pressureAlarm;
    }

    public Integer getPressureRecovery() {
        return pressureRecovery;
    }

    public void setPressureRecovery(Integer pressureRecovery) {
        this.pressureRecovery = pressureRecovery;
    }

    public Integer getLowLevelWarning() {
        return lowLevelWarning;
    }

    public void setLowLevelWarning(Integer lowLevelWarning) {
        this.lowLevelWarning = lowLevelWarning;
    }

    public Integer getHighLevelWarning() {
        return highLevelWarning;
    }

    public void setHighLevelWarning(Integer highLevelWarning) {
        this.highLevelWarning = highLevelWarning;
    }

    public Integer getLowLevelAlarm() {
        return lowLevelAlarm;
    }

    public void setLowLevelAlarm(Integer lowLevelAlarm) {
        this.lowLevelAlarm = lowLevelAlarm;
    }

    public Integer getHighLevelAlarm() {
        return highLevelAlarm;
    }

    public void setHighLevelAlarm(Integer highLevelAlarm) {
        this.highLevelAlarm = highLevelAlarm;
    }

    public Integer getFirstDosingStartHour() {
        return firstDosingStartHour;
    }

    public void setFirstDosingStartHour(Integer firstDosingStartHour) {
        this.firstDosingStartHour = firstDosingStartHour;
    }

    public Integer getFirstDosingStartMinute() {
        return firstDosingStartMinute;
    }

    public void setFirstDosingStartMinute(Integer firstDosingStartMinute) {
        this.firstDosingStartMinute = firstDosingStartMinute;
    }

    public Integer getFirstDosingStopHour() {
        return firstDosingStopHour;
    }

    public void setFirstDosingStopHour(Integer firstDosingStopHour) {
        this.firstDosingStopHour = firstDosingStopHour;
    }

    public Integer getFirstDosingStopMinute() {
        return firstDosingStopMinute;
    }

    public void setFirstDosingStopMinute(Integer firstDosingStopMinute) {
        this.firstDosingStopMinute = firstDosingStopMinute;
    }

    public Integer getSecondDosingStartHour() {
        return secondDosingStartHour;
    }

    public void setSecondDosingStartHour(Integer secondDosingStartHour) {
        this.secondDosingStartHour = secondDosingStartHour;
    }

    public Integer getSecondDosingStartMinute() {
        return secondDosingStartMinute;
    }

    public void setSecondDosingStartMinute(Integer secondDosingStartMinute) {
        this.secondDosingStartMinute = secondDosingStartMinute;
    }

    public Integer getSecondDosingStopHour() {
        return secondDosingStopHour;
    }

    public void setSecondDosingStopHour(Integer secondDosingStopHour) {
        this.secondDosingStopHour = secondDosingStopHour;
    }

    public Integer getSecondDosingStopMinute() {
        return secondDosingStopMinute;
    }

    public void setSecondDosingStopMinute(Integer secondDosingStopMinute) {
        this.secondDosingStopMinute = secondDosingStopMinute;
    }

    public Integer getThirdDosingStartHour() {
        return thirdDosingStartHour;
    }

    public void setThirdDosingStartHour(Integer thirdDosingStartHour) {
        this.thirdDosingStartHour = thirdDosingStartHour;
    }

    public Integer getThirdDosingStartMinute() {
        return thirdDosingStartMinute;
    }

    public void setThirdDosingStartMinute(Integer thirdDosingStartMinute) {
        this.thirdDosingStartMinute = thirdDosingStartMinute;
    }

    public Integer getThirdDosingStopHour() {
        return thirdDosingStopHour;
    }

    public void setThirdDosingStopHour(Integer thirdDosingStopHour) {
        this.thirdDosingStopHour = thirdDosingStopHour;
    }

    public Integer getThirdDosingStopMinute() {
        return thirdDosingStopMinute;
    }

    public void setThirdDosingStopMinute(Integer thirdDosingStopMinute) {
        this.thirdDosingStopMinute = thirdDosingStopMinute;
    }

    // 随机实例生成方法
    public static DFP5 createRandomInstance() {
        Random random = new Random();
        DFP5 randomObj = new DFP5();

        // 设置当前时间
        randomObj.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        // 压力参数范围 [50, 200]
        randomObj.setPressureAlarm(50 + random.nextInt(151));  // 50-200
        randomObj.setPressureRecovery(50 + random.nextInt(151));

        // 液位参数范围 [0, 100]
        randomObj.setLowLevelWarning(random.nextInt(101));
        randomObj.setHighLevelWarning(random.nextInt(101));
        randomObj.setLowLevelAlarm(random.nextInt(101));
        randomObj.setHighLevelAlarm(random.nextInt(101));

        // 设置加药时间范围
        setDosingTimes(randomObj, "first", random);
        setDosingTimes(randomObj, "second", random);
        setDosingTimes(randomObj, "third", random);

        return randomObj;
    }

    // 辅助方法：设置单次加药时间
    private static void setDosingTimes(DFP5 obj, String prefix, Random random) {
        String startHour = prefix + "DosingStartHour";
        String startMinute = prefix + "DosingStartMinute";
        String stopHour = prefix + "DosingStopHour";
        String stopMinute = prefix + "DosingStopMinute";

        try {
            // 通过反射设置时间字段
            Class<?> cls = obj.getClass();

            // 开始时间 (0-23小时, 0-59分钟)
            Method startHourSetter = cls.getMethod("set" + capitalize(startHour), Integer.class);
            startHourSetter.invoke(obj, random.nextInt(24));

            Method startMinuteSetter = cls.getMethod("set" + capitalize(startMinute), Integer.class);
            startMinuteSetter.invoke(obj, random.nextInt(60));

            // 结束时间 (需在开始时间之后)
            int stopHourVal = random.nextInt(24);
            Method stopHourSetter = cls.getMethod("set" + capitalize(stopHour), Integer.class);
            stopHourSetter.invoke(obj, stopHourVal);

            Method stopMinuteSetter = cls.getMethod("set" + capitalize(stopMinute), Integer.class);
            stopMinuteSetter.invoke(obj, random.nextInt(60));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 辅助方法：首字母大写
    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public void fixData(){
        // 设置当前时间
        setUpdateTime(new Timestamp(System.currentTimeMillis()));
    }
}
