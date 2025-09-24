package com.gugusb.hwics.smartmonitor;

import com.gugusb.hwics.pojo.ProductionParameter;
import com.gugusb.hwics.pojo.WellParameter;
import com.gugusb.hwics.utils.DateSpawner;
import com.gugusb.hwics.utils.StageKey;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class StageManager {
    private Integer mainStageId;        //0系统未开启；1系统刚开启/刚开井；2抽汲系统已经启动；3气举系统已经启动;4泡排系统已经启动
    private Integer childStageId;       //0启动条件判断中，设备未启动；1符合启动条件，已经启动，转换条件判断中；2符合结束条件，设备已经关闭，等待下一步操作

    private Timestamp mainStartTime;
    private Timestamp childStartTime;

    private Integer probationTime;       //试用时间
    private List<Integer> transProgress;       //阶段更新槽

    private Integer fomaTimes;

    private static Map<StageKey, StageKey> transMap = new HashMap<>(){{
        put(new StageKey(1, 0), new StageKey(2, 0));
        put(new StageKey(2, 0), new StageKey(2, 1));
        put(new StageKey(2, 1), new StageKey(3, 0));
        put(new StageKey(3, 0), new StageKey(3, 1));
        //put(new StageKey(3, 1), new StageKey(3, 2));
        put(new StageKey(3, 2), new StageKey(4, 0));
        put(new StageKey(4, 0), new StageKey(4, 1));
        put(new StageKey(4, 1), new StageKey(4, 2));
        //put(new StageKey(4, 2), new StageKey(4, 0));
    }};

    // 系统初始化
    public void initSystem(){
        mainStageId = 0;
        childStageId = 0;
        mainStartTime = null;
        childStartTime = null;
        probationTime = 0;
        transProgress = new ArrayList<>();
        fomaTimes = 0;
    }

    // 系统开井
    public void openWell(){
        mainStageId = 1;
        childStageId = 0;
        mainStartTime = DateSpawner.getLocalTimestamp();
        childStartTime = DateSpawner.getLocalTimestamp();
        probationTime = 0;
        transProgress = new ArrayList<>();
        fomaTimes = 0;
    }

    // 阶段转换 + 阶段转化触发事件
    public void transStage(Integer tarMainStageId, Integer tarChildStageId, String special) {
        // 打印一次阶段转换log

        // 重置阶段时间
        if(!Objects.equals(tarMainStageId, mainStageId)){
            this.mainStartTime = DateSpawner.getLocalTimestamp();
        }
        if(!Objects.equals(tarChildStageId, childStageId) || !Objects.equals(tarMainStageId, mainStageId)){
            this.childStartTime = DateSpawner.getLocalTimestamp();
        }
        // 重置阶段试用时间
        this.probationTime = StaticParameters.STAGE_PROBATION.get(new StageKey(mainStageId, childStageId));
        // 重置阶段更新槽
        this.transProgress = new ArrayList<>();
        // 若不为气举阶段内部转换，重置气举次数
        if(this.mainStageId == 4 && tarMainStageId != 4){
            this.fomaTimes = 0;
        }
        // 重置阶段ID
        this.mainStageId = tarMainStageId;
        this.childStageId = tarChildStageId;

        // 触发阶段转换事件
        transStageEvent(tarMainStageId, tarChildStageId, null);
    }

    // 阶段转化触发事件
    public void transStageEvent(Integer tarMainStageId, Integer tarChildStageId, String special) {

    }

    // 是否已经将更新槽积累至满值
    public void checkProgressAccumulation(){
        List<Integer> updater = new ArrayList<>();
        int len = StaticParameters.STAGE_MAX_PROGRESS_BAR.get(new StageKey(mainStageId, childStageId)).size();
        for(int i=0; i<len; i++){
            Integer maxProgress = StaticParameters.STAGE_MAX_PROGRESS_BAR.get(new StageKey(mainStageId, childStageId)).get(i);

            // 如果是气举阶段另作判定
            if(mainStageId == 3){
                // 气举一小段时间发现无效
                if(i == 1 && transProgress.get(i) >= maxProgress){
                    transStage(0, 0, "气举无效，智能系统关闭");
                }
                // 气举2天判定结束
                if(i == 2 && transProgress.get(i) >= maxProgress){
                    transStage(3, 2, "气举有效，提前结束气举");
                }
                // 气举3天自动结束
                if(i == 0 && transProgress.get(i) >= maxProgress){
                    transStage(3, 2, "气举正常结束");
                }
            }

            // 如果是加药阶段另作判定
            if(mainStageId == 4){
                if(transProgress.get(i) >= maxProgress){
                    if(fomaTimes < StaticParameters.MAX_FOAM_TIMES){
                        transStage(4, 0, "第" + (fomaTimes + 1) + "次加药效果不佳，拟再次加药");
                    }else{
                        transStage(0, 0, "加药次数达到上限，系统关闭");
                    }
                }
            }

            // 正常阶段 进度达到最大值后触发阶段转换
            if(transProgress.get(i) >= maxProgress){
                StageKey tkey = transMap.get(new StageKey(mainStageId, childStageId));
                transStage(tkey.getX(), tkey.getY(), "正常阶段转换");
            }

        }

        return ;
    }

    // 是否满足更新槽积累的条件 并输出更新值
    public List<Integer> checkProgressAccumulationReq(Integer mainStageId, Integer childStageId, ProductionParameter productionParameter, WellParameter wellParameter) {
        List<Integer> updater = new ArrayList<>();
        int len = StaticParameters.STAGE_MAX_PROGRESS_BAR.get(new StageKey(mainStageId, childStageId)).size();
        for (int i = 0; i < len; i++) {
            updater.add(1);
        }
        switch (mainStageId) {
            case 2:{
                switch (childStageId) {
                    case 1:{
                        //如果井口油压在10分钟内始终小于0.2MPa，认为抽汲系统效果有限
                        if(productionParameter.getOilPressure() < 0.2){
                            updater.set(0, 1);
                        }else{
                            updater.set(0, -1);
                        }
                    }
                }
            }
            case 3:{
                switch (childStageId) {
                    case 1:{
                        //非特殊的：气举系统默认开启3天
                        updater.set(0, 1);
                        //特殊的：发现井口油压在连续的10分钟内依然小于0.2MPa，认为气举无效或检查气举设备
                        if(productionParameter.getOilPressure() < 0.2){
                            updater.set(1, 1);
                        }else{
                            updater.set(1, -1);
                        }
                        //特殊的：若当气举开启2天后，检测到10小时内井口油压几乎保持不变（波动幅度最大1MPa），则认为井底积液已经被有效排出，结束气举系统
                        if(Math.abs(productionParameter.getOilPressure() - productionParameter.getOilAvgPressure()) < StaticParameters.MAX_OIL_PRESSURE_FLUCTUATION){
                            updater.set(2, 1);
                        }else{
                            updater.set(2, -1);
                        }
                    }
                    case 2:{
                        //若油套压差持续增大，10分钟内都高于阈值P时，认为开始出现积液风险，此时开启泡排系统
                        Float pd = 1.0f * wellParameter.getWellDeepth() * StaticParameters.MAX_LIQUID_HOLDUP / 110;
                        if(productionParameter.getCasingPressure() - productionParameter.getOilPressure() > pd){
                            updater.set(0, 1);
                        }else{
                            updater.set(0, -1);
                        }
                    }
                }
            }
            case 4:{
                switch (childStageId) {
                    case 2:{
                        //若油套压差在30分钟内始终大于P，则重新开启泡排系统
                        //若油套压差持续增大，10分钟内都高于阈值P时，认为开始出现积液风险，此时开启泡排系统
                        Float pd = 1.0f * wellParameter.getWellDeepth() * StaticParameters.MAX_LIQUID_HOLDUP / 110;
                        if(productionParameter.getCasingPressure() - productionParameter.getOilPressure() > pd){
                            updater.set(0, 1);
                        }else{
                            updater.set(0, -1);
                        }
                    }
                }
            }
        }
        return updater;
    }

    // 阶段转换持久化方法
    public void run(ProductionParameter productionParameter, WellParameter wellParameter){
        // 检查该阶段的试用期是否结束
        if(probationTime > 0){
            probationTime--;
            return;
        }
        // 试用期结束，累计进度槽
        List<Integer> updater = checkProgressAccumulationReq(mainStageId, childStageId, productionParameter, wellParameter);
        for(int i=0; i<updater.size(); i++){
            //this.transProgress.set(i, this.transProgress + updater.get(i));
        }
        // 检查进度槽是否累计至满 若满则执行阶段转化

    }
}
