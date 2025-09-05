package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "tb_smf_system")
public class SuctionMixedFlowSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id", nullable = false)
    private Integer progressId;          // ID，键值，自增

    @Column(name = "maximum_negative_pressure")
    private Float maximumNegativePressure;

    @Column(name = "machine_power")
    private Float machinePower;

    @Column(name = "machine_count")
    private Integer machineCount;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp endTime;

    private static final Map<Integer, String> craftParameterDic = Map.of(
            1, "最大负压",
            2, "机器功率",
            3, "启用机器"
    );

    public static String getCraftParameter(Integer craftid){
        return craftParameterDic.get(craftid);
    }

    @Override
    public String toString() {
        return "SuctionMixedFlowSystem{" +
                "progressId=" + progressId +
                ", maximumNegativePressure=" + maximumNegativePressure +
                ", machinePower=" + machinePower +
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

    public Float getMaximumNegativePressure() {
        return maximumNegativePressure;
    }

    public void setMaximumNegativePressure(Float maximumNegativePressure) {
        this.maximumNegativePressure = maximumNegativePressure;
    }

    public Float getMachinePower() {
        return machinePower;
    }

    public void setMachinePower(Float machinePower) {
        this.machinePower = machinePower;
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
