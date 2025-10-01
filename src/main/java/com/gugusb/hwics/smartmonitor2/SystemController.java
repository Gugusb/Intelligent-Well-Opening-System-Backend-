package com.gugusb.hwics.smartmonitor2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gugusb.hwics.logger.entity.LogEntry;
import com.gugusb.hwics.logger.mapper.LogRepository;
import com.gugusb.hwics.mapper.DFP1Mapper;
import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.dto.UserDTO;
import com.gugusb.hwics.smartmonitor2.entity.GasLiftParams;
import com.gugusb.hwics.smartmonitor2.entity.PumpParams;
import com.gugusb.hwics.smartmonitor2.entity.WellParams;
import com.gugusb.hwics.smartmonitor2.entity.processstate.FoamProgressState;
import com.gugusb.hwics.smartmonitor2.entity.processstate.GasLiftProgressState;
import com.gugusb.hwics.smartmonitor2.entity.processstate.PumpProgressState;
import com.gugusb.hwics.smartmonitor2.mapper.FoamPSMapper;
import com.gugusb.hwics.smartmonitor2.mapper.GasLiftPSMapper;
import com.gugusb.hwics.smartmonitor2.mapper.PumpPSMapper;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/smart")
public class SystemController {
    @Autowired
    ProcessController processController;
    @Autowired
    DFP1Mapper dfp1Mapper;
    @Autowired
    FoamPSMapper foamPSMapper;
    @Autowired
    GasLiftPSMapper gasLiftPSMapper;
    @Autowired
    PumpPSMapper pumpPSMapper;
    @Autowired
    LogRepository logRepository;

    @PostMapping("/gl-model-open")
    public MessageRespnser<Boolean> setGasLiftModelOpen(){
        return MessageRespnser.success(processController.setIsOpenGasLiftModel(true));
    }

    @PostMapping("/gl-model-close")
    public MessageRespnser<Boolean> setGasLiftModelClose(){
        return MessageRespnser.success(processController.setIsOpenGasLiftModel(false));
    }

    @GetMapping("/gl-model")
    public MessageRespnser<Boolean> getGasLiftModel(){
        return MessageRespnser.success(processController.getIsOpenGasLiftModel());
    }

    @PostMapping("/system-reset-process")
    public MessageRespnser<String> resetProcess() {
        processController.restartAllProcess();
        return MessageRespnser.success("重置所有进程");
    }

    @PostMapping("/system-start-check")
    public MessageRespnser<Map<String, Boolean>> startSystemCheck() {
        boolean res = processController.startSystemCheck();
        Map<String, Boolean> map = new HashMap<>();
        if(res){
            map.put("gaslift", true);
            map.put("pump", true);
            map.put("poam", true);
            map.put("llj", true);
            return MessageRespnser.success(map);
        }else{
            map.put("gaslift", false);
            map.put("pump", false);
            map.put("poam", false);
            map.put("llj", false);
            return MessageRespnser.success(map);
        }

    }

    @PostMapping("/system-start-process")
    public MessageRespnser<String> startSystemProcess(@RequestBody WellParams wellParams) {
        if(processController.getStartTag() == 0)
            processController.startSystemProcess(wellParams);
        else
            processController.editParams(wellParams);
        return MessageRespnser.success("智能系统成功开启");
    }

    @PostMapping("/edit-params")
    public MessageRespnser<String> editPms(@RequestBody WellParams wellParams) {
        if(processController.editParams(wellParams))
            return MessageRespnser.success("气井参数更新成功");
        return MessageRespnser.unsuccess("气井参数更新失败");
    }

    @PostMapping("/edit-craft")
    public MessageRespnser<String> editCraft() {
        return MessageRespnser.success("更新成功");
    }

    @PostMapping("/close-system")
    public MessageRespnser<String> closeSystem(){
        if(processController.isRunning()){
            if(processController.closeSystem())
                return MessageRespnser.success("系统关闭成功！");
        }
        return MessageRespnser.unsuccess("系统未开启，关闭失败");
    }

