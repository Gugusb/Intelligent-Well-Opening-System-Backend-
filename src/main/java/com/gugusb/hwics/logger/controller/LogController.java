package com.gugusb.hwics.logger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gugusb.hwics.logger.entity.LogEntry;
import com.gugusb.hwics.logger.enums.EventType;
import com.gugusb.hwics.logger.service.LogService;
import com.gugusb.hwics.logger.enums.ProcessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping
    public Page<LogEntry> getLogs(Pageable pageable) {
        return logService.getLogsPage(pageable);
    }

    @GetMapping("/by-process/{processType}")
    public List<LogEntry> getLogsByProcessType(@PathVariable ProcessType processType) {
        return logService.getLogsByProcessType(processType);
    }

    @GetMapping("/by-time")
    public List<LogEntry> getLogsByTimeRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return logService.getLogsByTimeRange(start, end);
    }

    @GetMapping("/by-process-and-time")
    public List<LogEntry> getLogsByProcessAndTime(
            @RequestParam ProcessType processType,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return logService.getLogsByProcessAndTime(processType, start, end);
    }

    // 新增接口1: 创建三种不同类型的新日志
    @PostMapping("/create-sample-logs")
    public String createSampleLogs() {
        // 创建泡排工艺启动日志
        Map<String, Object> foamingDetails = new HashMap<>();
        foamingDetails.put("param1", "value1");
        foamingDetails.put("param2", 123);
        logService.logProcessEvent(ProcessType.FOAMING, EventType.START,
                "泡排工艺启动", foamingDetails, 1);

        // 创建气举工艺警告日志
        Map<String, Object> gasLiftDetails = new HashMap<>();
        gasLiftDetails.put("pressure", 25.5);
        gasLiftDetails.put("flowRate", 30.2);
        logService.logProcessEvent(ProcessType.GAS_LIFT, EventType.WARNING,
                "气举压力异常", gasLiftDetails, 1);

        // 创建系统错误日志
        Map<String, Object> systemDetails = new HashMap<>();
        systemDetails.put("errorCode", "E1001");
        systemDetails.put("component", "数据采集模块");
        logService.logSystemEvent(EventType.ERROR,
                "数据采集失败", systemDetails, 1);

        return "三种示例日志已创建";
    }

    // 新增接口2: 获取最新一条日志
    @GetMapping("/latest")
    public Map<String, String[]> getLatestLog() {
        // 获取所有日志并按时间降序排列
        List<LogEntry> allLogs = logService.getAllLogsOrderByTimestampDesc();

        if (allLogs.isEmpty()) {
            return Map.of("data", new String[]{"暂无日志记录"});
        }

        // 取最新的一条日志
        LogEntry latestLog = allLogs.get(0);
        String logString = formatLogEntry(latestLog);

        return Map.of("data", new String[]{logString});
    }

    // 新增接口3: 获取全部日志
    @GetMapping("/all")
    public Map<String, String[]> getAllLogs() {
        // 获取所有日志并按时间降序排列
        List<LogEntry> allLogs = logService.getAllLogsOrderByTimestampDesc();

        if (allLogs.isEmpty()) {
            return Map.of("data", new String[]{"暂无日志记录"});
        }

        // 将所有日志转换为字符串数组
        String[] logStrings = allLogs.stream()
                .map(this::formatLogEntry)
                .toArray(String[]::new);

        return Map.of("data", logStrings);
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
