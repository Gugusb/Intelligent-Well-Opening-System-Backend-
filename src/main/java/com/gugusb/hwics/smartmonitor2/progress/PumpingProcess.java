package com.gugusb.hwics.smartmonitor2.progress;

import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;

public class PumpingProcess extends BaseProcess {

    @Override
    public void startWithParams(Object params) {
        // 抽汲工艺无条件启动，直接启动机组
        System.out.println("[抽汲] 启动机组");
        running = true;
    }

    @Override
    public void stop() {
        // 抽汲工艺不会主动停止，一直覆盖生命周期
        System.out.println("[抽汲] 工艺持续运行，不会停止");
    }

    @Override
    public boolean canStart(ProductionSnapshot snapshot) {
        // 抽汲工艺无条件启动，始终返回 true
        return true;
    }
}
