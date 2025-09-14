package com.gugusb.hwics.smartmonitor.Calculator;

import com.gugusb.hwics.pojo.FoamDrainageSystem;
import com.gugusb.hwics.pojo.GasLiftSystem;
import com.gugusb.hwics.pojo.ProductionParameter;
import com.gugusb.hwics.pojo.SuctionMixedFlowSystem;

public class FoamDrainageCalculator {
    private FoamDrainageCalculator() {}
    // 启动泡排需要的压差(MPa)
    private static final Integer STARTUP_PRESSURE_DIFFERENCE = 10;
    // 满足泡排启动压差的持续时间(s)
    private static final Integer STARTUP_KEEP_TIME = 5 * 60;
    // 泡排冷静期(s)
    private static final Integer COLD_TIME = 10 * 60;
    // 泡排标准排量(m³)
    private static final Integer STANDARD_DISPLACEMENT = 1000;

    public static Integer getDisplacement(FoamDrainageSystem gasLiftSystem, ProductionParameter productionParameter) {
        return STARTUP_PRESSURE_DIFFERENCE;
    }

    public static Integer getStartupPressureDifference(FoamDrainageSystem gasLiftSystem, ProductionParameter productionParameter) {
        return STARTUP_PRESSURE_DIFFERENCE;
    }

    public static Integer getStartupKeepTime(FoamDrainageSystem gasLiftSystem, ProductionParameter productionParameter) {
        return STARTUP_KEEP_TIME;
    }

}
