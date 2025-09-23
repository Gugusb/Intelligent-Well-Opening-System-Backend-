package com.gugusb.hwics.smartmonitor2;

import com.gugusb.hwics.logger.enums.EventType;
import com.gugusb.hwics.logger.enums.ProcessType;
import com.gugusb.hwics.logger.service.LogService;
import com.gugusb.hwics.smartmonitor2.entity.*;
import com.gugusb.hwics.smartmonitor2.entity.processstate.FoamProgressState;
import com.gugusb.hwics.smartmonitor2.entity.processstate.GasLiftProgressState;
import com.gugusb.hwics.smartmonitor2.entity.processstate.PumpProgressState;
import com.gugusb.hwics.smartmonitor2.mapper.FoamPSMapper;
import com.gugusb.hwics.smartmonitor2.mapper.GasLiftPSMapper;
import com.gugusb.hwics.smartmonitor2.mapper.PumpPSMapper;
import com.gugusb.hwics.smartmonitor2.mapper.SystemStateMapper;
import com.gugusb.hwics.smartmonitor2.progress.FoamProcess;
import com.gugusb.hwics.smartmonitor2.progress.GasLiftProcess;
import com.gugusb.hwics.smartmonitor2.progress.PumpingProcess;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 系统流程控制类
 */
@Service
public class ProcessController {

    @Autowired
    LogService logService;
    @Autowired
    DeviceManager deviceManager;

    @Autowired
    SystemStateMapper systemStateMapper;
    @Autowired
    FoamPSMapper foamPSMapper;
    @Autowired
    PumpPSMapper pumpPSMapper;
    @Autowired
    GasLiftPSMapper gasLiftPSMapper;

    private FoamProcess foamProcess = new FoamProcess();
    private PumpingProcess pumpingProcess = new PumpingProcess();
    private GasLiftProcess gasLiftProcess = new GasLiftProcess();

    private PumpProgressState pumpState = new PumpProgressState();
    private GasLiftProgressState gasLiftState = new GasLiftProgressState();
    private FoamProgressState foamState = new FoamProgressState();

    private SystemState systemState;

    Scheduler scheduler = new Scheduler(10);
    /**
     * 阶段0：系统未启动
     * 阶段1：系统启动，执行自检
     * 阶段2：系统自检完毕，输入气井参数
     * 阶段3：系统开始初始化，数据开始同步
     * 阶段4：启动泡排系统
     * 阶段5：启动抽汲系统
     * 阶段6：延迟后进入监测阶段
     * 阶段7：监测阶段（循环执行气举和泡排）
     */

    // 初始化流程控制类+断点恢复
    @PostConstruct
    private void initSystem(){
        // 系统恢复
        boolean isInit;
        //如果数据库为空或安全标签为1，则说明上次系统正常关闭，初始化state
        //否则，说明系统上次意外关闭，重新读取上次的state
        if(systemStateMapper.count() == 0){
            isInit = true;
        }
        else if(systemStateMapper.findFirstByOrderByDataIdDesc().isEmpty()){
            isInit = true;
        }
        else if(systemStateMapper.findFirstByOrderByDataIdDesc().get().getSafeColseTag() == 1){
            isInit = true;
        }else{
            isInit = false;
        }
        if(isInit){
            systemState = new SystemState();
            systemState.initState();
            logService.logSystemEvent(EventType.START, "开始初始化系统状态。");
        }else{
            //系统状态恢复
            this.systemState = systemStateMapper.findFirstByOrderByDataIdDesc().get();
            logService.logSystemEvent(EventType.WARNING, "检测到上次系统意外终止，正在尝试恢复上次的系统状态。");
        }

        //工艺状态恢复
        if(gasLiftPSMapper.count() > 0){
            if(!gasLiftPSMapper.findFirstByOrderByDataIdDesc().isEmpty()){
                this.gasLiftState = gasLiftPSMapper.findFirstByOrderByDataIdDesc().get();
                logService.logSystemEvent(EventType.PARAM_CHANGE, "气举系统数据恢复。");
            }
        }
        if(pumpPSMapper.count() > 0){
            if(!pumpPSMapper.findFirstByOrderByDataIdDesc().isEmpty()){
                this.pumpState = pumpPSMapper.findFirstByOrderByDataIdDesc().get();
                logService.logSystemEvent(EventType.PARAM_CHANGE, "抽汲系统数据恢复。");
            }
        }
        if(foamPSMapper.count() > 0){
            if(!foamPSMapper.findFirstByOrderByDataIdDesc().isEmpty()){
                this.foamState = foamPSMapper.findFirstByOrderByDataIdDesc().get();
                logService.logSystemEvent(EventType.PARAM_CHANGE, "泡排系统数据恢复。");
            }
        }
        checkAll();
    }