    @GetMapping("/get-crew-snap")
    public MessageRespnser<Map<String, Float>> getCrewAndSnap(){
        Map<String, Float> map = new HashMap<>();
        DFP1 dfp1 = dfp1Mapper.findFirstByOrderByDataIdDesc().get();
        if(dfp1.getUnit1RunStatus() == null || !dfp1.getUnit1RunStatus())map.put("u1", 0f);
        else map.put("u1", 1f);
        if(dfp1.getUnit2RunStatus() == null || !dfp1.getUnit2RunStatus())map.put("u2", 0f);
        else map.put("u2", 1f);
        if(dfp1.getUnit3RunStatus() == null || !dfp1.getUnit3RunStatus())map.put("u3", 0f);
        else map.put("u3", 1f);
        if(dfp1.getUnit4RunStatus() == null || !dfp1.getUnit4RunStatus())map.put("u4", 0f);
        else map.put("u4", 1f);
        if(dfp1.getUnit5RunStatus() == null || !dfp1.getUnit5RunStatus())map.put("u5", 0f);
        else map.put("u5", 1f);
        if(dfp1.getUnit6RunStatus() == null || !dfp1.getUnit6RunStatus())map.put("u6", 0f);
        else map.put("u6", 1f);
        if(dfp1.getFlowmeterInstantGasFlow() == null)map.put("gas_flow", 0f);
        else map.put("gas_flow", dfp1.getFlowmeterInstantGasFlow());
        if(dfp1.getFlowmeterInstantWaterFlow() == null)map.put("water_flow", 0f);
        else map.put("water_flow", dfp1.getFlowmeterInstantWaterFlow());
        if(dfp1.getFrontPressure1() == null)map.put("tubing_pressure", 0f);
        else map.put("tubing_pressure", (float)dfp1.getFrontPressure1());
        if(dfp1.getGasliftBackPressure6() == null)map.put("casing_pressure", 0f);
        else map.put("casing_pressure", (float)dfp1.getGasliftBackPressure6());
        return MessageRespnser.success(map);
    }

    @GetMapping("/get-gas-lift-state")
    public MessageRespnser<Map<String, Object>> getGasLiftState(){
        Map<String, Object> map = new HashMap<>();
        GasLiftProgressState state = gasLiftPSMapper.findFirstByOrderByDataIdDesc().get();
        if(state.isRunning())
            map.put("running", (double)1);
        else map.put("running", (double) 0);
        // 工艺参数
        map.put("start_pressure", (double) 0);
        map.put("inj_volume", (double) 0);
        map.put("inj_rate", (double) 0);
        map.put("inj_time", (double) 0);
        map.put("inj_rate_default", (double) 0);
        map.put("inj_time_default", (double) 0);
        map.put("last_time", "-");
        map.put("total_duration", "-");
        map.put("kept_duration", "-");
        map.put("progress", 0);
        map.put("gl_model_open", 0);

        // 气举模式开关
        //if(processController.getIsOpenGasLiftModel()) map.put("gl_model_open", 1);
        Optional<DFP1> dfp1 = dfp1Mapper.findFirstByOrderByDataIdDesc();
        if(dfp1 != null && dfp1.get()!=null && dfp1.get().getCurrentMode() !=null && dfp1.get().getCurrentMode())map.put("gl_model_open", 1);
        // 上次启动时间
        ProgressInfo timeInfo = calculateProgress(state.getLastStartTime(), Duration.ofSeconds(state.getLastDurationSecond()));
        if(state.getLastStartTime() != null)
            map.put("last_time", state.getLastStartTime().toString());
        if(state.isRunning()){
            map.put("total_duration", timeInfo.totalTime);
            map.put("kept_duration", timeInfo.elapsedTime);
            map.put("progress", timeInfo.getProgress());
        }

        // 如果还未生成工艺参数
        if(state.getGasLiftParams() == null){
            return MessageRespnser.success(map);
        }

        if(state.getGasLiftParams().getStartPressureDiff() != 0)
            map.put("start_pressure", state.getGasLiftParams().getStartPressureDiff());
        if(state.getGasLiftParams().getGasInjectionVolume() != 0)
            map.put("inj_volume", state.getGasLiftParams().getGasInjectionVolume());
        if(state.getGasLiftParams().getGasInjectionRate() != 0)
            map.put("inj_rate", state.getGasLiftParams().getGasInjectionRate());
        if(state.getGasLiftParams().getGasInjectionTime() != 0)
            map.put("inj_time", state.getGasLiftParams().getGasInjectionTime());
        if(state.getGasLiftParams().getGasInjectionRateDef() != 0)
            map.put("inj_rate_default", state.getGasLiftParams().getGasInjectionRateDef());
        if(state.getGasLiftParams().getGasInjectionTimeDef() != 0)
            map.put("inj_time_default", state.getGasLiftParams().getGasInjectionTimeDef());

        return MessageRespnser.success(map);
    }

