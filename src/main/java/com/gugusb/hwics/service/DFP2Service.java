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

    //每隔一段时间从服务器更新一次数据
    @Retryable(value = {DataAccessException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    @Scheduled(fixedRateString = "${date2.update.interval:100000}")
    public void periodicUserUpdate() {
        try {
            //从服务器获取一次最新数据
            dfp2Mapper.save(DFP2.createRandomInstance());
            //System.out.println("data update succeed");
        } catch (Exception ex) {
            Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);
            log.error("从服务器更新数据2失败", ex);
            throw ex; // 触发重试机制
        }
    }
}
