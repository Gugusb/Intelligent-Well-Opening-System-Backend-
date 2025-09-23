package com.gugusb.hwics.smartmonitor2.progress;

import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;

public abstract class BaseProcess {

    protected boolean running = false;
    protected boolean inCooldown = false;

    /**
     * 启动工艺（带参数）
     */
    public abstract void startWithParams(Object params);

    /**
     * 停止工艺
     */
    public abstract void stop();

    /**
     * 判断是否满足启动条件（由子类实现）
     */
    public abstract boolean canStart(ProductionSnapshot snapshot);

    /**
     * 工艺是否正在运行
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * 工艺是否处于冷静期
     */
    public boolean isInCooldown() {
        return inCooldown;
    }

    /**
     * 设置冷静期标志位
     */
    public void setCooldown(boolean cooldown) {
        this.inCooldown = cooldown;
    }
}

