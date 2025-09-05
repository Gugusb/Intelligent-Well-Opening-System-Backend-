package com.gugusb.hwics.pojo;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "tb_smf_system")
public class ProductionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false)
    private Integer logId;          // ID，键值，自增

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp time;

    @Column(name = "log_type")
    private Integer logType;                         //日志类型：1工艺更新；2参数警告；

    @Column(name = "exceptional_parameter")
    private Integer exceptionalParameter;            //异常参数：1日产气量；2日产液量；3井口油压；4井口套压；5油套压差

    @Column(name = "exceptional_value_cmp")
    private Integer exceptionalValueCMP;             //异常类型：0过小；1过大

    @Column(name = "exceptional_value")
    private Float exceptionalValue;

    @Column(name = "operation_type")
    private Integer operationType;                   //操作类型：1工艺启动；2工艺停止；3参数更新

    @Column(name = "craft_type")
    private Integer craftType;                       //工艺类型：1抽吸混输；2泡排；3气举

    @Column(name = "craft_parameter")
    private Integer craftParameter;                  //更新工艺参数时，填值（对应的参数依工艺类型变化）

    @Column(name = "craft_value")
    private Float craftValue;                        //更新工艺参数时，填值

    @Column(name = "discription")
    private String discription;

    private static final Map<Integer, String> logTypeDic = Map.of(
            1, "工艺更新",
            2, "参数警告"
    );

    private static final Map<Integer, String> exceptionalParameterDic = Map.of(
            1, "日产气量",
            2, "日产液量",
            3, "井口油压",
            4, "井口套压",
            5, "油套压差"
    );

    private static final Map<Integer, String> exceptionalValueCMPDic = Map.of(
            0, "过低",
            1, "过高"
    );

    private static final Map<Integer, String> operationTypeDic = Map.of(
            1, "工艺启动",
            2, "工艺停止",
            3, "参数更新"
    );

    private static final Map<Integer, String> craftTypeDic = Map.of(
            1, "抽吸混输工艺",
            2, "泡排工艺",
            3, "气举工艺"
    );

    public String printDiscription() {
        String head = "No." + logId + " [" + time.toString() + "] ";
        if(exceptionalParameter == null) return head + discription;
        if(exceptionalValue == null) return head + discription;
        if(operationType == null) return head + discription;
        if(craftValue == null) return head + discription;

        String dis = head;
        dis += logTypeDic.get(logType);
        if(logType == 1){
            switch(operationType){
                case 1:{
                    dis += craftTypeDic.get(craftType) + "已经启动。";
                }
                case 2:{
                    dis += craftTypeDic.get(craftType) + "已经结束。";
                }
                case 3:{
                    dis += craftTypeDic.get(craftType) + "工艺参数更新：";
                    switch (craftType){
                        case 1: dis += SuctionMixedFlowSystem.getCraftParameter(craftParameter); break;
                        case 2: dis += FoamDrainageSystem.getCraftParameter(craftParameter); break;
                        case 3: dis += GasLiftSystem.getCraftParameter(craftParameter); break;
                    }
                    dis += "更新为：" + craftValue;
                }
            }
        }else if(logType == 2){
            dis += exceptionalParameterDic.get(exceptionalParameter);
            dis += exceptionalValueCMPDic.get(exceptionalValueCMP);
            dis += "。当前值：" + exceptionalValue;
        }

        return dis;
    }

    @Override
    public String toString() {
        return "ProductionLog{" +
                "logId=" + logId +
                ", time=" + time +
                ", logType=" + logType +
                ", exceptionalParameter=" + exceptionalParameter +
                ", exceptionalValueCMP=" + exceptionalValueCMP +
                ", exceptionalValue=" + exceptionalValue +
                ", operationType=" + operationType +
                ", craftType=" + craftType +
                ", craftParameter=" + craftParameter +
                ", craftValue=" + craftValue +
                ", discription='" + discription + '\'' +
                '}';
    }

    public Integer getExceptionalValueCMP() {
        return exceptionalValueCMP;
    }

    public void setExceptionalValueCMP(Integer exceptionalValueCMP) {
        this.exceptionalValueCMP = exceptionalValueCMP;
    }

    public void setExceptionalParameter(Integer exceptionalParameter) {
        this.exceptionalParameter = exceptionalParameter;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Integer getCraftParameter() {
        return craftParameter;
    }

    public void setCraftParameter(Integer craftParameter) {
        this.craftParameter = craftParameter;
    }

    public Integer getCraftType() {
        return craftType;
    }

    public void setCraftType(Integer craftType) {
        this.craftType = craftType;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = Integer.valueOf(logType);
    }

    public Integer getExceptionalParameter() {
        return exceptionalParameter;
    }

    public void setExceptionalParameter(String exceptionalParameter) {
        this.exceptionalParameter = Integer.valueOf(exceptionalParameter);
    }

    public Float getExceptionalValue() {
        return exceptionalValue;
    }

    public void setExceptionalValue(Float exceptionalValue) {
        this.exceptionalValue = exceptionalValue;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public Float getCraftValue() {
        return craftValue;
    }

    public void setCraftValue(Float operationValue) {
        this.craftValue = operationValue;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
