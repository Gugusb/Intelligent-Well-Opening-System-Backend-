package com.gugusb.hwics.logger.service;

import com.gugusb.hwics.logger.entity.LogEntry;
import com.gugusb.hwics.logger.enums.EventType;
import com.gugusb.hwics.logger.enums.ProcessType;
import com.gugusb.hwics.logger.mapper.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class LogService {

    private static final int HEARTBEAT_INTERVAL = 2 * 60 * 60; // 2小时（秒）

    private final LogRepository logRepository;
    private final ScheduledThreadPoolExecutor scheduler;

    // 心跳任务映射
    private final Map<ProcessType, ScheduledFuture<?>> heartbeatTasks = new ConcurrentHashMap<>();

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
        this.scheduler = new ScheduledThreadPoolExecutor(4);
    }

    /**
     * 记录工艺事件日志（异步）
     */
    @Async
    @Transactional
    public void logProcessEvent(ProcessType processType, EventType eventType,
                                String message, Map<String, Object> details) {
        LogEntry entry = new LogEntry(
                LocalDateTime.now(),
                processType,
                eventType,
                message,
                details
        );

        // 保存到数据库
        logRepository.save(entry);

        // 通知UI(已禁用)
        //uiNotification.notifyNewLog(entry);

        // 特殊事件处理
        handleSpecialEvents(processType, eventType);
        System.out.println("进程日志打印" + ProcessType.SYSTEM + eventType + message);
    }

    /**
     * 记录系统事件日志
     */
    @Async
    @Transactional
    public void logSystemEvent(EventType eventType, String message) {
        System.out.println("系统日志打印" + ProcessType.SYSTEM + eventType + message);
        logProcessEvent(ProcessType.SYSTEM, eventType, message, null);
    }

    /**
     * 记录系统事件日志（带详情）
     */
    @Async
    @Transactional
    public void logSystemEvent(EventType eventType, String message, Map<String, Object> details) {
        logProcessEvent(ProcessType.SYSTEM, eventType, message, details);
    }

    /**
     * 查询日志
     */
    public List<LogEntry> getLogsByProcessType(ProcessType processType) {
        return logRepository.findByProcessTypeOrderByTimestampDesc(processType);
    }

    /**
     * 分页查询日志
     */
    public Page<LogEntry> getLogsPage(Pageable pageable) {
        return logRepository.findAllByOrderByTimestampDesc(pageable);
    }

    /**
     * 按时间范围查询日志
     */
    public List<LogEntry> getLogsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return logRepository.findByTimestampBetween(start, end);
    }

    /**
     * 按工艺类型和时间范围查询日志
     */
    public List<LogEntry> getLogsByProcessAndTime(ProcessType processType,
                                                  LocalDateTime start,
                                                  LocalDateTime end) {
        return logRepository.findByProcessTypeAndTimestampBetween(processType, start, end);
    }

    // 处理特殊事件（启动/结束）
    private void handleSpecialEvents(ProcessType processType, EventType eventType) {
        if (eventType == EventType.START) {
            scheduleHeartbeat(processType);
        } else if (eventType == EventType.END) {
            cancelHeartbeat(processType);
        }
    }

    // 调度心跳日志
    private void scheduleHeartbeat(ProcessType processType) {
        ScheduledFuture<?> task = scheduler.scheduleAtFixedRate(
                () -> logHeartbeat(processType),
                HEARTBEAT_INTERVAL,
                HEARTBEAT_INTERVAL,
                TimeUnit.SECONDS
        );
        heartbeatTasks.put(processType, task);
    }

    // 取消心跳日志
    private void cancelHeartbeat(ProcessType processType) {
        ScheduledFuture<?> task = heartbeatTasks.remove(processType);
        if (task != null) {
            task.cancel(false);
        }
    }

    // 记录心跳日志
    private void logHeartbeat(ProcessType processType) {
        String message = String.format("%s工艺运行中", processType.getDisplayName());
        logProcessEvent(processType, EventType.HEARTBEAT, message, null);
    }

    public List<LogEntry> getAllLogsOrderByTimestampDesc() {
        return logRepository.findAllByOrderByTimestampDesc();
    }
}
