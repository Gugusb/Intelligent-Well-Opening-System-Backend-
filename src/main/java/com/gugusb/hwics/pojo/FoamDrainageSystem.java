package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "tb_fd_system")
public class FoamDrainageSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id", nullable = false)
    private Integer progressId;          // ID，键值，自增

    @Column(name = "dilution_ratio")
    private Float dilutionRatio;

    @Column(name = "pump_drug_speed")
    private Float pumpDrugSpeed;

    @Column(name = "drug_injection_pressure")
    private Float drugInjectionPressure;

    @Column(name = "drug_concentration")
    private Float drugConcentration;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp endTime;

    private static final Map<Integer, String> craftParameterDic = Map.of(
            1, "稀释比",
            2, "加药浓度",
            3, "泵药速度",
            4, "加药压力"
    );

    public static String getCraftParameter(Integer craftid){
        return craftParameterDic.get(craftid);
    }

    @Override
    public String toString() {
        return "FoamDrainageSystem{" +
                "progressId=" + progressId +
                ", dilutionRatio=" + dilutionRatio +
                ", pumpDrugSpeed=" + pumpDrugSpeed +
                ", drugInjectionPressure=" + drugInjectionPressure +
                ", drugConcentration=" + drugConcentration +
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

    public Float getDilutionRatio() {
        return dilutionRatio;
    }

    public void setDilutionRatio(Float dilutionRatio) {
        this.dilutionRatio = dilutionRatio;
    }

    public Float getPumpDrugSpeed() {
        return pumpDrugSpeed;
    }

    public void setPumpDrugSpeed(Float pumpDrugSpeed) {
        this.pumpDrugSpeed = pumpDrugSpeed;
    }

    public Float getDrugInjectionPressure() {
        return drugInjectionPressure;
    }

    public void setDrugInjectionPressure(Float drugInjectionPressure) {
        this.drugInjectionPressure = drugInjectionPressure;
    }

    public Float getDrugConcentration() {
        return drugConcentration;
    }

    public void setDrugConcentration(Float drugConcentration) {
        this.drugConcentration = drugConcentration;
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
