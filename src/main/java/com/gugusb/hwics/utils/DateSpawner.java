package com.gugusb.hwics.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class DateSpawner {
    public static Timestamp getLocalTimestamp(){
        // 获取当前 UTC 时间戳
        long utcMillis = System.currentTimeMillis();

        int offset = TimeZone.getTimeZone("Asia/Shanghai").getOffset(utcMillis);

        return new Timestamp(utcMillis + offset);
    }

    public static LocalDateTime getLocalTime(){
        ZonedDateTime beijingZonedTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        LocalDateTime beijingTime1 = beijingZonedTime.toLocalDateTime().plusHours(8);
        return beijingTime1;
    }
}
