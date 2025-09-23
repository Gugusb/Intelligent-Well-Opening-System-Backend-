package com.gugusb.hwics.smartmonitor2.config;

import java.util.concurrent.TimeUnit;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
@ConfigurationProperties(prefix = "system")
public class SystemConfiguration {

    // 自检参数
    private double selfCheckThreshold;

    // 状态保存参数
    private int statusSaveIntervalMinutes;

    // 工艺参数
    private Process process = new Process();

    // 日志参数
    private Logging logging = new Logging();

    // Kepserver 参数
    private Kepserver kepserver = new Kepserver();

    // 标签配置
    private Tags tags = new Tags();

    // 转换后的时间单位（秒）
    private long foamingPostWaitSeconds;
    private long swabbingPostWaitSeconds;
    private long gasLiftCooldownSeconds;
    private long foamingCooldownSeconds;
    private long heartbeatLogIntervalSeconds;
    private long statusSaveIntervalSeconds;
    private long foamingDefaultInjectionTimeSeconds;
    private long gasLiftDefaultInjectionTimeSeconds;

    @PostConstruct
    public void init() {
        // 将时间单位转换为秒，便于内部使用
        this.foamingPostWaitSeconds = TimeUnit.MINUTES.toSeconds(this.process.getFoaming().getPostWaitMinutes());
        this.swabbingPostWaitSeconds = TimeUnit.MINUTES.toSeconds(this.process.getSwabbing().getPostWaitMinutes());
        this.gasLiftCooldownSeconds = TimeUnit.HOURS.toSeconds(this.process.getGasLift().getCooldownHours());
        this.foamingCooldownSeconds = TimeUnit.HOURS.toSeconds(this.process.getFoaming().getCooldownHours());
        this.heartbeatLogIntervalSeconds = TimeUnit.HOURS.toSeconds(this.logging.getHeartbeat().getIntervalHours());
        this.statusSaveIntervalSeconds = TimeUnit.MINUTES.toSeconds(this.statusSaveIntervalMinutes);
        this.foamingDefaultInjectionTimeSeconds = TimeUnit.MINUTES.toSeconds(this.process.getFoaming().getDefaultInjectionTimeMinutes());
        this.gasLiftDefaultInjectionTimeSeconds = TimeUnit.MINUTES.toSeconds(this.process.getGasLift().getDefaultInjectionTimeMinutes());
    }

    // 内部类定义
    public static class Process {
        private Foaming foaming = new Foaming();
        private Swabbing swabbing = new Swabbing();
        private GasLift gasLift = new GasLift();

        // Getter 和 Setter
        public Foaming getFoaming() { return foaming; }
        public void setFoaming(Foaming foaming) { this.foaming = foaming; }
        public Swabbing getSwabbing() { return swabbing; }
        public void setSwabbing(Swabbing swabbing) { this.swabbing = swabbing; }
        public GasLift getGasLift() { return gasLift; }
        public void setGasLift(GasLift gasLift) { this.gasLift = gasLift; }
    }

    public static class Foaming {
        private int postWaitMinutes;
        private int cooldownHours;
        private double defaultInjectionRate;
        private int defaultInjectionTimeMinutes;

        // Getter 和 Setter
        public int getPostWaitMinutes() { return postWaitMinutes; }
        public void setPostWaitMinutes(int postWaitMinutes) { this.postWaitMinutes = postWaitMinutes; }
        public int getCooldownHours() { return cooldownHours; }
        public void setCooldownHours(int cooldownHours) { this.cooldownHours = cooldownHours; }
        public double getDefaultInjectionRate() { return defaultInjectionRate; }
        public void setDefaultInjectionRate(double defaultInjectionRate) { this.defaultInjectionRate = defaultInjectionRate; }
        public int getDefaultInjectionTimeMinutes() { return defaultInjectionTimeMinutes; }
        public void setDefaultInjectionTimeMinutes(int defaultInjectionTimeMinutes) { this.defaultInjectionTimeMinutes = defaultInjectionTimeMinutes; }
    }

    public static class Swabbing {
        private int postWaitMinutes;