    // 修改参数
    public boolean editParams(WellParams wellParams){
        if(systemState.isSystemRunning()){
            this.systemState.setWellParams(wellParams);
            logService.logSystemEvent(EventType.PARAM_CHANGE, "气井数据修改完成。");
            return true;
        }
        return false;
    }

    // 开启系统并开始自检（阶段0-1）
    public boolean startSystemCheck(){
        if(systemState.getSystemStage() == 0){
            systemState.setSystemRunning(true);
            systemState.setSystemStage(1);
            storeSystemState();
            logService.logSystemEvent(EventType.START, "智能系统开始启动！即将进行系统自检。");
        }
        if(systemState.getSystemStage() == 1){
            logService.logSystemEvent(EventType.START, "系统自检中...");
            double checkResult = deviceManager.checkAllTags();
            if(checkResult > 0.95){
                systemState.setSystemStage(2);
                storeSystemState();
                logService.logSystemEvent(EventType.START, "智能系统自检成功！成功率：" + checkResult * 100 + "%");
                return true;
            }
        }
        return false;
    }

    // 启动系统的后续工艺（阶段2-6）
    public void startSystemProcess(WellParams wellParams) {
        if(systemState.getSystemStage() <= 1)return;
        if(!systemState.isSystemRunning())return;

        // 读取用户写入的气井数据(阶段2操作)
        if(systemState.getSystemStage() == 2){
            systemState.setWellParams(wellParams);
            systemState.setSystemStage(3);
            storeSystemState();
            logService.logSystemEvent(EventType.PARAM_CHANGE, "气井数据输入完成。");
        }

        // 每分钟检测/存储一次所有设备的状态(全阶段操作)
        scheduler.schedulePeriodic(
                "All checking",
                this::checkAll,
                0,
                1,
                TimeUnit.MINUTES);

        if(systemState.getSystemStage() == 3){
            systemState.setSystemStage(4);
            storeSystemState();
        }

        // (4) 泡排工艺启动（阶段4操作）
        if(systemState.getSystemStage() == 4){
            firstStartFoam();
            systemState.setSystemStage(5);
            storeSystemState();
        }

        // (5) 泡排结束 20 分钟后 -> 启动抽汲工艺（阶段5操作）
        if(systemState.getSystemStage() >= 5){
            if(systemState.getSystemStage() > 5){
                scheduler.schedulePeriodic(
                        "Start pump check",
                        () -> {
                            monitorForPump();
                        },
                        0,
                        1,
                        TimeUnit.MINUTES
                );
            }
            if(systemState.getSystemStage() == 5){
                scheduler.schedulePeriodic(
                        "Start pump check",
                        () -> {
                            monitorForPump();
                        },
                        1,
                        1,
                        TimeUnit.MINUTES
                );
                systemState.setSystemStage(6);
                storeSystemState();
            }
        }

        // (6) 泡排启动 30 分钟后 -> 每分钟检测一次气举开启条件（阶段6操作）
        if(systemState.getSystemStage() == 6){
            scheduler.schedulePeriodic(
                    "Gas lift checking",
                    ()->{
                        this.monitorForGasLift();
                        if(systemState.getSystemStage() == 6) {
                            systemState.setSystemStage(7);
                            storeSystemState();
                        }
                    },
                    3,
                    1,
                    TimeUnit.MINUTES);
            scheduler.schedulePeriodic(
                    "Foam checking",
                    ()->{
                        if(systemState.isFirstGasLiftStarted()){
                            this.monitorForFoam();
                            if(systemState.getSystemStage() == 7)
                                systemState.setSystemStage(8);
                        }
                    },
                    3,
                    1,
                    TimeUnit.MINUTES);
        }
        // (7) 开始持续的气举条件监测（以此法开启的气举监测不需要初始等待时间）
        if(systemState.getSystemStage() == 7){
            scheduler.schedulePeriodic(
                    "Gas lift checking",
                    ()->{
                        this.monitorForGasLift();
                    },
                    0,
                    1,
                    TimeUnit.MINUTES);
            scheduler.schedulePeriodic(
                    "Foam checking",
                    ()->{
                        if(systemState.isFirstGasLiftStarted()){
                            this.monitorForFoam();
                            if(systemState.getSystemStage() == 7)
                                systemState.setSystemStage(8);
                        }
                    },
                    3,
                    1,
                    TimeUnit.MINUTES);
        }

        // (8) 开始持续的气举+泡排条件监测（以此法开启的监测不需要等待时间）
        if(systemState.getSystemStage() == 8){
            scheduler.schedulePeriodic(
                    "Gas lift checking",
                    ()->{
                        this.monitorForGasLift();
                    },
                    0,
                    1,
                    TimeUnit.MINUTES);
            scheduler.schedulePeriodic(
                    "Foam checking",
                    ()->{
                        this.monitorForFoam();
                    },
                    0,
                    1,
                    TimeUnit.MINUTES);
        }
    }

