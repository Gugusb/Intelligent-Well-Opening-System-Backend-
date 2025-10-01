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
        return new PumpParams(4, 200000 * newD, 0.2 * newD, 4, 100000);
    }

    /**
     * 根据井参数和生产快照计算泡排参数
     */
    public static FoamParams computeFoamParams(WellParams wellParams, ProductionSnapshot snapshot) {
        //启动压力
        double startPress = 2.07;
        //积液量计算
        double liquidLoadHeight = Math.abs(snapshot.getWellheadPressure()-snapshot.getCasingPressure()) / 0.01 / 1;
        double liquidLoadVolume = liquidLoadHeight * 0.25 * Math.PI * Math.pow(wellParams.getTubingDiameter(),2);
        //泡排剂加注量(单位升)
        double FoamInjectionVolume = 2000 * liquidLoadVolume * 0.05 / (1 - 0.05 - 0.05 * 4);

        //加注速率 已知 升/小时
        double FoamInjectionRate = 9;
        //加注时间 单位小时
        double FoamInjectionTime = FoamInjectionVolume / FoamInjectionRate;

        //默认参数计算
        //积液量计算
        double liquidLoadHeightDef = Math.abs(5.12 - 0.4) / 0.01 / 1;
        double liquidLoadVolumeDef = liquidLoadHeightDef * 0.25 * Math.PI * Math.pow(wellParams.getTubingDiameter(),2);
        //泡排剂加注量(单位升)
        double FoamInjectionVolumeDef = 2000 * liquidLoadVolumeDef * 0.05 / (1 - 0.05 - 0.05 * 4);

        //加注速率 已知 升/小时
        double FoamInjectionRateDef = 60 * 10;
        //加注时间 单位小时
        double FoamInjectionTimeDef = (double) 1 / 3;

        return new FoamParams(liquidLoadVolume, FoamInjectionVolume, FoamInjectionRate, FoamInjectionTime, startPress, FoamInjectionRateDef, FoamInjectionTimeDef);
    }

    /**
     * 根据井参数和生产快照计算气举参数
     */
    public static GasLiftParams computeGasLiftParams(WellParams wellParams, ProductionSnapshot snapshot) {

        //启动压力
        double startPress = 0.01 * wellParams.getVerticalDepth() + 0.3;

        //注气量 单位万方
        double gasInjectionVolume = 0.2726 * Math.exp(0.1938 * snapshot.getLiquidRate()) * 10000;
        //注气速率 方每天
        double gasInjectionRate = 2000 / 24;
        //注气时间 小时
        double gasInjectionTime = gasInjectionVolume / gasInjectionRate;
        //注气压力 MPa，问甲方再调整
        double gasInjectionPress = 2;

        return new GasLiftParams(gasInjectionVolume, gasInjectionRate, gasInjectionTime, startPress, gasInjectionRate, 24);
    }

}
