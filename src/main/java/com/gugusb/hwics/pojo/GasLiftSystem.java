package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "tb_gl_system")
public class GasLiftSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id", nullable = false)
    private Integer progressId;          // ID，键值，自增

    @Column(name = "gas_injection_rate")
    private Float gasInjectionRate;

    @Column(name = "gas_injection_pressure")
    private Float GasInjectionPressure;

    @Column(name = "machine_count")
    private Integer machineCount;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp endTime;

    private static final Map<Integer, String> craftParameterDic = Map.of(
            1, "注气速度",
            2, "注气压力",
            3, "机器数量"
    );

    public static String getCraftParameter(Integer craftid){
        return craftParameterDic.get(craftid);
    }

    @Override
    public String toString() {
        return "GasLiftSystem{" +
                "progressId=" + progressId +
                ", gasInjectionRate=" + gasInjectionRate +
                ", GasInjectionPressure=" + GasInjectionPressure +
                ", machineCount=" + machineCount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Integer getProgressId() {
        return progressId;
    }

    public void setProgressId(Integer progressId) {
        this.progressId = progressId;
    }

    public Float getGasInjectionRate() {
        return gasInjectionRate;
    }

    public void setGasInjectionRate(Float gasInjectionRate) {
        this.gasInjectionRate = gasInjectionRate;
    }

    public Float getGasInjectionPressure() {
        return GasInjectionPressure;
    }

    public void setGasInjectionPressure(Float gasInjectionPressure) {
        GasInjectionPressure = gasInjectionPressure;
    }

    public Integer getMachineCount() {
        return machineCount;
    }

    public void setMachineCount(Integer machineCount) {
        this.machineCount = machineCount;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