    @GetMapping("/get-pump-state")
    public MessageRespnser<Map<String, Object>> getPumpState(){
        Map<String, Object> map = new HashMap<>();
        PumpProgressState state = pumpPSMapper.findFirstByOrderByDataIdDesc().get();
        if(state.isRunning())
            map.put("running", (double)1);
        else map.put("running", (double) 0);
        // 工艺参数
        map.put("start_pressure", (double) 0);
        map.put("machine_count", (double) 0);
        map.put("machine_power", (double) 0);
        map.put("machine_count_default", (double) 0);
        map.put("machine_power_default", (double) 0);
        map.put("last_time", "-");
        map.put("total_duration", "-");
        map.put("kept_duration", "-");
        map.put("progress", 0);
        // 上次启动时间
        ProgressInfo timeInfo = calculateProgress(state.getLastStartTime(), Duration.ofSeconds(state.getLastDurationSecond()));
        if(state.getLastStartTime() != null)
            map.put("last_time", state.getLastStartTime().toString());
        if(state.isRunning()){
            map.put("total_duration", timeInfo.totalTime);
            map.put("kept_duration", timeInfo.elapsedTime);
            map.put("progress", timeInfo.getProgress());
        }
        // 如果还未生成工艺参数
        if(state.getPumpParams() == null){
            return MessageRespnser.success(map);
        }

        if(state.getPumpParams().getStartPressureDiff() != 0)
            map.put("start_pressure", state.getPumpParams().getStartPressureDiff());
        if(state.getPumpParams().getMachineCount() != 0)
            map.put("machine_count", state.getPumpParams().getMachineCount());
        if(state.getPumpParams().getMachinePower() != 0)
            map.put("machine_power", state.getPumpParams().getMachinePower());
        if(state.getPumpParams().getMachineCountDef() != 0)
            map.put("machine_count_default", state.getPumpParams().getMachineCountDef());
        if(state.getPumpParams().getMachinePowerDef() != 0)
            map.put("machine_power_default", state.getPumpParams().getMachinePowerDef());

        return MessageRespnser.success(map);
    }

    @GetMapping("/get-foam-state")
    public MessageRespnser<Map<String, Object>> getFoamState(){
        Map<String, Object> map = new HashMap<>();
        FoamProgressState state = foamPSMapper.findFirstByOrderByDataIdDesc().get();
        if(state.isRunning())
            map.put("running", 1);
        else map.put("running", 0);
        // 工艺参数
        map.put("start_pressure", (double) 0);
        map.put("fluid_volume", 0);
        map.put("inj_volume", (double) 0);
        map.put("inj_rate", (double) 0);
        map.put("inj_time", (double) 0);
        map.put("inj_rate_default", (double) 0);
        map.put("inj_time_default", (double) 0);
        map.put("last_time", "-");
        map.put("total_duration", "-");
        map.put("kept_duration", "-");
        map.put("progress", 0);
        // 上次启动时间
        ProgressInfo timeInfo = calculateProgress(state.getLastStartTime(), Duration.ofSeconds(state.getLastDurationSecond()));
        if(state.getLastStartTime() != null)
            map.put("last_time", state.getLastStartTime().toString());
        if(state.isRunning()){
            map.put("total_duration", timeInfo.totalTime);
            map.put("kept_duration", timeInfo.elapsedTime);
            map.put("progress", timeInfo.getProgress());
        }
        // 如果还未生成工艺参数
        if(state.getFoamParams() == null){
            return MessageRespnser.success(map);
        }

        if(state.getFoamParams().getStartPressureDiff() != 0)
            map.put("start_pressure", state.getFoamParams().getStartPressureDiff());
        if(state.getFoamParams().getInjectionRate() != 0)
            map.put("inj_rate", state.getFoamParams().getInjectionRate());
        if(state.getFoamParams().getInjectionVolume() != 0)
            map.put("inj_volume", state.getFoamParams().getInjectionVolume());
        if(state.getFoamParams().getInjectionTime() != 0)
            map.put("inj_time", state.getFoamParams().getInjectionTime());
        if(state.getFoamParams().getInjectionRateDef() != 0)
            map.put("inj_rate_default", state.getFoamParams().getInjectionRateDef());
        if(state.getFoamParams().getInjectionTimeDef() != 0)
            map.put("inj_time_default", state.getFoamParams().getInjectionTimeDef());
        map.put("fluid_volume", 100);

        return MessageRespnser.success(map);
    }

