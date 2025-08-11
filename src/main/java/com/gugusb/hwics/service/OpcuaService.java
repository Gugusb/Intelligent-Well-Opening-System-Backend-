package com.gugusb.hwics.service;

import com.gugusb.hwics.conn.ClientConnectManagerRunner;
import com.gugusb.hwics.conn.OPCUAReader;
import com.gugusb.hwics.exception.ExceptionAdvice;
import com.gugusb.hwics.mapper.DFP1Mapper;
import com.gugusb.hwics.pojo.DFP1;
import com.gugusb.hwics.pojo.DFPMix;
import com.gugusb.hwics.service.Interface.IOpcuaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OpcuaService implements IOpcuaService {
    @Autowired
    DFP1Mapper dfp1Mapper;

    @Override
    public void readPageData() throws Exception {
        OPCUAReader example = new OPCUAReader();
        DFPMix newDfpm = new ClientConnectManagerRunner(example, true).run();
        dfp1Mapper.save(newDfpm.getDfp1());
        return ;
    }

    @Retryable(value = {DataAccessException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    @Scheduled(fixedRateString = "${date1.update.interval:100000}")
    public void periodicUserUpdate() throws Exception {
        try {
            //从服务器获取一次最新数据
            OPCUAReader example = new OPCUAReader();
            DFPMix newDfpm = new ClientConnectManagerRunner(example, true).run();
            dfp1Mapper.save(newDfpm.getDfp1());
            //System.out.println("data update succeed");
        } catch (Exception ex) {
            Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);
            log.error("从服务器更新数据1失败", ex);
            throw ex; // 触发重试机制
        }
    }
}
