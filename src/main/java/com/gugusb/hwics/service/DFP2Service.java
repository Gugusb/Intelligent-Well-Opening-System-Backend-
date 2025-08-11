package com.gugusb.hwics.service;

import com.gugusb.hwics.exception.ExceptionAdvice;
import com.gugusb.hwics.mapper.DFP2Mapper;
import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.DFP2;
import com.gugusb.hwics.service.Interface.IDFP2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DFP2Service implements IDFP2Service {
    @Autowired
    DFP2Mapper dfp2Mapper;

    @Override
    public DFP2 getLeastData() {
        return dfp2Mapper.findFirstByOrderByDataIdDesc().orElseThrow(() -> {
            throw new IllegalArgumentException("Database is empty");
        });
    }
}
