package com.gugusb.hwics.service.Interface;

import com.gugusb.hwics.pojo.DFP1;

public interface IDFP1Service {
    DFP1 getLeastData();

    DFP1 getDFP1(int dataId);
}
