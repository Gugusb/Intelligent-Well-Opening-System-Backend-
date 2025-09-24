package com.gugusb.hwics.smartmonitor2;

import com.gugusb.hwics.smartmonitor2.entity.*;

import java.util.Random;

/**
 * ===============================
 * 工艺参数计算管理器
 * 用于根据井参数和生产快照计算
 * 泡排 / 气举 的工艺参数
 * ===============================
 */
public class ParamManager {

    /**
     * 根据井参数和生产快照计算参数
     */
    public static PumpParams computePumpParams(WellParams wellParams, ProductionSnapshot snapshot) {
        // 简化计算逻辑（可替换为真实工艺公式）
        Random random = new Random();
        double newD = random.nextDouble();
        return new PumpParams(2 * newD, 200000 * newD, 0.2 * newD, 4, 100000);
    }

    /**
     * 根据井参数和生产快照计算泡排参数
     */
    public static FoamParams computeFoamParams(WellParams wellParams, ProductionSnapshot snapshot) {
        // 简化计算逻辑（可替换为真实工艺公式）
        Random random = new Random();
        double newD = random.nextDouble();
        return new FoamParams(100 * newD, 100 * newD, 100 * newD, 66666 + 100 * 100 * newD, 100 * newD, 100, 100);
    }

    /**
     * 根据井参数和生产快照计算气举参数
     */
    public static GasLiftParams computeGasLiftParams(WellParams wellParams, ProductionSnapshot snapshot) {
        // 简化计算逻辑（可替换为真实工艺公式）
        Random random = new Random();
        double newD = random.nextDouble();
        return new GasLiftParams(100 * newD, 100 * newD, 66666 + 100 * 100 * newD, 100 * newD, 100, 100);
    }

}
