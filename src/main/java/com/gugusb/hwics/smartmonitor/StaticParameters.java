package com.gugusb.hwics.smartmonitor;

public final class StaticParameters {
    // 私有构造函数防止实例化
    private StaticParameters() {
        throw new AssertionError("不能实例化静态参数类");
    }

    // =======================气举=======================
    // 启动气举需要的压差
    public static final Integer GASLIFT_STARTUP_PRESSURE_DIFFERENCE = 10;
    // 满足气举启动压差的持续时间（s）
    public static final Integer GASLIFT_STARTUP_KEEP_TIME = 10;
    // 气举冷静期（s）

    // =======================气举=======================
    // 启动气举需要的压差


}
