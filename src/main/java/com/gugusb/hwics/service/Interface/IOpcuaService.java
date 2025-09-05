package com.gugusb.hwics.service.Interface;

import com.gugusb.hwics.pojo.DataCheckResult;
import org.springframework.scheduling.annotation.Async;

public interface IOpcuaService {
    DataCheckResult checkPageData() throws Exception;

    void readPageData() throws Exception;


    Boolean writeDataBoolean(Boolean data, String place) throws Exception;

    Boolean writeDataShort(Short data, String place) throws Exception;

    Boolean writeDataFloat(Float data, String place) throws Exception;
}
