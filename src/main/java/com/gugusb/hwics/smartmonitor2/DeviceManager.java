package com.gugusb.hwics.smartmonitor2;

import com.gugusb.hwics.mapper.DFP1Mapper;
import com.gugusb.hwics.pojo.DFP1;
import org.hibernate.collection.spi.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceManager {

    @Autowired
    DFP1Mapper dfp1Mapper;

    public double checkAllTags(){
        Random random = new Random();
        return 0.95 + random.nextDouble();
    }

    public boolean turnOffGasLift(){
        System.out.println("气举设备关闭成功！");
        return true;
    }

    public boolean turnOffFoam(){
        System.out.println("泡排设备关闭成功！");
        return true;
    }

    public boolean turnOffPump(){
        System.out.println("抽吸设备关闭成功！");
        return true;
    }

    public boolean turnOnGasLift(){
        System.out.println("气举设备启动成功！");
        // 问题：设备有两套启停机制，一套是时间 一套是直接开关
        // --气举只能手动开关
        // 如果我用直接开关强制停止了气举，等到再次到达气举开始时间时，气举还会正常启动吗？
        // --可以，但是气举没有启动时间设计，只能够强制停启
        // 开启时间和关闭时间相同时，气举/泡排会立即关闭吗？
        // --会立即停止
        // 关闭泡排只有设置时间一种方式吗？
        // --只有一种方式
        // 泡排如果不设置模式，直接设计开启时间，时间到后可以正常泡排吗？（设置泡排时间还需要前置条件吗？）
        // --打模式的时候只有两种模式，且模式基本不用东，只设计泡排时间即可
        // 模式从气举2切换到泡排2之后，如果到了气举的时间，气举还会正常启动吗？
        // --球阀切换状态后，状态不会改变；
        // --
        // 同时开启需要什么操作
        // --气举和泡排不冲突，不需要额外操作
        // --

        //注气速率-注气排量
        //添加注气压力

        // 参数 时间

        // 1.检查阀门是否打开

        // 2.设计气举时间和结束时间

        return true;
    }

    public boolean turnOnPump(){
        System.out.println("抽汲设备启动成功！");
        // 参数：无

        // 直接开启1-4

        return true;
    }

    public boolean turnOnFoam(){
        System.out.println("泡排设备启动成功！");
        // 参数 时间

        // 1.检查阀门是否打开

        // 2.设计加药时间和结束时间


        return true;
    }



    public Map<String, Boolean> getProcessState() {
        Map<String, Boolean> map = new HashMap<>();
        // 0抽吸 1泡排 2气举
        map.put("gas_lift", false);
        map.put("foam", false);
        map.put("pump", false);

        Optional<DFP1> dfp1 = dfp1Mapper.findFirstByOrderByDataIdDesc();
        if(dfp1 == null)return map;
        if(dfp1.get() == null)return map;

        // 机组1开启就视为开启抽吸

        // 检查泡排开关判定是否开启泡排

        // 检查时间来判断气举是否打开


        return map;
    }
}
