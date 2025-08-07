package com.gugusb.hwics.controller;

import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.User;
import com.gugusb.hwics.pojo.dto.UserDTO;
import com.gugusb.hwics.service.DFP1Service;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page1")
public class DFP1Controller {
    @Autowired
    private DFP1Service dfp1Service;

    @GetMapping("/getlastdata")
    public MessageRespnser<DFP1> getLast() {
        DFP1 dfp1 = dfp1Service.getLeastData();
        return MessageRespnser.success(dfp1);
    }
}
