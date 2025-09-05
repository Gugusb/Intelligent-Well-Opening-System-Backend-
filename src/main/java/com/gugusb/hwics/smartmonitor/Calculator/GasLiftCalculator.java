package com.gugusb.hwics.smartmonitor.Calculator;

import com.gugusb.hwics.pojo.GasLiftSystem;
import com.gugusb.hwics.pojo.ProductionParameter;

public class GasLiftCalculator {
    private GasLiftCalculator(){

    }
    // 启动气举需要的压差(MPa)
    private static final Integer GASLIFT_STARTUP_PRESSURE_DIFFERENCE = 10;
    // 满足气举启动压差的持续时间(s)
    private static final Integer GASLIFT_STARTUP_KEEP_TIME = 5 * 60;
    // 气举冷静期(s)
    private static final Integer GASLIFT_COLD_TIME = 10 * 60;
    // 气举标准持续时间(s)
    private static final Integer GASLIFT_DURATION = 3 * 60 * 60 * 24;

    public static Integer getStartupPressureDifference(GasLiftSystem gasLiftSystem, ProductionParameter productionParameter) {
        return GASLIFT_STARTUP_PRESSURE_DIFFERENCE;
    }

    public static Integer getStartupKeepTime(GasLiftSystem gasLiftSystem, ProductionParameter productionParameter) {
        return GASLIFT_STARTUP_KEEP_TIME;
    }

    public static Integer getDuration(GasLiftSystem gasLiftSystem, ProductionParameter productionParameter) {
        return GASLIFT_DURATION;
    }

}
