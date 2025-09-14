package com.gugusb.hwics.smartmonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WellMonitoringTask {
    @Autowired
    SmartMonitor smartMonitor;

    @Autowired
    StaticParameters staticParameters;



}
