package com.gugusb.hwics.smartmonitor2;

import com.gugusb.hwics.smartmonitor2.entity.FoamParams;
import com.gugusb.hwics.smartmonitor2.entity.GasLiftParams;
import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;
import com.gugusb.hwics.smartmonitor2.entity.WellParams;

/**
 * ===============================
 * 工艺参数计算管理器
 * 用于根据井参数和生产快照计算
 * 泡排 / 气举 的工艺参数
 * ===============================
 */
public class ParamManager {

    /**
     * 根据井参数和生产快照计算泡排参数
     */
    public static FoamParams computeFoamParams(WellParams wellParams, ProductionSnapshot snapshot) {
        // 简化计算逻辑（可替换为真实工艺公式）

        // 假设：积液量 ~ 水平段长度 * 管径^2 * 系数
        double liquidVolume = wellParams.getHorizontalLength() *
                Math.pow(wellParams.getTubingDiameter() / 1000, 2) * 0.8;

        // 加注量：按积液量的 20%
        double injectionVolume = liquidVolume * 0.2;

        // 加注速率：固定速率（例如 0.5 m³/min）
        double injectionRate = 0.5;

        // 加注时间 = 加注量 / 加注速率
        double injectionTime = injectionVolume / injectionRate;

        return new FoamParams(liquidVolume, injectionVolume, injectionRate, injectionTime);
    }

    /**
     * 提供一套默认泡排参数
     */
    public static FoamParams defaultFoamParams() {
        // 默认参数，保证系统能运行
        double defaultLiquidVolume = 5.0;   // m³
        double defaultInjectionVolume = 1.0; // m³
        double defaultInjectionRate = 0.5;   // m³/min
        double defaultInjectionTime = defaultInjectionVolume / defaultInjectionRate;

        return new FoamParams(defaultLiquidVolume, defaultInjectionVolume, defaultInjectionRate, defaultInjectionTime);
    }

    /**
     * 根据井参数和生产快照计算气举参数
     */
    public static GasLiftParams computeGasLiftParams(WellParams wellParams, ProductionSnapshot snapshot) {
        // 简化计算逻辑（可替换为真实工艺公式）

        // 注气量：与日产气量成正比，乘一个系数
        double gasInjectionVolume = snapshot.getGasRate() * 0.1;

        // 注气速率：固定值
        double gasInjectionRate = 10.0; // m³/min

        // 注气时间 = 注气量 / 注气速率
        double gasInjectionTime = gasInjectionVolume / gasInjectionRate;

        return new GasLiftParams(gasInjectionVolume, gasInjectionRate, gasInjectionTime);
    }

    /**
     * 提供一套默认气举参数
     */
    public static GasLiftParams defaultGasLiftParams() {
        double defaultVolume = 100.0; // m³
        double defaultRate = 10.0;    // m³/min
        double defaultTime = defaultVolume / defaultRate;

        return new GasLiftParams(defaultVolume, defaultRate, defaultTime);
    }
}
