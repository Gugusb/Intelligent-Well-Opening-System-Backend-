package com.gugusb.hwics.controller;

import com.gugusb.hwics.pojo.DFP3;
import com.gugusb.hwics.pojo.DFP4;
import com.gugusb.hwics.service.DFP3Service;
import com.gugusb.hwics.service.DFP4Service;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page4")
public class DFP4Controller {
    @Autowired
    private DFP4Service dfp4Service;

    @GetMapping("/getlastdata")
    public MessageRespnser<DFP4> getLast() {
        DFP4 dfp4 = dfp4Service.getLeastData();
        return MessageRespnser.success(dfp4);
    }
}
