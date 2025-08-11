package com.gugusb.hwics.service;

import com.gugusb.hwics.exception.ExceptionAdvice;

import com.gugusb.hwics.mapper.DFP5Mapper;

import com.gugusb.hwics.pojo.DFP5;

import com.gugusb.hwics.service.Interface.IDFP5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DFP5Service implements IDFP5Service {
    @Autowired
    DFP5Mapper dfp5Mapper;

    @Override
    public DFP5 getLeastData() {
        return dfp5Mapper.findFirstByOrderByDataIdDesc().orElseThrow(() -> {
            throw new IllegalArgumentException("Database is empty");
        });
    }

}
