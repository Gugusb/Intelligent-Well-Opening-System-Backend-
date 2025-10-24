package com.gugusb.hwics.smartmonitor2;

import com.gugusb.hwics.mapper.DFP1Mapper;
import com.gugusb.hwics.mapper.DFP4Mapper;
import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.DFP4;
import com.gugusb.hwics.pojo.dto.WriterDTO;
import com.gugusb.hwics.service.OpcuaService;
import org.hibernate.collection.spi.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class DeviceManager {

    @Autowired
    DFP1Mapper dfp1Mapper;
    @Autowired
    DFP4Mapper dfp4Mapper;
    @Autowired
    OpcuaService opcuaService;

    Boolean isReal = false;

    public double checkAllTags(){
        Random random = new Random();
        return 0.95 + random.nextDouble();
    }

    public boolean turnOffGasLift(){
        System.out.println("DeviceManager:气举设备关闭成功！");
        if(isReal){
            WriterDTO writerDTO = new WriterDTO();
            writerDTO.setDataType("short");
            writerDTO.setDataShort((short) 1);
            writerDTO.setPlace("gugu通道2.混输气举撬456.气举关闭");
            writeData(writerDTO);
        }
        return true;
    }

    public boolean turnOffFoam(){
        System.out.println("DeviceManager:泡排设备关闭成功！");
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");

        short startHour = (short) 12;
        short startMin = (short) 0;
        short stopHour = (short) 12;
        short stopMin = (short) 0;

        System.out.println("泡排时间：" + startHour + " " + startMin + " / " +
                stopHour + " " + stopMin);

        if(isReal){
            // 开始时
            writerDTO.setPlace("gugu通道2.加药.启动时");
            writerDTO.setDataShort(startHour);
            writeData(writerDTO);

            // 开始分
            writerDTO.setPlace("gugu通道2.加药.启动分");
            writerDTO.setDataShort(startMin);
            writeData(writerDTO);

            // 结束时
            writerDTO.setPlace("gugu通道2.加药.结束时");
            writerDTO.setDataShort(stopHour);
            writeData(writerDTO);

            // 结束分
            writerDTO.setPlace("gugu通道2.加药.结束分");
            writerDTO.setDataShort(stopMin);
            writeData(writerDTO);
        }
        return true;
    }

    public boolean turnOffPump(){
        System.out.println("DeviceManager:抽吸设备关闭成功！");
        if(isReal){
            WriterDTO writerDTO = new WriterDTO();
            writerDTO.setDataType("short");
            writerDTO.setDataShort((short) 0);
            writerDTO.setPlace("gugu通道2.混输气举撬123-3.1#机组启用或停运");
            writeData(writerDTO);
            writerDTO.setPlace("gugu通道2.混输气举撬123-3.2#机组启用或停运");
            writeData(writerDTO);
            writerDTO.setPlace("gugu通道2.混输气举撬123-3.3#机组启用或停运");
            writeData(writerDTO);
            writerDTO.setPlace("gugu通道2.混输气举撬456.4#机组启用或停运");
            writeData(writerDTO);
        }
        return true;
    }

    public boolean turnOnGasLift(){
        System.out.println("DeviceManager:气举设备启动成功！");
        if(isReal){
            WriterDTO writerDTO = new WriterDTO();
            writerDTO.setDataType("short");
            writerDTO.setDataShort((short) 1);
            writerDTO.setPlace("gugu通道2.混输气举撬456.气举启动");
            writeData(writerDTO);
        }
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
        System.out.println("DeviceManager:抽汲设备启动成功！");
        if(isReal){
            WriterDTO writerDTO = new WriterDTO();
            writerDTO.setDataType("short");
            writerDTO.setDataShort((short) 0);
            writerDTO.setPlace("gugu通道2.混输气举撬123-3.1#机组启用或停运");
            writeData(writerDTO);
            writerDTO.setPlace("gugu通道2.混输气举撬123-3.2#机组启用或停运");
            writeData(writerDTO);
            writerDTO.setPlace("gugu通道2.混输气举撬123-3.3#机组启用或停运");
            writeData(writerDTO);
            writerDTO.setPlace("gugu通道2.混输气举撬456.4#机组启用或停运");
            writeData(writerDTO);
        }
        // 直接开启1-4
        return true;
    }

    public boolean turnOnFoam(LocalDateTime time, Duration duration){
        System.out.println("DeviceManager:泡排设备启动成功！");
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");

        LocalDateTime endtime = time.plus(duration);

        short startHour = (short) time.getHour();
        short startMin = (short) time.getMinute();
        short stopHour = (short) endtime.getHour();
        short stopMin = (short) endtime.getMinute();

        System.out.println("泡排时间：" + startHour + " " + startMin + " / " +
                stopHour + " " + stopMin);

        if(false){
            // 开始时
            writerDTO.setPlace("gugu通道2.加药.启动时");
            writerDTO.setDataShort(startHour);
            writeData(writerDTO);

            // 开始分
            writerDTO.setPlace("gugu通道2.加药.启动分");
            writerDTO.setDataShort(startMin);
            writeData(writerDTO);

            // 结束时
            writerDTO.setPlace("gugu通道2.加药.结束时");
            writerDTO.setDataShort(stopHour);
            writeData(writerDTO);

            // 结束分
            writerDTO.setPlace("gugu通道2.加药.结束分");
            writerDTO.setDataShort(stopMin);
            writeData(writerDTO);
        }

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

        Optional<DFP4> dfp4 = dfp4Mapper.findFirstByOrderByDataIdDesc();
        if(dfp4 == null)return map;
        if(dfp4.get() == null)return map;

        // 机组1开启就视为开启抽吸
        if(dfp1.get().getUnit1RunStatus() != null && dfp1.get().getUnit1RunStatus()){
            map.put("pump", true);
            System.out.println("抽吸为开启状态");
        }
        // 检查泡排开关判定是否开启泡排
        if(dfp4.get().getDosingRunStatus() != null && dfp4.get().getDosingRunStatus()){
            map.put("foam", true);
        }
        // 检查45的开关
        if(dfp1.get().getCurrentMode() != null && dfp1.get().getCurrentMode()){
            if(dfp1.get().getUnit5RunStatus() && dfp1.get().getUnit6RunStatus()){
                map.put("gas_lift", true);
            }
        }

        return map;
    }

    private Boolean writeData(WriterDTO writer){
        System.out.println(writer.toString());
        try{
            Boolean result = switch (writer.getDataType()) {
                case "boolean" -> opcuaService.writeDataBoolean(writer.getDataBoolean(), writer.getPlace());
                case "short" -> opcuaService.writeDataShort(writer.getDataShort(), writer.getPlace());
                case "float" -> opcuaService.writeDataFloat(writer.getDataFloat(), writer.getPlace());
                default -> false;
            };
            if(!result){
                return false;
            }else{
                try{
                    // 异步执行读取操作
                    CompletableFuture.runAsync(() -> {
                        System.out.println("开始异步读取数据");
                        try {
                            opcuaService.readPageData();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("异步读取完成");
                    });
                }catch (Exception e){
                    System.err.println("DeciceManager:" + e);
                }

            }
            return true;
        }catch (Exception e){
            System.err.println("DeviceManager:" + e);
            return false;
        }
    }
}
