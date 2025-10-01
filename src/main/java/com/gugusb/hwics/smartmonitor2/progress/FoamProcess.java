package com.gugusb.hwics.smartmonitor2.progress;

import com.gugusb.hwics.smartmonitor2.DeviceManager;
import com.gugusb.hwics.smartmonitor2.entity.FoamParams;
import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class FoamProcess{
    @Autowired
    DeviceManager deviceManager;
    @Autowired
    GasLiftTimer gasLiftTimer;

    protected boolean running = false;
    protected boolean inCooldown = false;
    private Integer conditionProcess = 0;
    private static Integer maxConditionProcess = 1 * 6;
    private static Double conditionHitRate = 0.6;

    public void startWithParams(FoamParams params){
        // TODO: 调用 Kepserver 写入逻辑，开启泡排设备
        System.out.println("[泡排] 开启工艺，使用参数: " + params);
        deviceManager.turnOnFoam(LocalDateTime.now().plus(Duration.ofMinutes(2)),
                Duration.ofMillis(Math.round(params.getInjectionTimeDef() * 3_600_000)));
        gasLiftTimer.addTask(LocalDateTime.now().plus(Duration.ofMinutes(2)),
                Duration.ofMillis(Math.round(params.getInjectionTimeDef() * 3_600_000)));
        running = true;
    }

    public void stop() {
        // TODO: 调用 Kepserver 写入逻辑，关闭泡排设备
        System.out.println("[泡排] 工艺停止");
        running = false;
    }

    public boolean canStart(ProductionSnapshot snapshot, FoamParams foamParams) {
        // 条件 P1 的判断逻辑
        if(conditionProcess >= maxConditionProcess * conditionHitRate){
            conditionProcess = 0;
            return true;
        }else{
            if(snapshot.getCasingPressure() - snapshot.getWellheadPressure() < 20.0){
                System.out.println("【FomaProccess】:气井油套压差过大，若长时间如此将开启泡排");
                conditionProcess += 1;
            }else{
                conditionProcess -= 1;
                if(conditionProcess < 0)conditionProcess = 0;
            }
        }
        return false; // 举例：井口压力超过某阈值一段时间后触发
    }
}

