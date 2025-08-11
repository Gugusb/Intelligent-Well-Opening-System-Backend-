package com.gugusb.hwics.controller;

import com.gugusb.hwics.conn.ClientConnectManagerRunner;
import com.gugusb.hwics.conn.OPCUAReader;
import com.gugusb.hwics.service.OpcuaService;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
