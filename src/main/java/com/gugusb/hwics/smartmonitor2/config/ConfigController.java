package com.gugusb.hwics.smartmonitor2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private SystemConfiguration systemConfig;

    /**
     * 获取所有配置参数
     */
    @GetMapping("/all")
    public Map<String, Object> getAllConfig() {
        Map<String, Object> configMap = new HashMap<>();

        // 系统配置
        configMap.put("selfCheckThreshold", systemConfig.getSelfCheckThreshold());
        configMap.put("statusSaveIntervalSeconds", systemConfig.getStatusSaveIntervalSeconds());

        // 工艺配置 - 泡排
        configMap.put("foamingPostWaitSeconds", systemConfig.getFoamingPostWaitSeconds());
        configMap.put("foamingCooldownSeconds", systemConfig.getFoamingCooldownSeconds());
        configMap.put("foamingDefaultInjectionRate", systemConfig.getProcess().getFoaming().getDefaultInjectionRate());
        configMap.put("foamingDefaultInjectionTimeSeconds", systemConfig.getFoamingDefaultInjectionTimeSeconds());

        // 工艺配置 - 抽汲
        configMap.put("swabbingPostWaitSeconds", systemConfig.getSwabbingPostWaitSeconds());

        // 工艺配置 - 气举
        configMap.put("gasLiftCooldownSeconds", systemConfig.getGasLiftCooldownSeconds());
        configMap.put("gasLiftDefaultInjectionRate", systemConfig.getProcess().getGasLift().getDefaultInjectionRate());
        configMap.put("gasLiftDefaultInjectionTimeSeconds", systemConfig.getGasLiftDefaultInjectionTimeSeconds());

        // 日志配置
        configMap.put("heartbeatLogIntervalSeconds", systemConfig.getHeartbeatLogIntervalSeconds());

        return configMap;
    }

    /**
     * 获取特定工艺的配置参数
     */
    @GetMapping("/process/{processType}")
    public Map<String, Object> getProcessConfig(String processType) {
        Map<String, Object> processConfig = new HashMap<>();

        switch (processType.toLowerCase()) {
            case "foaming":
                processConfig.put("postWaitSeconds", systemConfig.getFoamingPostWaitSeconds());
                processConfig.put("cooldownSeconds", systemConfig.getFoamingCooldownSeconds());
                processConfig.put("defaultInjectionRate", systemConfig.getProcess().getFoaming().getDefaultInjectionRate());
                processConfig.put("defaultInjectionTimeSeconds", systemConfig.getFoamingDefaultInjectionTimeSeconds());
                break;

            case "gaslift":
                processConfig.put("cooldownSeconds", systemConfig.getGasLiftCooldownSeconds());
                processConfig.put("defaultInjectionRate", systemConfig.getProcess().getGasLift().getDefaultInjectionRate());
                processConfig.put("defaultInjectionTimeSeconds", systemConfig.getGasLiftDefaultInjectionTimeSeconds());
                break;

            case "swabbing":
                processConfig.put("postWaitSeconds", systemConfig.getSwabbingPostWaitSeconds());
                break;

            default:
                processConfig.put("error", "未知的工艺类型: " + processType);
        }

        return processConfig;
    }

    /**
     * 获取系统自检阈值
     */
    @GetMapping("/self-check-threshold")
    public double getSelfCheckThreshold() {
        return systemConfig.getSelfCheckThreshold();
    }

    /**
     * 获取状态保存间隔
     */
    @GetMapping("/status-save-interval")
    public Map<String, Object> getStatusSaveInterval() {
        Map<String, Object> result = new HashMap<>();
        result.put("seconds", systemConfig.getStatusSaveIntervalSeconds());
        result.put("minutes", systemConfig.getStatusSaveIntervalSeconds() / 60);
        return result;
    }

    /**
     * 获取心跳日志间隔
     */
    @GetMapping("/heartbeat-interval")
    public Map<String, Object> getHeartbeatInterval() {
        Map<String, Object> result = new HashMap<>();
        result.put("seconds", systemConfig.getHeartbeatLogIntervalSeconds());
        result.put("hours", systemConfig.getHeartbeatLogIntervalSeconds() / 3600);
        return result;
    }
}
