package com.gugusb.hwics.smartmonitor2.progress;

import com.gugusb.hwics.smartmonitor2.entity.GasLiftParams;
import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;

public class GasLiftProcess{
    protected boolean running = false;
    protected boolean inCooldown = false;
    public void startWithParams(GasLiftParams params) {
        // TODO: 调用 Kepserver 写入逻辑，开启气举设备
        System.out.println("[气举] 开启工艺，使用参数: " + params);
        running = true;
    }


    public void stop() {
        // TODO: 调用 Kepserver 写入逻辑，关闭气举设备
        System.out.println("[气举] 工艺停止");
        running = false;
    }

    public boolean canStart(ProductionSnapshot snapshot, GasLiftParams gasLiftParams) {
        // 条件 GL1 的判断逻辑，这里暂时用伪代码
        return true; // 举例：井口压力超过某阈值触发
    }
}
