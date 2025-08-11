package com.gugusb.hwics.service;

import com.gugusb.hwics.exception.ExceptionAdvice;
import com.gugusb.hwics.mapper.DFP4Mapper;
import com.gugusb.hwics.pojo.DFP4;
import com.gugusb.hwics.service.Interface.IDFP4Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DFP4Service implements IDFP4Service {
    @Autowired
    DFP4Mapper dfp4Mapper;

    @Override
    public DFP4 getLeastData() {
        return dfp4Mapper.findFirstByOrderByDataIdDesc().orElseThrow(() -> {
            throw new IllegalArgumentException("Database is empty");
        });
    }
}