        // Getter 和 Setter
        public int getPostWaitMinutes() { return postWaitMinutes; }
        public void setPostWaitMinutes(int postWaitMinutes) { this.postWaitMinutes = postWaitMinutes; }
    }

    public static class GasLift {
        private int cooldownHours;
        private double defaultInjectionRate;
        private int defaultInjectionTimeMinutes;

        // Getter 和 Setter
        public int getCooldownHours() { return cooldownHours; }
        public void setCooldownHours(int cooldownHours) { this.cooldownHours = cooldownHours; }
        public double getDefaultInjectionRate() { return defaultInjectionRate; }
        public void setDefaultInjectionRate(double defaultInjectionRate) { this.defaultInjectionRate = defaultInjectionRate; }
        public int getDefaultInjectionTimeMinutes() { return defaultInjectionTimeMinutes; }
        public void setDefaultInjectionTimeMinutes(int defaultInjectionTimeMinutes) { this.defaultInjectionTimeMinutes = defaultInjectionTimeMinutes; }
    }

    public static class Logging {
        private Heartbeat heartbeat = new Heartbeat();

        // Getter 和 Setter
        public Heartbeat getHeartbeat() { return heartbeat; }
        public void setHeartbeat(Heartbeat heartbeat) { this.heartbeat = heartbeat; }
    }

    public static class Heartbeat {
        private int intervalHours;

        // Getter 和 Setter
        public int getIntervalHours() { return intervalHours; }
        public void setIntervalHours(int intervalHours) { this.intervalHours = intervalHours; }
    }

    public static class Kepserver {
        private String host;
        private int port;
        private int connectionTimeout;
        private int readTimeout;

        // Getter 和 Setter
        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
        public int getConnectionTimeout() { return connectionTimeout; }
        public void setConnectionTimeout(int connectionTimeout) { this.connectionTimeout = connectionTimeout; }
        public int getReadTimeout() { return readTimeout; }
        public void setReadTimeout(int readTimeout) { this.readTimeout = readTimeout; }
    }

    public static class Tags {
        private List<Map<String, String>> units;
        private List<Map<String, String>> production;

        // Getter 和 Setter
        public List<Map<String, String>> getUnits() { return units; }
        public void setUnits(List<Map<String, String>> units) { this.units = units; }
        public List<Map<String, String>> getProduction() { return production; }
        public void setProduction(List<Map<String, String>> production) { this.production = production; }
    }

    // Getter 和 Setter 方法
    public double getSelfCheckThreshold() { return selfCheckThreshold; }
    public void setSelfCheckThreshold(double selfCheckThreshold) { this.selfCheckThreshold = selfCheckThreshold; }

    public int getStatusSaveIntervalMinutes() { return statusSaveIntervalMinutes; }
    public void setStatusSaveIntervalMinutes(int statusSaveIntervalMinutes) { this.statusSaveIntervalMinutes = statusSaveIntervalMinutes; }

    public Process getProcess() { return process; }
    public void setProcess(Process process) { this.process = process; }

    public Logging getLogging() { return logging; }
    public void setLogging(Logging logging) { this.logging = logging; }

    public Kepserver getKepserver() { return kepserver; }
    public void setKepserver(Kepserver kepserver) { this.kepserver = kepserver; }

    public Tags getTags() { return tags; }
    public void setTags(Tags tags) { this.tags = tags; }

    // 转换后的时间单位获取方法
    public long getFoamingPostWaitSeconds() { return foamingPostWaitSeconds; }
    public long getSwabbingPostWaitSeconds() { return swabbingPostWaitSeconds; }
    public long getGasLiftCooldownSeconds() { return gasLiftCooldownSeconds; }
    public long getFoamingCooldownSeconds() { return foamingCooldownSeconds; }
    public long getHeartbeatLogIntervalSeconds() { return heartbeatLogIntervalSeconds; }
    public long getStatusSaveIntervalSeconds() { return statusSaveIntervalSeconds; }
    public long getFoamingDefaultInjectionTimeSeconds() { return foamingDefaultInjectionTimeSeconds; }
    public long getGasLiftDefaultInjectionTimeSeconds() { return gasLiftDefaultInjectionTimeSeconds; }
}
