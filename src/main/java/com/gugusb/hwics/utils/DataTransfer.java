package com.gugusb.hwics.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class DataTransfer {
    private static Map<String, Double> ts = new HashMap<>();

    static {
        String head = "gugu通道2.";
        ts.put(head + "混输气举撬123.1#机组前端压力", 0.01);
        ts.put(head + "混输气举撬123.123#后端压力", 0.01);
        ts.put(head + "混输气举撬123.1#机启动压力", 0.01);
        ts.put(head + "混输气举撬123.1#机停止压力", 0.1);
        ts.put(head + "混输气举撬123.2#机组前端压力", 0.01);
        ts.put(head + "混输气举撬123.2#机组瞬时流量", 0.1);
        ts.put(head + "混输气举撬123.2#机组累计流量", 100d);
        ts.put(head + "混输气举撬123.2#机启动压力", 0.01);
        ts.put(head + "混输气举撬123.2#机停止压力", 0.01);
        ts.put(head + "混输气举撬123.3#机组前端压力", 0.01);
        ts.put(head + "混输气举撬123.3#机组瞬时流量", 0.1);
        ts.put(head + "混输气举撬123.3#机组累计流量", 100d);
        ts.put(head + "混输气举撬123.3#机启动压力", 0.01);
        ts.put(head + "混输气举撬123.3#机停止压力", 0.01);
        ts.put(head + "混输气举撬123.后端报警压力", 0.01);
        ts.put(head + "混输气举撬123.后端恢复压力", 0.01);
        ts.put(head + "混输气举撬123.123#总瞬时流量", 0.1);
        ts.put(head + "混输气举撬123.123#总累计流量", 100d);

        ts.put(head + "混输气举撬456.4#5#前端压力", 0.01);
        ts.put(head + "混输气举撬456.45#后端压力", 0.01);
        ts.put(head + "混输气举撬456.6#前端压力值", 0.01);
        ts.put(head + "混输气举撬456.6#后端压力值", 0.01);
        ts.put(head + "混输气举撬456.4#启动压力", 0.01);
        ts.put(head + "混输气举撬456.4#停止压力", 0.01);
        ts.put(head + "混输气举撬456.后端报警压力1", 0.01);
        ts.put(head + "混输气举撬456.后端恢复压力1", 0.01);
        ts.put(head + "混输气举撬456.5#启动压力", 0.01);
        ts.put(head + "混输气举撬456.5#停止压力", 0.01);
        ts.put(head + "混输气举撬456.4#5#总瞬时流量", 0.1);
        ts.put(head + "混输气举撬456.4#5#总累计流量", 100d);
        ts.put(head + "混输气举撬456.气举总瞬时流量", 0.1);
        ts.put(head + "混输气举撬456.气举总累计流量", 100d);
        ts.put(head + "混输气举撬456.3#高限位保护值", 0.01);
        ts.put(head + "混输气举撬456.3#恢复压力值", 0.01);
        ts.put(head + "混输气举撬456.4#高限位报警值", 0.01);
        ts.put(head + "混输气举撬456.4#左阀时间", 0.1);
        ts.put(head + "混输气举撬456.4#右阀时间", 0.1);
        ts.put(head + "混输气举撬456.5#左阀时间", 0.1);
        ts.put(head + "混输气举撬456.5#右阀时间", 0.1);
        ts.put(head + "加药.加药压力", 0.01);
        ts.put(head + "加药.加药后端报警压力", 0.01);
        ts.put(head + "加药.加药后端恢复压力", 0.01);

    }

    public static Double getTrans(String tagName){
        return ts.get(tagName);
    }

    public static Boolean canTrans(String tagName){
        return ts.containsKey(tagName);
    }
}
