package com.gugusb.hwics.service;

import com.gugusb.hwics.exception.ExceptionAdvice;
import com.gugusb.hwics.mapper.DFP1Mapper;
import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.service.Interface.IDFP1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DFP1Service implements IDFP1Service {
    @Autowired
    DFP1Mapper dfp1Mapper;

    @Override
    public DFP1 getLeastData() {
        return dfp1Mapper.findFirstByOrderByDataIdDesc().orElseThrow(() -> {
            throw new IllegalArgumentException("Database is empty");
        });
    }

    //每隔一段时间从服务器更新一次数据
    @Retryable(value = {DataAccessException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    @Scheduled(fixedRateString = "${date1.update.interval:100000}")
    public void periodicUserUpdate() {
        try {
            //从服务器获取一次最新数据
            dfp1Mapper.save(DFP1.createRandomInstance());
            //System.out.println("data update succeed");
        } catch (Exception ex) {
            Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);
            log.error("从服务器更新数据失败", ex);
            throw ex; // 触发重试机制
        }
    }

    @Override
    public DFP1 getDFP1(int dataId) {
        return dfp1Mapper.findById(dataId).orElseThrow(() -> {
            throw new IllegalArgumentException("ID not found");
        });
    }
}
