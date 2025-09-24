package com.gugusb.hwics.smartmonitor2.progress;

import com.gugusb.hwics.smartmonitor2.entity.FoamParams;
import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;

public class FoamProcess{
    protected boolean running = false;
    protected boolean inCooldown = false;

    public void startWithParams(FoamParams params) {
        // TODO: 调用 Kepserver 写入逻辑，开启泡排设备
        System.out.println("[泡排] 开启工艺，使用参数: " + params);
        running = true;
    }

    public void stop() {
        // TODO: 调用 Kepserver 写入逻辑，关闭泡排设备
        System.out.println("[泡排] 工艺停止");
        running = false;
    }

    public boolean canStart(ProductionSnapshot snapshot, FoamParams foamParams) {
        // 条件 P1 的判断逻辑，这里暂时用伪代码
        return true; // 举例：液量低于某阈值触发
    }
}