    // 关闭系统
    public boolean closeSystem(){
        // 标记安全关闭
        systemState.setSystemRunning(false);
        systemState.setSafeColseTag(1);
        // 保存最后的状态到数据库
        systemStateMapper.save(systemState);
        // 注销所有执行事件
        scheduler.shutdown();
        // 创建新的系统状态
        this.systemState = new SystemState();
        this.systemState.initState();
        storeSystemState();
        // 创建新的计时器
        this.scheduler = new Scheduler(10);
        // 工艺状态保存(不做修改)
        logService.logProcessEvent(ProcessType.SYSTEM, EventType.END, "系统已经关闭", null);
        return true;
    }

    // 仅测试-重置所有的工艺状态
    public void restartAllProcess(){
        this.pumpState = new PumpProgressState();
        this.foamState = new FoamProgressState();
        this.gasLiftState = new GasLiftProgressState();
        checkAll();
    }

    /**
     * 首次尝试开启泡排
     */
    private void firstStartFoam(){
        ProductionSnapshot snapshot = DataReader.readProductionSnapshot();
        FoamParams calcFoamParams = ParamManager.computeFoamParams(systemState.getWellParams(), snapshot);
        FoamParams defaultFoamParams = ParamManager.defaultFoamParams();
        //选择默认/计算得到的参数
        FoamParams selectedFoamParams = defaultFoamParams;
        foamProcess.startWithParams(selectedFoamParams);
        foamState.start(Duration.ofSeconds((long)selectedFoamParams.getInjectionTime()));
        // 强制存储状态一次
        storeAllState();
        logService.logProcessEvent(ProcessType.FOAMING, EventType.START,
                "初始泡排工艺启动", selectedFoamParams.transToMap());
    }

    /**
     * 监测抽吸工艺启动状态
     */
    private void monitorForPump() {
        System.out.println("=====抽吸状态监测=====");
        if(pumpState.isRunning())return;
        if (systemState.isSystemRunning()) {
            pumpingProcess.startWithParams(null); // 抽汲工艺无需参数
            pumpState.start(Duration.ofSeconds((long)999999999));
            logService.logProcessEvent(ProcessType.SWABBING, EventType.START,
                    "抽汲工艺启动", null);
            // 强制存储状态一次
            checkAll();
        }
    }

