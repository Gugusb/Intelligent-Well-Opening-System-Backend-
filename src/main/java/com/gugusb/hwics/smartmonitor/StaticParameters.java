package com.gugusb.hwics.smartmonitor;

import com.gugusb.hwics.utils.StageKey;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Configuration(proxyBeanMethods = false)
public class StaticParameters {

    // =======================试用期(s)=======================
    // 试用期结束之前不会进行任何条件判断 不会切换到下一个阶段
    public static Map<StageKey, Integer> STAGE_PROBATION = new HashMap<StageKey, Integer>() {{
        //0系统未开启；1系统刚开启/刚开井；2抽汲系统；3气举系统;4泡排系统
        //0启动条件判断中，设备未启动；1符合启动条件，已经启动，转换条件判断中；2符合结束条件，设备已经关闭，等待下一步操作
        put(new StageKey(0, 0), 0);
        put(new StageKey(0, 1), 0);
        put(new StageKey(0, 2), 0);

        put(new StageKey(1, 0), 0);
        put(new StageKey(1, 1), 10);
        put(new StageKey(1, 2), 0);

        put(new StageKey(2, 0), 10);
        put(new StageKey(2, 1), 30 * 60);
        put(new StageKey(2, 2), 0);

        put(new StageKey(3, 0), 10);
        put(new StageKey(3, 1), 1 * 60 * 60);
        put(new StageKey(3, 2), 1 * 24 * 60 * 60);

        put(new StageKey(4, 0), 10);
        put(new StageKey(4, 1), 10);
        put(new StageKey(4, 2), 1 * 24 * 60 * 60);
    }};

    // =======================更新进度槽最大值(*)=======================
    // 试用期结束后 进行条件判断 若符合条件则累计进度槽 进度槽满后出发阶段转换
    public static Map<StageKey, List<Integer>> STAGE_MAX_PROGRESS_BAR = new HashMap<StageKey, List<Integer>>() {{
        //0系统未开启；1系统刚开启/刚开井；2抽汲系统；3气举系统;4泡排系统
        //0启动条件判断中，设备未启动；1符合启动条件，已经启动，转换条件判断中；2符合结束条件，设备已经关闭，等待下一步操作
        put(new StageKey(0, 0), List.of(5));
        put(new StageKey(0, 1), List.of(5));
        put(new StageKey(0, 2), List.of(5));

        put(new StageKey(1, 0), List.of(5));
        put(new StageKey(1, 1), List.of(5));
        put(new StageKey(1, 2), List.of(5));

        put(new StageKey(2, 0), List.of(5));
        put(new StageKey(2, 1), List.of(10 * 60));
        put(new StageKey(2, 2), List.of(5));

        put(new StageKey(3, 0), List.of(5));
        //非特殊的：气举系统默认开启3天
        //特殊的：发现井口油压在连续的10分钟内依然小于0.2MPa，认为气举无效或检查气举设备
        //特殊的：若当气举开启2天后，检测到10小时内井口油压几乎保持不变（波动幅度最大1MPa），则认为井底积液已经被有效排出，结束气举系统
        put(new StageKey(3, 1), List.of(3 * 24 * 60 * 60, 10 * 60, 2 * 24 * 60 * 60));
        put(new StageKey(3, 2), List.of(10 * 60));

        put(new StageKey(4, 0), List.of(10));
        put(new StageKey(4, 1), List.of(10));
        put(new StageKey(4, 2), List.of(30 * 60));
    }};

    // =======================气举=======================
    // 气举结束最大油压波动幅度
    public static final Float MAX_OIL_PRESSURE_FLUCTUATION = 0.5f;
    public static final Float MAX_LIQUID_HOLDUP = 0.5f;

    // =======================泡排=======================
    // 最大泡排注入次数
    public static final Integer MAX_FOAM_TIMES = 3;


}

