package com.gugusb.hwics.controller;

import com.gugusb.hwics.pojo.DFP2;
import com.gugusb.hwics.pojo.DFP3;
import com.gugusb.hwics.service.DFP2Service;
import com.gugusb.hwics.service.DFP3Service;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page3")
public class DFP3Controller {
    @Autowired
    private DFP3Service dfp3Service;

    @GetMapping("/getlastdata")
    public MessageRespnser<DFP3> getLast() {
        DFP3 dfp3 = dfp3Service.getLeastData();
        return MessageRespnser.success(dfp3);
    }
}