    /**
     * 监测气举工艺启动条件（GL1）
     */
    private void monitorForGasLift() {
        System.out.println("=====气举条件监测=====");
        if (gasLiftState.isRunning()){
            systemState.setFirstGasLiftStarted(true);
            storeSystemState();
        }
        if(gasLiftState.isRunning())return;
        if (systemState.isSystemRunning()) {
            ProductionSnapshot snapshot = DataReader.readProductionSnapshot();
            if (!gasLiftProcess.isInCooldown() && gasLiftProcess.canStart(snapshot)) {
                GasLiftParams calcGasLiftParams = ParamManager.computeGasLiftParams(systemState.getWellParams(), snapshot);
                GasLiftParams defaultGasLiftParams = ParamManager.defaultGasLiftParams();
                GasLiftParams selectedGasLiftParams = defaultGasLiftParams;

                gasLiftProcess.startWithParams(selectedGasLiftParams);
                gasLiftState.start(Duration.ofSeconds((long)selectedGasLiftParams.getGasInjectionTime()));

                logService.logProcessEvent(ProcessType.GAS_LIFT, EventType.START,
                        "气举工艺启动", selectedGasLiftParams.transToMap());
                // 强制存储状态一次
                checkAll();
                if (!systemState.isFirstGasLiftStarted()) {
                    systemState.setFirstGasLiftStarted(true);
                }
            }
        }
    }

    /**
     * 监测泡排工艺启动条件（P1）
     */
    private void monitorForFoam() {
        System.out.println("=====泡排条件监测=====");
        if(foamState.isRunning())return;
        if (systemState.isSystemRunning()) {
            ProductionSnapshot snapshot = DataReader.readProductionSnapshot();
            if (!foamProcess.isInCooldown() && foamProcess.canStart(snapshot)) {
                FoamParams calcFoamParams = ParamManager.computeFoamParams(systemState.getWellParams(), snapshot);
                FoamParams defaultFoamParams = ParamManager.defaultFoamParams();
                FoamParams selectedFoamParams = defaultFoamParams;

                foamProcess.startWithParams(selectedFoamParams);
                foamState.start(Duration.ofSeconds((long)selectedFoamParams.getInjectionTime()));
                logService.logProcessEvent(ProcessType.FOAMING, EventType.START,
                        "泡排工艺启动", selectedFoamParams.transToMap());
                // 强制存储状态一次
                checkAll();
            }
        }
    }

    private void storeSystemState(){
        systemState.fillAsEntity();
        systemStateMapper.save(systemState);
    }

    private void storeProcessState(){
        foamState.fillAsEntity();
        gasLiftState.fillAsEntity();
        pumpState.fillAsEntity();

        foamPSMapper.save(foamState);
        pumpPSMapper.save(pumpState);
        gasLiftPSMapper.save(gasLiftState);
    }

    private void storeAllState(){
        storeProcessState();
        storeSystemState();
    }

    private void checkProcessState(){
        // 0Pump 1Foam 2GasLift
        List<Boolean>  realStates = deviceManager.getProcessState();
        //pumpState.setRunning(realStates.get(0));
        //foamState.setRunning(realStates.get(1));
        //gasLiftState.setRunning(realStates.get(2));
    }

    private void checkAll(){
        //检查设备是否关闭，如果关闭则更新state
        checkProcessState();
        logService.logSystemEvent(EventType.PARAM_CHANGE, "现场设备状态检查完毕");

        //存储所有的状态
        storeAllState();
        logService.logSystemEvent(EventType.PARAM_CHANGE, "智能控制状态存储完毕");
    }

    public boolean isRunning() {
        return systemState.isSystemRunning();
    }
}
