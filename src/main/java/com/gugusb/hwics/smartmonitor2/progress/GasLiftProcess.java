package com.gugusb.hwics.smartmonitor2.progress;

import com.gugusb.hwics.smartmonitor2.entity.GasLiftParams;
import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;
import com.gugusb.hwics.smartmonitor2.entity.processstate.GasLiftProgressState;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class GasLiftProcess{
    @Autowired
    GasLiftTimer gasLiftTimer;

    protected boolean running = false;
    protected boolean inCooldown = false;
    // 判断气举是否开启
    private Integer conditionProcess = 0;
    private static Integer maxConditionProcess = 1 * 2;
    private static Double conditionHitRate = 0.6;
    // 判断气举是否续费
    private Double continueAvg = 0.0;
    private Integer avgCount = 0;
    private Integer continuePorcess = 0;
    private static Double range = 0.2;
    private static Integer maxContinueProcess = 60 * 1;
    private static Double continueHitRate = 0.6;

    public void startWithParams(GasLiftParams params) {
        // TODO: 调用 Kepserver 写入逻辑，开启气举设备
        System.out.println("[气举] 开启工艺，使用参数: " + params);
        gasLiftTimer.addTask(LocalDateTime.now().plus(Duration.ofMinutes(2)),
                Duration.ofMillis(Math.round(params.getGasInjectionTimeDef() * 3_600_000)));
        running = true;
    }

    public void startWithHours(Double hours) {
        // TODO: 调用 Kepserver 写入逻辑，开启气举设备
        System.out.println("[气举] 延续工艺，延长" + hours + "小时");
        gasLiftTimer.addTask(LocalDateTime.now().plus(Duration.ofMinutes(2)),
                Duration.ofMillis(Math.round(hours * 3_600_000)));
        running = true;
    }

    public void stop() {
        // TODO: 调用 Kepserver 写入逻辑，关闭气举设备
        System.out.println("[气举] 工艺停止");
        running = false;
    }

    public boolean canStart(ProductionSnapshot snapshot, GasLiftParams gasLiftParams) {
        // 条件 GL1 的判断逻辑，这里暂时用伪代码
        if(conditionProcess >= maxConditionProcess * conditionHitRate){
            conditionProcess = 0;
            return true;
        }else{
            System.out.println("当前生产" + snapshot.toString());
            if(snapshot.getWellheadPressure() / 100 < 3){
                System.out.println("【GasLiftProcess】油压低于警戒值，气举启动进度：" + conditionProcess + "/" + maxConditionProcess * conditionHitRate);
                conditionProcess += 1;
            }else{
                conditionProcess -= 1;
                if(conditionProcess < 0)conditionProcess = 0;
            }
        }
        return false; // 举例：井口压力超过某阈值一段时间后触发
    }

    public boolean needContinue(ProductionSnapshot snapshot, GasLiftProgressState gasLiftProgressState) {
        // 气井续费的判断逻辑，如果连续一小时
        if(continuePorcess >= maxContinueProcess * continueHitRate){
            continuePorcess = 0;
            continueAvg = 0d;
            avgCount = 0;
            return true;
        }else{
            Double dif = snapshot.getCasingPressure() - snapshot.getWellheadPressure();
            if(avgCount == 0){
                continueAvg = dif;
                avgCount = 1;
                return false;
            }
            continueAvg = (continueAvg * avgCount + dif) / (avgCount + 1);
            avgCount += 1;
            if(Math.abs(dif - continueAvg) / continueAvg < range){
                continuePorcess += 1;
            }else{
                continuePorcess-= 1;
                if(continuePorcess < 0)continuePorcess = 0;
            }
        }
        return false; // 举例：井口压力超过某阈值一段时间后触发
    }

    public void initContinueProcess(){
        this.continuePorcess = 0;
        this.continueAvg = 0d;
        this.avgCount = 0;
    }
}
