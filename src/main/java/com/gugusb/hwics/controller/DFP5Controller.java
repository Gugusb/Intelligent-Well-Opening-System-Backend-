package com.gugusb.hwics.controller;

import com.gugusb.hwics.pojo.DFP4;
import com.gugusb.hwics.pojo.DFP5;
import com.gugusb.hwics.service.DFP4Service;
import com.gugusb.hwics.service.DFP5Service;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page5")
public class DFP5Controller {
    @Autowired
    private DFP5Service dfp5Service;

    @GetMapping("/getlastdata")
    public MessageRespnser<DFP5> getLast() {
        DFP5 dfp5 = dfp5Service.getLeastData();
        return MessageRespnser.success(dfp5);
    }
}
