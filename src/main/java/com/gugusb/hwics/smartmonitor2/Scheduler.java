package com.gugusb.hwics.smartmonitor2;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ===============================
 * Scheduler
 * -------------------------------
 * 功能：
 *  - 提供统一的调度服务
 *  - 可延时执行、周期执行
 *  - 可取消任务，避免重复调度
 * ===============================
 */
public class Scheduler {

    private final ScheduledExecutorService executor;
    private final Map<String, ScheduledFuture<?>> taskRegistry; // 记录任务，支持取消
    private final AtomicInteger taskCounter; // 任务编号

    public Scheduler(int poolSize) {
        this.executor = Executors.newScheduledThreadPool(poolSize);
        this.taskRegistry = new ConcurrentHashMap<>();
        this.taskCounter = new AtomicInteger(0);
    }

    /**
     * 提交一个延时任务
     * @param name 任务名称（业务唯一标识）
     * @param task 执行逻辑
     * @param delay 延时时间
     * @param unit 时间单位
     */
    public void scheduleOnce(String name, Runnable task, long delay, TimeUnit unit) {
        cancelTask(name); // 避免重复
        ScheduledFuture<?> future = executor.schedule(() -> {
            try {
                task.run();
            } finally {
                taskRegistry.remove(name); // 执行完自动清理
            }
        }, delay, unit);
        taskRegistry.put(name, future);
    }

    /**
     * 提交一个周期性任务
     * @param name 任务名称
     * @param task 执行逻辑
     * @param initialDelay 首次延时
     * @param period 执行周期
     * @param unit 时间单位
     */
    public void schedulePeriodic(String name, Runnable task, long initialDelay, long period, TimeUnit unit) {
        cancelTask(name); // 避免重复
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(task, initialDelay, period, unit);
        taskRegistry.put(name, future);
    }

    /**
     * 取消任务
     */
    public void cancelTask(String name) {
        if (taskRegistry.containsKey(name)) {
            taskRegistry.get(name).cancel(false);
            taskRegistry.remove(name);
        }
    }

    /**
     * 生成唯一任务名
     */
    public String nextTaskName(String prefix) {
        return prefix + "-" + taskCounter.incrementAndGet();
    }

    /**
     * 停止调度器
     */
    public void shutdown() {
        executor.shutdownNow();
    }
}

