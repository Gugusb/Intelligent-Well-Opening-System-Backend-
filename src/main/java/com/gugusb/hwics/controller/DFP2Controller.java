package com.gugusb.hwics.controller;

import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.DFP2;
import com.gugusb.hwics.service.DFP1Service;
import com.gugusb.hwics.service.DFP2Service;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page2")
public class DFP2Controller {
    @Autowired
    private DFP2Service dfp2Service;

    @GetMapping("/getlastdata")
    public MessageRespnser<DFP2> getLast() {
        DFP2 dfp2 = dfp2Service.getLeastData();
        return MessageRespnser.success(dfp2);
    }
}
