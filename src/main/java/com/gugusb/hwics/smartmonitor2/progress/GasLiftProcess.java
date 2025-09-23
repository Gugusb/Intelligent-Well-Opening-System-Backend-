package com.gugusb.hwics.smartmonitor2.progress;

import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;

public class GasLiftProcess extends BaseProcess {

    @Override
    public void startWithParams(Object params) {
        // TODO: 调用 Kepserver 写入逻辑，开启气举设备
        System.out.println("[气举] 开启工艺，使用参数: " + params);
        running = true;
    }

    @Override
    public void stop() {
        // TODO: 调用 Kepserver 写入逻辑，关闭气举设备
        System.out.println("[气举] 工艺停止");
        running = false;
    }

    @Override
    public boolean canStart(ProductionSnapshot snapshot) {
        // 条件 GL1 的判断逻辑，这里暂时用伪代码
        return true; // 举例：井口压力超过某阈值触发
    }
}
