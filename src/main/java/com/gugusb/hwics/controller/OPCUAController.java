package com.gugusb.hwics.controller;

import com.gugusb.hwics.conn.ClientConnectManagerRunner;
import com.gugusb.hwics.conn.OPCUAReader;
import com.gugusb.hwics.pojo.dto.WriterDTO;
import com.gugusb.hwics.service.OpcuaService;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opcua")
public class OPCUAController {

    @Autowired
    OpcuaService opcuaService;

    @GetMapping("/updatedata")
    public MessageRespnser<String> updateData() throws Exception {
        opcuaService.readPageData();
        return MessageRespnser.success("ok");
    }

    @PutMapping("/writedata")
    public MessageRespnser<String> writeData(@RequestBody WriterDTO writer) throws Exception {
        System.out.println(writer.toString());
        Boolean result = switch (writer.getDataType()) {
            case "boolean" -> opcuaService.writeDataBoolean(writer.getDataBoolean(), writer.getPlace());
            case "short" -> opcuaService.writeDataShort(writer.getDataShort(), writer.getPlace());
            case "float" -> opcuaService.writeDataFloat(writer.getDataFloat(), writer.getPlace());
            default -> false;
        };
        if(!result){
            MessageRespnser.unsuccess("bad");
        }else{
            System.out.println("成功更新数据 开始读取数据");
            opcuaService.readPageData();
        }
        return MessageRespnser.success("ok");
    }
}
