package com.gugusb.hwics.smartmonitor2;

import com.gugusb.hwics.pojo.dto.WriterDTO;
import com.gugusb.hwics.service.OpcuaService;
import com.gugusb.hwics.smartmonitor2.progress.FoamProcess;
import com.gugusb.hwics.smartmonitor2.progress.GasLiftProcess;
import com.gugusb.hwics.smartmonitor2.progress.PumpingProcess;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/dev")
public class DeviceControllerTest {
    @Autowired
    FoamProcess foamProcess;
    @Autowired
    PumpingProcess pumpingProcess;
    @Autowired
    GasLiftProcess gasLiftProcess;

    @Autowired
    OpcuaService opcuaService;

    @PostMapping("/continue-gas-lift/{hours}")
    public MessageRespnser<Boolean> openGL(@PathVariable Double hours){
        System.out.println("DeviceController：下达新的气举任务，气举时长" + hours);
        gasLiftProcess.startWithHours(hours);
        return MessageRespnser.success(true);
    }

    @PostMapping("/change-1-time")
    public MessageRespnser<Boolean> test() throws Exception {
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");
        writerDTO.setDataShort((short) 27);
        writerDTO.setPlace("gugu通道2.混输气举撬123-3.1#机组启泵次数");
        writeData(writerDTO);
        return MessageRespnser.success(true);
    }

    @PostMapping("/start-gas-lift")
    public MessageRespnser<Boolean> startGL() throws Exception {
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");
        writerDTO.setDataShort((short) 1);
        writerDTO.setPlace("gugu通道2.混输气举撬456.气举启动");
        writeData(writerDTO);
        return MessageRespnser.success(true);
    }

    @PostMapping("/stop-gas-lift")
    public MessageRespnser<Boolean> stopGL() throws Exception {
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");
        writerDTO.setDataShort((short) 1);
        writerDTO.setPlace("gugu通道2.混输气举撬456.气举关闭");
        writeData(writerDTO);
        return MessageRespnser.success(true);
    }

    @PostMapping("/start-pump")
    public MessageRespnser<Boolean> startPump() throws Exception{
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");
        writerDTO.setDataShort((short) 1);
        writerDTO.setPlace("gugu通道2.混输气举撬123-3.1#机组启用或停运");
        writeData(writerDTO);
        writerDTO.setPlace("gugu通道2.混输气举撬123-3.2#机组启用或停运");
        writeData(writerDTO);
        writerDTO.setPlace("gugu通道2.混输气举撬123-3.3#机组启用或停运");
        writeData(writerDTO);
        writerDTO.setPlace("gugu通道2.混输气举撬456.4#机组启用或停运");
        writeData(writerDTO);

        return MessageRespnser.success(true);
    }

    @PostMapping("/end-pump")
    public MessageRespnser<Boolean> endPump() throws Exception{
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");
        writerDTO.setDataShort((short) 1);
        writerDTO.setPlace("gugu通道2.混输气举撬123-3.1#机组启用或停运");
        writeData(writerDTO);
        writerDTO.setPlace("gugu通道2.混输气举撬123-3.2#机组启用或停运");
        writeData(writerDTO);
        writerDTO.setPlace("gugu通道2.混输气举撬123-3.3#机组启用或停运");
        writeData(writerDTO);
        writerDTO.setPlace("gugu通道2.混输气举撬456.4#机组启用或停运");
        writeData(writerDTO);

        return MessageRespnser.success(true);
    }

    @PostMapping("/start-form")
    public MessageRespnser<Boolean> startFM() throws Exception {
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");

        LocalDateTime time = LocalDateTime.now().plus(Duration.ofMinutes(2));
        LocalDateTime endtime = time.plus(Duration.ofMinutes(5));

        short startHour = (short) time.getHour();
        short startMin = (short) time.getMinute();
        short stopHour = (short) endtime.getHour();
        short stopMin = (short) endtime.getMinute();

        System.out.println("泡排时间：" + startHour + " " + startMin + " / " +
                stopHour + " " + stopMin);

        if(true){
            // 开始时
            writerDTO.setPlace("gugu通道2.加药.时间1-启动时");
            writerDTO.setDataShort(startHour);
            writeData(writerDTO);

            // 开始分
            writerDTO.setPlace("gugu通道2.加药.时间1-启动分");
            writerDTO.setDataShort(startMin);
            writeData(writerDTO);

            // 结束时
            writerDTO.setPlace("gugu通道2.加药.时间1-停止时");
            writerDTO.setDataShort(stopHour);
            writeData(writerDTO);

            // 结束分
            writerDTO.setPlace("gugu通道2.加药.时间1-停止分");
            writerDTO.setDataShort(stopMin);
            writeData(writerDTO);
        }

        return MessageRespnser.success(true);
    }

    @PostMapping("/end-form")
    public MessageRespnser<Boolean> endFM() throws Exception {
        WriterDTO writerDTO = new WriterDTO();
        writerDTO.setDataType("short");;

        short startHour = 0;
        short startMin = 0;
        short stopHour = 0;
        short stopMin = 0;

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

        return MessageRespnser.success(true);
    }

    private Boolean writeData(WriterDTO writer) throws Exception {
        System.out.println(writer.toString());
        Boolean result = switch (writer.getDataType()) {
            case "boolean" -> opcuaService.writeDataBoolean(writer.getDataBoolean(), writer.getPlace());
            case "short" -> opcuaService.writeDataShort(writer.getDataShort(), writer.getPlace());
            case "float" -> opcuaService.writeDataFloat(writer.getDataFloat(), writer.getPlace());
            default -> false;
        };
        if(!result){
            return false;
        }else{
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
        }
        return true;
    }

}
