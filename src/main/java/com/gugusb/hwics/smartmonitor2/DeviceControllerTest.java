package com.gugusb.hwics.smartmonitor2;

import com.gugusb.hwics.smartmonitor2.progress.FoamProcess;
import com.gugusb.hwics.smartmonitor2.progress.GasLiftProcess;
import com.gugusb.hwics.smartmonitor2.progress.PumpingProcess;
import com.gugusb.hwics.utils.MessageRespnser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
public class DeviceControllerTest {
    @Autowired
    FoamProcess foamProcess;
    @Autowired
    PumpingProcess pumpingProcess;
    @Autowired
    GasLiftProcess gasLiftProcess;

    @PostMapping("/open-gas-lift/{hours}")
    public MessageRespnser<Boolean> openGL(@PathVariable Double hours){
        System.out.println("DeviceController：下达新的气举任务，气举时长" + hours);
        gasLiftProcess.startWithHours(hours);
        return MessageRespnser.success(true);
    }

}
