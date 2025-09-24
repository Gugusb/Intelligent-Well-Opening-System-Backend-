package com.gugusb.hwics.smartmonitor2;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import com.gugusb.hwics.smartmonitor2.entity.ProductionSnapshot;
import com.gugusb.hwics.utils.DateSpawner;

/**
 * ===============================
 * DataReader
 * -------------------------------
 * 功能：
 *  - 从现场（例如 KepServer 或数据库）读取实时数据
 *  - 提供设备状态读取
 *  - 提供生产参数读取
 *  - 提供数据可读性检查（用于开机前自检）
 *
 * 注意：
 *  - 这里是骨架实现，真实逻辑需替换为对 KepServer / OPC-UA / Modbus 等的访问
 * ===============================
 */
public class DataReader {

    private Random random = new Random();

    /**
     * 模拟读取现场的生产参数
     */
    public static ProductionSnapshot readProductionSnapshot() {
        // 在真实实现中，这里应该调用 KepServer API 获取数据
        Random random = new Random();
        LocalDateTime time = DateSpawner.getLocalTime();
        double gasRate = 1000 + random.nextDouble() * 100;  // 日产气量 m³/d
        double liquidRate = 50 + random.nextDouble() * 10;  // 日产液量 m³/d
        double wellheadPressure = 5 + random.nextDouble();  // 井口油压 MPa
        double casingPressure = 3 + random.nextDouble();    // 井口套压 MPa

        return new ProductionSnapshot(time, gasRate, liquidRate, wellheadPressure, casingPressure, UnitStatus());
    }

    /**
     * 模拟读取机组运行状态
     * 例如 6 个机组的开关状态
     */
    public static boolean[] UnitStatus() {
        Random random = new Random();
        boolean[] unitStatus = new boolean[6];
        for (int i = 0; i < 6; i++) {
            unitStatus[i] = random.nextBoolean();
        }
        return unitStatus;
    }

    /**
     * 自检逻辑：检查采集数据的可用比例
     * @return 可用数据比例（0~1）
     */
    public double checkDataReadability() {
        // 模拟读取 100 个点，随机部分失效
        int total = 100;
        int available = 0;
        for (int i = 0; i < total; i++) {
            if (random.nextDouble() > 0.05) { // 95% 概率可读
                available++;
            }
        }
        return (double) available / total;
    }
}