    @GetMapping("/get-logs-by-id")
    public MessageRespnser<List<String>> getAllLogsById(){
        Integer sysId = processController.getSystemId();
        if(sysId == 0){
            return MessageRespnser.unsuccess(Collections.singletonList("系统尚未启动"));
        }
        List<LogEntry> logs = logRepository.findBySystemStateIdOrderByTimestampAsc(sysId);
        if (logs.isEmpty()) {
            return MessageRespnser.unsuccess(Collections.singletonList("暂无日志记录"));
        }
        // 将所有日志转换为字符串数组
        List<String> list = new ArrayList<>();
        for(LogEntry log : logs){
            log.setTimestamp(log.getTimestamp().minusHours(8));
            list.add(formatLogEntry(log));
        }
        return MessageRespnser.success(list);
    }

    @GetMapping("/get-rec-logs-by-id")
    public MessageRespnser<List<String>> getRecentLogsById(){
        Integer sysId = processController.getSystemId();
        if(sysId == 0){
            return MessageRespnser.unsuccess(Collections.singletonList("系统尚未启动"));
        }
        List<LogEntry> logs = logRepository.findRecentBySystemStateIdNative(sysId);
        if (logs.isEmpty()) {
            return MessageRespnser.unsuccess(Collections.singletonList("暂无日志记录"));
        }
        // 将所有日志转换为字符串数组
        List<String> list = new ArrayList<>();
        for(LogEntry log : logs){
            log.setTimestamp(log.getTimestamp().minusHours(8));
            list.add(formatLogEntry(log));
        }
        return MessageRespnser.success(list);
    }

    // 工具类
    public static ProgressInfo calculateProgress(LocalDateTime startTime, Duration totalDuration) {
        // 计算已运行时间
        Duration elapsedDuration;
        if (startTime != null) {
            elapsedDuration = Duration.between(startTime, LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
        } else {
            elapsedDuration = Duration.ZERO;
        }

        // 格式化时间
        String elapsedTime = formatDuration(elapsedDuration);
        String totalTime = formatDuration(totalDuration);

        // 计算进度百分比
        double progress = 0.0;
        if (totalDuration.getSeconds() > 0) {
            progress = (elapsedDuration.getSeconds() * 100.0) / totalDuration.getSeconds();
            // 确保进度在0-100%之间
            progress = Math.min(100.0, Math.max(0.0, progress));
        }

        return new ProgressInfo(elapsedTime, totalTime, progress);
    }

    private static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();

        StringBuilder sb = new StringBuilder();

        if (days > 0) {
            sb.append(days).append("天");
        }
        if (hours > 0) {
            if (sb.length() > 0) sb.append("");
            sb.append(hours).append("小时");
        }
        if (minutes > 0 || sb.length() == 0) {
            if (sb.length() > 0) sb.append("");
            sb.append(minutes).append("分钟");
        }

        return sb.toString();
    }

    static class ProgressInfo {
        private final String elapsedTime;
        private final String totalTime;
        private final double progress;

        public ProgressInfo(String elapsedTime, String totalTime, double progress) {
            this.elapsedTime = elapsedTime;
            this.totalTime = totalTime;
            this.progress = progress / 100;
        }

        public String getElapsedTime() {
            return elapsedTime;
        }

        public String getTotalTime() {
            return totalTime;
        }

        public double getProgress() {
            return progress;
        }
    }

    // 辅助方法：格式化日志条目为字符串
    private String formatLogEntry(LogEntry logEntry) {
        StringBuilder formattedLog = new StringBuilder();

        // 时间戳 - 使用更友好的格式
        String timestamp = logEntry.getTimestamp().toString().replace('T', ' ');

        // 基础信息
        formattedLog.append(String.format("[%s] ", timestamp));
        formattedLog.append(String.format("%s-%s: ",
                logEntry.getProcessType().getDisplayName(),
                logEntry.getEventType().getDisplayName()));
        formattedLog.append(logEntry.getMessage());

        // 添加ID信息
        formattedLog.append(String.format(" (ID: %d)", logEntry.getId()));

        // 如果有详情信息，则添加
        if (logEntry.getDetails() != null && !logEntry.getDetails().isEmpty()) {
            formattedLog.append(" - 详情: ");
            try {
                // 使用ObjectMapper将详情格式化为JSON字符串
                ObjectMapper objectMapper = new ObjectMapper();
                String detailsJson = objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(logEntry.getDetails());
                formattedLog.append(detailsJson);
            } catch (JsonProcessingException e) {
                formattedLog.append("详情格式错误");
            }
        }

        return formattedLog.toString();
    }

}
