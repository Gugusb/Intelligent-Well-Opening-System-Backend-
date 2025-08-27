package com.gugusb.hwics.service.Interface;

import org.springframework.scheduling.annotation.Async;

public interface IOpcuaService {
    void readPageData() throws Exception;


    Boolean writeDataBoolean(Boolean data, String place) throws Exception;

    Boolean writeDataShort(Short data, String place) throws Exception;

    Boolean writeDataFloat(Float data, String place) throws Exception;
}
