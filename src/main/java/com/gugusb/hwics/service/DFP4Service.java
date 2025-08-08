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

    //每隔一段时间从服务器更新一次数据
    @Retryable(value = {DataAccessException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    @Scheduled(fixedRateString = "${date4.update.interval:100000}")
    public void periodicUserUpdate() {
        try {
            //从服务器获取一次最新数据
            dfp4Mapper.save(DFP4.createRandomInstance());
            //System.out.println("data update succeed");
        } catch (Exception ex) {
            Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);
            log.error("从服务器更新数据4失败", ex);
            throw ex; // 触发重试机制
        }
    }
}
