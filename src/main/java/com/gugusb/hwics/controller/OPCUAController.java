package com.gugusb.hwics.controller;

import com.gugusb.hwics.conn.ClientConnectManagerRunner;
import com.gugusb.hwics.pojo.DFP5;
import com.gugusb.hwics.service.DFP5Service;
import com.gugusb.hwics.service.OPCUAReaderService;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/opcua")
public class OPCUAController {

    @GetMapping("/getlastdata")
    public MessageRespnser<String> getLast() throws Exception {
        OPCUAReaderService example = new OPCUAReaderService();

        new ClientConnectManagerRunner(example, true).run();
        return MessageRespnser.success("ok");
    }
}
