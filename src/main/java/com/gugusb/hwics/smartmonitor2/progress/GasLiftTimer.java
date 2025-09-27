package com.gugusb.hwics.smartmonitor2.progress;

import com.gugusb.hwics.smartmonitor2.DeviceManager;
import com.gugusb.hwics.smartmonitor2.Scheduler;
import com.gugusb.hwics.smartmonitor2.entity.GasLiftTimerPojo;
import com.gugusb.hwics.smartmonitor2.mapper.GasLiftTimerMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class GasLiftTimer {
    @Autowired
    GasLiftTimerMapper timerMapper;
    @Autowired
    DeviceManager deviceManager;

    private GasLiftTimerPojo timerPojo;
    private LocalDateTime startTime;
    private Duration duration;
    Scheduler scheduler = new Scheduler(10);

    // 初始化计时器
    @PostConstruct
    private void initTimer(){
        // 数据库为空则新建一个timer
        if(timerMapper.count() == 0){
            timerPojo = new GasLiftTimerPojo();
        }
        // 数据库存在则直接读取
        else{
            Optional<GasLiftTimerPojo> timerT = timerMapper.findFirstByOrderByDataIdDesc();
            if(timerT == null || timerT.get().getStartTime() == null || timerT.get().getDuration() == null){
                timerPojo = new GasLiftTimerPojo();
            }else{
                timerPojo = timerT.get();
                this.startTime = timerPojo.getStartTime();
                this.duration = timerPojo.getDuration();
            }
        }
        saveData();
        // 创建一个计时器负责气举的开启和数据存储
        scheduler.schedulePeriodic(
                "Gas Lift Real Open",
                ()->{
                    openGasLiftCheck();
                    saveData();
                },
                1,
                1,
                TimeUnit.MINUTES);
    }

    // 气举开启监测任务
    public void openGasLiftCheck(){
        System.out.println("GasLiftTimer: 进行气举状态监测，尝试开启/结束气举");
        // 如果开始时间和持续时间尚未初始化，跳出
        if(this.startTime == null || this.duration == null){
            System.out.println("GasLiftTimer: 气举监测完毕，气举任务尚未初始化！");
            return;
        }
        // 如果气举开启中，检查是否应该关闭
        if(deviceManager.getProcessState().get("gas_lift")){
            if(LocalDateTime.now().isAfter(this.startTime.plus(duration))){
                System.out.println("GasLiftTimer: 超出气举时间，气举关闭");
                endGasLift();
            }
        }
        // 如果气举关闭，尝试开启
        else{
            if((this.startTime.plus(duration)).isAfter(LocalDateTime.now())){
                System.out.println("GasLiftTimer: 处于气举开启时间，气举开启");
                startGasLift();
            }
        }
    }
    // 数据存储任务
    public void saveData(){
        this.timerPojo.setDuration(this.duration);
        this.timerPojo.setStartTime(this.startTime);
        timerMapper.save(this.timerPojo);
    }

    // 增添一个气举的执行任务
    public void addTask(LocalDateTime start, Duration dur){
        System.out.println("GasLiftTimer: 新增气举任务，检查是否需要延续气举时间");
        System.out.println("GasLiftTimer: 任务开启时间：" + start + "持续时间：" + dur.toMinutesPart() + "min");
        // 气举若没有正在运行，则重置开始时间和结束时间
        if(!this.isRunning()){
            this.startTime = start;
            this.duration = dur;
            System.out.println("GasLiftTimer: 检查到没有气举任务，新增气举任务");
        }
        // 若气举正在运行，则比较最终时间的长短
        else{
            LocalDateTime endTemp = startTime.plus(duration);
            LocalDateTime endNew = start.plus(dur);
            System.out.print("GasLiftTimer: 检查到已有历史气举任务" + endTemp + " / " + endNew);
            // 如果新的结束时间在旧的结束时间之后 更新最晚结束的持续时间
            if(endNew.isAfter(endTemp)){
                Duration newDur = Duration.between(this.startTime, endNew);
                this.duration = newDur;
                System.out.println("   新任务结束时间更晚，时间延续，新的结束时间：" + endNew);
            }else
                System.out.println("   旧任务结束时间更晚，时间不变：" + endTemp);
        }
        saveData();
    }

    // 开始气举
    private void startGasLift(){
        deviceManager.turnOnGasLift();
    }
    // 停止气举
    private void endGasLift(){
        deviceManager.turnOffGasLift();
    }
    // 气举是否正在运行
    public Boolean isRunning(){
        if(startTime == null || duration == null)return false;
        if(LocalDateTime.now().isAfter(startTime.plus(duration)))
            return false;
        return true;
    }

    public static Boolean isRunning(LocalDateTime startTime, Duration duration){
        System.out.println("start:" + startTime);
        System.out.println("end:" + startTime.plus(duration));
        System.out.println("now:" + LocalDateTime.now());
        if(LocalDateTime.now().isAfter(startTime.plus(duration)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GasListTimer{" +
                "startTime=" + startTime +
                ", duration=" + duration +
                '}';
    }
}
